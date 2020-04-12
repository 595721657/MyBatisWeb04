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
    	<div id="info">${name }，您好！&nbsp;&nbsp;<a href="admin-user-login.html">登出</a></div>
    	<div class="menu">
    		<ul>
    			<li><a href="admin/admin-home.jsp">首页</a></li>
    			<li><a href="admin/category-mgr.jsp">图书分类管理</a></li>
    			<li><a href="admin/book-mgr.jsp">图书管理</a></li>
    			<li><a href="user/cart.jsp">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">
			<div class="section-left">    	
				<h2>编辑图书信息</h2>
				<c:forEach items="${info_list}" var="inf">
					<form action="${pageContext.request.contextPath }/Info_Admin?op=update" method="post" enctype="Multipart/form-data">
						<input type="hidden" name="id" value="${inf.id }" />
						<input type="hidden" name="pid" value="${inf.photo }" />
						<p>图书书名：<input type="text" name="bookName" value="${inf.bookName }"  /></p>
						<p>图书作者：<input type="text" name="author" value="${inf.author}"  /></p>
						<p>图书分类：
							<select name="categoryId">		
								<c:forEach items="${category }" var="cat">
									<c:if test="${cat.id==inf.categoryId }" var="isOK">
										  <option value="${inf.category.id}" selected="selected">${inf.category.category}</option>	
									</c:if>
									<c:if test="${!isOK }">
									      <option value="${cat.id}">${cat.category}</option>	
									</c:if>
							    </c:forEach>
						    </select>
						</p>
						<p>图书售价：<input type="text" name="price" value="${inf.price }" /></p>
						<p>图书出版社：<input type="text" name="publisher" value="${inf.publisher }"  /></p>  
						<p>当前图片：<img width="150" height="90" src="${inf.photo }" /></p> 
						<p>图书图片：<input type="file" name="photo"  /></p>    				 				
						<p><input type="submit" value=" 修 改 "  />&nbsp;</p>					
					</form>
				</c:forEach>
			</div>
			<div class="section-right"></div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>
