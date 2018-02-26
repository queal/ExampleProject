package com.example.test;

import java.math.BigDecimal;

import com.example.utils.FeeRegUtils;

public class FeeTest {

	public static void main(String[] args) {
		BigDecimal money = new BigDecimal(10);
		String feeReg = "S_1.5";
		System.out.println(FeeRegUtils.calcFee(money, feeReg));
		
		// System.out.println(FeeRegUtils.calcSingleFee(money, feeReg));

		// money = new BigDecimal(10);
		// feeReg = "P_0.5_6_10";
		// System.out.println(FeeRegUtils.calcPercentFee(money, feeReg));

		money = new BigDecimal(15);
		feeReg = "B_(0,10]#P_0.5_4_10@(10,20]#S_0.5";
//		System.out.println(FeeRegUtils.calcBetweenFee(money, feeReg));

		// money = new BigDecimal(10);
		// feeReg = "";
		// System.out.println(FeeRegUtils.calcPercentFee(money, feeReg));
	}
}
