<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello</title>
</head>
<body>

	<form action="./doImgCheckAction" method="post">
		业务输入val1:<input name="val1" value="${val1 }">
		
		验证码:
		<input name="checkCode" value="${checkCode }">
		<img src="./getImgCheckCode" onclick="this.src='./getImgCheckCode?'+Math.random();">
		<input type="submit" value="submit">
	</form>

	<div>
		${msg }
	</div>

</body>

</html>