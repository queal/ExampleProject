<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello</title>
</head>
<body>

	<form action="./changeLanguage" method="post">
		<select name="language">
			<option value="en">En</option>
			<option value="zh_CN">zhCN</option>
		</select>
		<input type="submit" value="change">
	</form>
	<div>
		<spring:message code="welcome"></spring:message>
	</div>

</body>

</html>