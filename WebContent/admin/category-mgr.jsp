<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>图书网后台管理系统</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/mgr.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>智远教育--图书网后台管理系统</h1>
		</div>
		<div id="info">${name }，您好！&nbsp;&nbsp;<a
				href="${pageContext.request.contextPath }/User?op=login_out">登出</a>
		</div>
		<div class="menu">
			<ul>
				<li><a href="admin/admin-home.jsp">首页</a></li>
    			<li><a href="${pageContext.request.contextPath }/Category?op=show">图书分类管理</a></li>
    			<li><a href="${pageContext.request.contextPath }/Info_Admin?op=show">图书管理</a></li>
    			<li><a href="admin/book_cart.jsp">购书订单管理</a></li>
			</ul>
		</div>
		<div id="main">
			<div class="section-left">
				<h2>图书分类信息</h2>
				<table class="table" cellspacing="0">
					<tr>
						<td class="header" width="200">图书分类</td>
						<td class="header" width="60">操作</td>
					</tr>
					<c:forEach items="${category }" var="cat">
						<tr>
							<td>${cat.category }</td>
							<td><a onclick="del('${cat.id}')" style="cursor: pointer">删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="section-right">
				<h2>添加分类信息</h2>
				<form action="${pageContext.request.contextPath }/Category?op=add"
					method="post">
					<p>
						分类名称：<input type="text" name="tname" /><input type="submit"
							value=" 保 存 " />
					</p>
				</form>
			</div>
			<div class="cf"></div>
		</div>
		<div id="footer">
			<p>版权所有&copy;智远教育</p>
		</div>
	</div>
	<script type="text/javascript">
		/* 使用AJAX提交删除方法 */
		function del(id) {
			var r = confirm("是否删除");
			if (r == true) {
				//使用Ajax来进行后台请求
				$(function() {
					$.post({
						url : "${pageContext.request.contextPath }/Category?op=del",//请求的servelt名称
						type : "post",
						data : "id=" + id,//传递的参数值
						dataType : "text",//后台响应的数据类型
						success : function(text) {
							//处理成功时响应数据的方法
							if (text.trim() == "true") {
								window.location.reload();
								alert("删除成功！！");
							} else {
								alert("删除失败！！");
							}
						}
					});
				})
			}
		}
	</script>
</body>
</html>
