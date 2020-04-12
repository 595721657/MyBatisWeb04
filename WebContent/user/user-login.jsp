<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>智远图书网</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/styles.css" />
</head>
<body>
	<div id="container">
		<div id="header">
			<div id="tool-bar">
			<c:if test="${!empty name }" var="isOK">   		
    		&nbsp;&nbsp; 欢迎<mark style="color: red;">${name }</mark>光临智远图书网
    		</c:if>
    		<c:if test="${!isOK }">
    		 [<a href="user-login.jsp">请登录</a>]&nbsp;[<a href="user-regist.jsp">免费注册</a>]
    		</c:if>&nbsp;&nbsp;&nbsp;<a
					href="index.jsp">首页</a>&nbsp;|&nbsp;<a href="cart.jsp">购物车</a>&nbsp;|&nbsp;<a
					href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a>
			</div>
			<h1>
				智远图书网-<span
					style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span>
			</h1>
		</div>
		<div id="main">
			<div class="box" id="register">
				<div class="title">用户登录</div>
				<form action="${pageContext.request.contextPath }/User?op=login" method="post" style="margin: 10px;">
					<table cellspacing="0" class="no-border">
						<tr>
							<td style="text-align: right;">登录账号：</td>
							<td><input type="text" name="loginId" class="txt" value="" /></td>
						</tr>
						<tr>
							<td style="text-align: right;">登录密码：</td>
							<td><input type="password" name="loginPsw" class="txt"
								value="" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value=" 登  录 " class="btn" />&nbsp;&nbsp;</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>版权所有&copy;智远教育</p>
		</div>
	</div>
</body>
</html>