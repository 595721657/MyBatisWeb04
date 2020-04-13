<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    		 [<a href="user-login.jsp">请登录</a>]&nbsp;[<a
						href="user-regist.jsp">免费注册</a>]
    		</c:if>
				&nbsp;&nbsp;&nbsp;<a href="user/index.jsp">首页</a>&nbsp;|&nbsp;<a
					href="${pageContext.request.contextPath }/Orders?op=show">购物车</a>&nbsp;|&nbsp;<a href="${pageContext.request.contextPath }/Orders?op=show">我的订单</a>&nbsp;|&nbsp;<a
					href="#">帮助</a>
			</div>
			<h1>
				智远图书网-<span
					style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span>
			</h1>
		</div>
		<form id="search-bar"
			action="${pageContext.request.contextPath }/Info?op=show&pageIndex=1"
			method="post">
			书名：<input type="text" class="txt" name="condition" /> <input
				id="search-btn" type="submit" value=" 搜索图书 " />
		</form>
		<div id="main">
			<div class="section-left">
				<div class="box-left">
					<div class="box-title">分类畅销榜</div>
					<div class="box-content">
						<c:forEach items="${category }" var="ca">
							<p>
								·<a href="${pageContext.request.contextPath }/Info?op=show&pageIndex=1&id=${ca.id}">${ca.category }</a>
							</p>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="section-right">
				<div class="box-right">
					<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
					<div class="paging"
						style="border-bottom: 1px solid #64A26F; color: #006666;">
						<span class="fr"><a href="#">首页</a>&nbsp;<a href="#">上一页</a>&nbsp;<a
							href="#">下一页</a>&nbsp;<a href="#">尾页</a>&nbsp;</span>
						共有图书${pg.totalCount }种，分50页显示，每页显示20个商品
					</div>
					<c:forEach items="${pg.pageLists}" var="bo">
						<div class="box-item">
							<div class="img-box">
								<img src="${pageContext.request.contextPath }/${bo.photo }" />
							</div>
							<div class="info-box">
								<span style="font-size: 14px;"><a href="#">${bo.bookName }</a></span><br />
								作者：${bo.author }&nbsp;&nbsp;著<br /> 分类：${bo.category.category }<br />
								出版社：${bo.publisher }<br /> 售价：￥<span style="color: #990000;" class="book_price">${bo.price }</span>
								<br />
							</div>
							<a onclick="add('${bo.id}')" style="cursor:pointer" class="btn-buy">购&nbsp;&nbsp;买</a>
							<div class="cf"></div>
						</div>
					</c:forEach>
					<div class="paging">
							<span class="fr">
							【第${pg.currPage }页/共${pg.totalPages}页】
							<a href="${pageContext.request.contextPath }/Info?op=show&pageIndex=1">首页</a>&nbsp;
							<a href="${pageContext.request.contextPath }/Info?op=show&pageIndex=${pg.currPage-1 }">上一页</a>&nbsp;
							<a href="${pageContext.request.contextPath }/Info?op=show&pageIndex=${pg.currPage+1 }">下一页</a>&nbsp;
							<a href="#">尾页</a>&nbsp;</span>
					</div>
				</div>
			</div>
			<div class="cf"></div>
		</div>
		<div id="footer">
			<p>版权所有&copy;智远教育</p>
		</div>
	</div>
	<script type="text/javascript">
    	/* 使用AJAX提交删除方法 */
		function add(id) {
				//使用Ajax来进行后台请求
				$(function() {
					var bid=id;//图书id
			    	var currprice=$(".book_price").html();//单价
					$.post({
						url : "Orders?op=add",//请求的servelt名称
						type : "post",
						data : "bid=" + bid+"&curprice="+currprice,//传递的参数值
						dataType : "text",//后台响应的数据类型
						success : function(text) {
							//处理成功时响应数据的方法
							if (text.trim() == "true") {	
								alert("增加成功！！");
								window.open("${pageContext.request.contextPath }/Orders?op=show");
							} else {
								alert("增加失败！！");
							}
						}
					});
				})
		}
	</script>
</body>
</html>