package com.controller.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commons.MyBatisUtils;
import com.entity.Orders;
import com.service.orders.OrdersService;
import com.service.orders.impl.OrdersServiceImpl;
@WebServlet("/Orders")
public class OrdersServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4363481486326768741L;
    private boolean isPass;
    private OrdersService os=new OrdersServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if("show".equals(op)) {
			//展示购物车信息
			showOrders(req,resp);
		}else if("add".equals(op)) {
			//添加购物车
			addOrders(req,resp);
		}
	}
	//添加购物车
	private void addOrders(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int bid=Integer.parseInt(req.getParameter("bid"));
			double count=1;//数量默认给1
			double curPrice=count*Double.parseDouble(req.getParameter("curprice"));
		    Date date=MyBatisUtils.getDate();
		    String uid=(String) req.getSession().getAttribute("name");
		    String oid=uid+MyBatisUtils.GetId();//用户id+时间
		    System.out.println("123:"+oid);
		    Orders orders=new Orders(oid, bid, count, curPrice, date, uid);
		    isPass=os.addOrders(orders);
		    PrintWriter out = resp.getWriter();
			if(isPass) {
				//删除成功
				out.write("true");
			}else {
				//删除失败
				out.write("false");
			}
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//展示购物车信息
	private void showOrders(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

}
