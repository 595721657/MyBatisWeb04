<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>        
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/styles.css"/>
   <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.4.1.min.js"></script>
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
    				    <td class="header" width="200">编号</td>
    					<td class="header" width="100">书名</td>
    					<td class="header"  width="60">单价</td>
    					<td class="header"  width="160">购物数量</td>
    					<td class="header"  width="60">小计</td>
    				</tr>
    				<c:forEach items="${order }" var="ord">
	    				  <tr class="calcTr">
	    				    <td>${ord.oid }</td>
	    					<td>${ord.info.bookName } </td>
	    					<td>￥${ord.info.price } </td>
	    					<td><button onclick="del_book(this)">-</button><input type="text" value="${ord.count }" style="width: 18px;"><button onclick="add(this)">+</button> 本</td>
	    					<td>￥${ord.curPrice } </td>
	    				  </tr>
    				</c:forEach>
    				<tr>
    					<td colspan="5" class="header" style="text-align: right;">总计：￥${totlePrice }</td>
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
		<script type="text/javascript">
			$(function(){
				//calc();
			});
			//增加数量
			function add(obj) {
				// 获得需要修改的值
				var count = $(obj).prev().val();
				count++;
				$(obj).prev().val(count);
				// 获得更新的订单号
				var oid = $(obj).parents("tr").find("td").eq(0).text();
				var price = parseFloat($(obj).parents("tr").find("td").eq(2).text().substring(1));
				// 使用ajax去实现数据的更新
				$.post({
					url : "Orders?op=update",//请求的servelt名称
					type : "post",
					data : "oid=" + oid+"&price="+price+"&count="+count,//传递的参数值
					dataType : "text",//后台响应的数据类型
					success : function(text) {
						//处理成功时响应数据的方法
						if (text.trim() == "true") {	
							window.location.reload();
							alert("更新成功！！");
						} else {
							alert("更新失败！！");
						}
					}
				});
			}
			//减少数量
			function del_book(obj) {
				// 获得需要修改的值
				var count = $(obj).next().val();
				count--;
				if(count==0){
					count=1;
				}
				$(obj).next().val(count);
				// 获得更新的订单号
				var oid = $(obj).parents("tr").find("td").eq(0).text();
				var price = parseFloat($(obj).parents("tr").find("td").eq(2).text().substring(1));
				// 使用ajax去实现数据的更新
				$.post({
					url : "Orders?op=update",//请求的servelt名称
					type : "post",
					data : "oid=" + oid+"&price="+price+"&count="+count,//传递的参数值
					dataType : "text",//后台响应的数据类型
					success : function(text) {
						//处理成功时响应数据的方法
						if (text.trim() == "true") {	
							window.location.reload();
							alert("更新成功！！");
						} else {
							alert("更新失败！！");
						}
					}
				});
			}
			//统计总价
			function calc() {
				
			}
		</script>
  </body>
</html>