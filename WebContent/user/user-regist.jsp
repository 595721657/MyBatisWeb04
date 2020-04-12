<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>	
<!DOCTYPE HTML>
<html>
<head>
<title>智远图书网</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/styles.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery-3.4.1.min.js"></script>
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
				<div class="title">新用户注册</div>
				<form action="${pageContext.request.contextPath }/User?op=add"
					method="post" style="margin: 10px;">
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
							<td style="text-align: right;">再次输入密码：</td>
							<td><input type="password" name="reLoginPsw" class="txt"
								value="" /></td>
						</tr>
						<tr>
							<td style="text-align: right;">真实姓名：</td>
							<td><input type="text" name="name" class="txt" value="" /></td>
						</tr>
						<tr>
						   <td style="text-align: right;">级别：</td>
						   <td>
						     <select id="second" name="role">
							      <option value="1">用户</option>
							      <option value="2">管理员</option>
							 </select>
						   </td>
						</tr>
						<tr>
							<td style="text-align: right;">验证码：</td>
							<td><input type="text" name="code" class="txt" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><img id="codeImg" src=""/>&nbsp;&nbsp;看不清？<a
								href="javascript:changeImg();" style="color: #64A26F;">换张图</a></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value=" 注  册 " class="btn" />&nbsp;&nbsp;</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>版权所有&copy;智远教育</p>
		</div>
	</div>
	<script type="text/javascript">
	 $(function(){
	    	changeImg();
	    	/* $("input[type='submit']").click(function(){
	    		var x=$("input[name='Adminname']").val();
	    		var y=$("input[name='Adminpwd']").val();
	    		var z=$("input[name='code']").val();
	    		if(x=="" || y=="" || z==""){
	    		  alert("不能为空");
	    		}
	    	}) */
	     });
		function changeImg() {
			// 获得验证码图片元素 使用JavaScript获得
			var img = document.getElementById("codeImg");
			// 当src的路径发生改变，都会到后台去请求一次
			// new Date().getTime() 避免浏览器不去后台请求数据，因为有缓存
			img.src="${pageContext.request.contextPath}/CodeImg?"+new Date().getTime();
		}	
	</script>
</body>
</html>