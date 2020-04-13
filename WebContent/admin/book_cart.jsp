<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
<!DOCTYPE HTML>
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>智远教育--图书网后台管理系统</h1></div>
    	<div id="info">${name }，您好！&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/User?op=login_out">登出</a></div>
    	<div class="menu">
    		<ul>
    			<li><a href="admin/admin-home.jsp">首页</a></li>
    			<li><a href="${pageContext.request.contextPath }/Category?op=show">图书分类管理</a></li>
    			<li><a href="${pageContext.request.contextPath }/Info_Admin?op=show">图书管理</a></li>
    			<li><a href="admin/book_cart.jsp">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">			
			<div class="section-left"></div>
			<div class="section-right"></div>						
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>