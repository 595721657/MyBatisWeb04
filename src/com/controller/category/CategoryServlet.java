package com.controller.category;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Category;
import com.service.category.CategoryService;
import com.service.category.impl.CategoryServiceImpl;
@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1532111482927200154L;
    private CategoryService cs=new CategoryServiceImpl();
    private List<Category> list;
    private boolean isPass;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收页面数据
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if("show".equals(op)) {
			//展示数据
			showCategory(req,resp);
		}else if("del".equals(op)) {
			//删除数据
			delCategory(req,resp);
		}else if("add".equals(op)) {
			//增加数据
			addCategory(req,resp);
		}
	}
	//增加数据
	private void addCategory(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//接收页面数据
			String name=req.getParameter("tname");
			isPass=cs.addCategory(name);
			if(isPass) {
				req.getRequestDispatcher("Category?op=show").forward(req, resp);
			}else {
				req.getRequestDispatcher("admin/admin-home.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//删除数据
	private void delCategory(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int cid=Integer.parseInt(req.getParameter("id"));
			isPass=cs.delCategory(cid);
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

	//展示数据
	private void showCategory(HttpServletRequest req, HttpServletResponse resp) {
		try {
			list=cs.getCategory();
			req.getSession().setAttribute("category", list);
			req.getRequestDispatcher("admin/category-mgr.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
