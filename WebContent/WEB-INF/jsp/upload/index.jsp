<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="./js/jquery-3.0.0.min.js"></script>
<title>Hello</title>
</head>
<body>
	<div>${msg },
		<span class="fileName">${fileName}</span>
	</div>
	<div>
		上传例子：
		<form action="./doUploadFile" method="post"
			enctype="multipart/form-data">
			选择文件: <input type="file" name="file"> <input name="key1">
			<input type="submit" value="提交">
		</form>
	</div>

	<div>
		<input type="button" class="showPicBtn" value="显示图片">
		<img class="showPicImg">
	</div>

</body>

<script type="text/javascript">
	$(document).ready(function() {
		$(".showPicBtn").click(function() {
			var fileName = $(".fileName").html();
			if (fileName == '') {
				return;
			}

			$.post("showUploadFile.asp", {
				fileName : fileName
			}, function(data) {
				$(".showPicImg").attr("src", data);
			});
		});
	});
</script>
</html>