<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<meta name="keywords" content="学生成绩批改"/>
		<meta name="Description" content="学生成绩批改系统登陆"/>
		<title>学生成绩批改系统登陆</title>
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/code.js"></script>
	</head>
	
	<body>
		<!--login start-->
		<div class="login1">
			<p class="title">用户登陆</p>
			<div class="login2">
			<form action="teaLogin" method="post">
			<p>用户名:</p>
			<i class="icon-user"></i>
		    <input type="text" id="stuid" name="teaid" class="name"/>
		    <p>密码:</p>
		    <i class="icon-password"></i>
			<input type="password" id="password" name="password" class="password"/>
			<font>${error}</font>
			<p>验证码:</p>
			<input type="text" id="code" name="code" class="code"/>
			<img src="code" id="codeImage" class="codeImage"/><br/>
			<font>${codeError}</font>
			<div class="sub">
			<input type="checkbox" id="rember" class="rember"/>
			<label class="remberfont">记住用户名和密码</label>
			<input type="submit" class="refer" value="登陆"/>
			</div>
			</form>
			</div>
		</div>
		<!--login end -->
	</body>
</html>
