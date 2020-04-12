<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>        
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/styles.css"/>
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
    		</c:if>&nbsp;&nbsp;&nbsp;<a href="index.jsp">首页</a>&nbsp;|&nbsp;<a href="cart.jsp">购物车</a>&nbsp;|&nbsp;<a href="#">我的订单</a>&nbsp;|&nbsp;<a href="#">帮助</a></div>
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>   
    	<form id="search-bar" action="${pageContext.request.contextPath }/Info?op=find_name" method="post">
	    		书名：<input type="text" class="txt" name="condition" />
	    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>   	
    	<div id="main">
    		<div class="section-left">
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>
    				<div class="box-content">
						<c:forEach items="${category }" var="ca">
						    <p>·<a href="${pageContext.request.contextPath }/Info?op=find_id&id=${ca.id}">${ca.category }</a></p>
						</c:forEach>   					 					
    				</div>
    			</div>
    		</div>
    		<div class="section-right">
    			<h3 align="center" style="margin-bottom: 20px; ">您选购的商品如下：</h3>
    			<table  align="center"  cellpadding="0" cellspacing="0">
    				<tr>
    					<td class="header" width="200">书名</td>
    					<td class="header"  width="60">单价</td>
    					<td class="header"  width="60">购物数量</td>
    					<td class="header"  width="60">小计</td>
    				</tr>
    				<tr>
    					<td>《红楼小讲》</td>
    					<td>￥15.0</td>
    					<td>7 本</td>
    					<td>￥105.0</td>
    				</tr>
    				<tr>
    					<td>《尸鬼》</td>
    					<td>￥170.0</td>
    					<td>1 本</td>
    					<td>￥170.0</td>
    				</tr>
    				<tr>
    					<td colspan="4" class="header" style="text-align: right;">总计：￥275.0</td>
    				</tr>
    			</table>	
    			<div style="text-align: center; font-size: 14px; line-height: 40px;">
    				<a href="#" style="text-decoration: underline;">去结账</a>
    			</div>
    		</div>
    		<div class="cf"></div>   	
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
  <script type="text/javascript">
		/* 使用AJAX提交增加方法 */
		function add() {
				//使用Ajax来进行后台请求
				$(function() {
					$.post({
						url : "Category?op=del",//请求的servelt名称
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
	</script>
</html>