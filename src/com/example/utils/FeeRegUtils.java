package com.example.utils;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FeeRegUtils {
	private static final Log logger = LogFactory.getLog(FeeRegUtils.class);

	public static void main(String[] args) {
		BigDecimal money = new BigDecimal(10);
		String feeReg = "S_1.5";
		// System.out.println(calcSingleFee(money, feeReg));

		// money = new BigDecimal(10);
		// feeReg = "P_0.5_6_10";
		// System.out.println(calcPercentFee(money, feeReg));

		money = new BigDecimal(15);
		feeReg = "B_(0,10]#P_0.5_4_10@(10,20]#S_0.5";
		System.out.println(calcBetweenFee(money, feeReg));

		// money = new BigDecimal(10);
		// feeReg = "";
		// System.out.println(calcPercentFee(money, feeReg));
	}

	/**
	 * 返回null说明计算有错误
	 * 
	 * @param money
	 * @param feeReg
	 * @return
	 */
	public static BigDecimal calcFee(BigDecimal money, String feeReg) {
		BigDecimal feeMoney = null;
		// S_money=单笔_价格
		// P_ratio_low_top=百分比收费_系数_下限_上限
		// B_[low,top)#S or P reg_[low,top)#S or P reg...

		if (StringUtils.isBlank(feeReg)) {
			feeMoney = new BigDecimal(0);
		} else if (StringUtils.startsWith(feeReg, "S")) {
			feeMoney = calcSingleFee(money, feeReg);
		} else if (StringUtils.startsWith(feeReg, "P")) {
			feeMoney = calcPercentFee(money, feeReg);
		} else if (StringUtils.startsWith(feeReg, "B")) {
			feeMoney = calcBetweenFee(money, feeReg);
		}

		return feeMoney;
	}

	/**
	 * @param money
	 * @param feeReg
	 *            S_money=单笔_价格
	 * @return
	 */
	public static BigDecimal calcSingleFee(BigDecimal money, String feeReg) {
		BigDecimal feeMoney = null;
		try {
			if (StringUtils.isBlank(feeReg)) {
				return new BigDecimal(0);
			}

			if (!StringUtils.startsWith(feeReg, "S_")) {
				return feeMoney;
			}

			feeMoney = new BigDecimal(feeReg.split("_")[1]);
		} catch (Exception e) {
			logger.error("计费表达式计算错误 feeReg:" + feeReg, e);
		}
		return feeMoney;
	}

	/**
	 * @param money
	 * @param feeReg
	 *            P_ratio_low_top=百分比收费_系数_下限_上限
	 * @return
	 */
	public static BigDecimal calcPercentFee(BigDecimal money, String feeReg) {
		BigDecimal feeMoney = null;
		BigDecimal ratio = null;
		BigDecimal low = null;
		BigDecimal top = null;
		try {
			if (StringUtils.isBlank(feeReg)) {
				return new BigDecimal(0);
			}

			if (!StringUtils.startsWith(feeReg, "P_")) {
				return feeMoney;
			}

			String[] feeRegParams = feeReg.split("_");
			ratio = new BigDecimal(feeRegParams[1]);
			if (feeRegParams.length >= 3) {
				low = new BigDecimal(feeRegParams[2]);
			}
			if (feeRegParams.length >= 4) {
				top = new BigDecimal(feeRegParams[3]);
			}

			feeMoney = money.multiply(ratio);
			if (low != null) {
				feeMoney = feeMoney.max(low);
			}
			if (top != null) {
				feeMoney = feeMoney.min(top);
			}

		} catch (Exception e) {
			logger.error("计费表达式计算错误 feeReg:" + feeReg, e);
		}
		return feeMoney;
	}

	/**
	 * 
	 * @param money
	 * @param feeReg
	 *            B_[low,top)#S or P reg_[low,top)#S or P reg...
	 * @return
	 */
	public static BigDecimal calcBetweenFee(BigDecimal money, String feeReg) {

		try {
			if (StringUtils.isBlank(feeReg)) {
				return new BigDecimal(0);
			}

			if (!StringUtils.startsWith(feeReg, "B_")) {
				return null;
			}

			String feeRegDetail = feeReg.substring(feeReg.indexOf("B_") + 2);
			// [low,top)#S reg_[low,top)#P reg
			for (String subFeeRegDetail : feeRegDetail.split("@")) {
				String subFeeReg = subFeeRegDetail.substring(subFeeRegDetail.indexOf("#") + 1);
				BigDecimal low = new BigDecimal(subFeeRegDetail.substring(1, subFeeRegDetail.indexOf(",")));
				BigDecimal top = new BigDecimal(
						subFeeRegDetail.substring(subFeeRegDetail.indexOf(",") + 1, subFeeRegDetail.indexOf("#") - 1));

				boolean lowEqFlag = '[' == (subFeeRegDetail.charAt(0));
				boolean topEqFlag = ']' == (subFeeRegDetail.charAt(subFeeRegDetail.indexOf("#") - 1));

				if (lowEqFlag && topEqFlag) {
					if (money.compareTo(low) >= 0 && money.compareTo(top) <= 0) {
						return calcFee(money, subFeeReg);
					}
				} else if (!lowEqFlag && topEqFlag) {
					if (money.compareTo(low) > 0 && money.compareTo(top) <= 0) {
						return calcFee(money, subFeeReg);
					}
				} else if (lowEqFlag && !topEqFlag) {
					if (money.compareTo(low) >= 0 && money.compareTo(top) < 0) {
						return calcFee(money, subFeeReg);
					}
				} else if (!lowEqFlag && !topEqFlag) {
					if (money.compareTo(low) > 0 && money.compareTo(top) < 0) {
						return calcFee(money, subFeeReg);
					}
				}
			}

		} catch (Exception e) {
			logger.error("计费表达式计算错误 feeReg:" + feeReg, e);
		}
		return null;
	}

}
