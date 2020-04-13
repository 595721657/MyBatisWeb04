package com.controller.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

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
    private List<Orders> list=null;
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
			//更新购物车
			addOrders(req,resp);
		}else if("update".equals(op)) {
			updateOrder(req,resp);
		}
	}
	//更新购物车
	private void updateOrder(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String oid = req.getParameter("oid");
			double count = Double.parseDouble(req.getParameter("count"));
			double price = Double.parseDouble(req.getParameter("price"));
			double curPrice = price*count;
			isPass=os.updateOrders(oid, count, curPrice);
			req.getSession().setAttribute("totlePrice", os.getAllPrice((String)req.getSession().getAttribute("name")));
			PrintWriter out = resp.getWriter();
			if(isPass) {
				//增加成功
				out.write("true");
			}else {
				//增加失败
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

	//添加购物车
	private void addOrders(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int bid=Integer.parseInt(req.getParameter("bid"));
			System.out.println("123:"+bid);
			double count=1;//数量默认给1
			double curPrice=count*Double.parseDouble(req.getParameter("curprice"));
		    Date date=MyBatisUtils.getDate();
		    String uid=(String) req.getSession().getAttribute("name");
		    String oid=uid+MyBatisUtils.GetId();//用户id+时间
		    list=os.getAll(uid,bid);
		    if(list.isEmpty()) {
		    	count=1;
			    Orders orders=new Orders(oid, bid, count, curPrice, date, uid);
		    	isPass=os.addOrders(orders);
		    }else {
		    	count=list.get(0).getCount()+1;
		    	double curprice=(list.get(0).getCurPrice()/list.get(0).getCount())*count;
		    	isPass=os.updateOrders(list.get(0).getOid(), count,curprice);
		    }		    
		    req.getSession().setAttribute("totlePrice", os.getAllPrice(uid));
		    PrintWriter out = resp.getWriter();
		    System.out.println("ispass:"+isPass);
			if(isPass) {
				//增加成功
				out.write("true");
			}else {
				//增加失败
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
		try {
			String userid=(String) req.getSession().getAttribute("name");
			list=os.getAll(userid,0);
			req.getSession().setAttribute("totlePrice", os.getAllPrice(userid));
			req.getSession().setAttribute("order", list);
			req.getRequestDispatcher("user/cart.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
