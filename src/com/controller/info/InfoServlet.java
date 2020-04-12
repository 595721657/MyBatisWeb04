package com.controller.info;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Pager;
import com.service.category.CategoryService;
import com.service.category.impl.CategoryServiceImpl;
import com.service.info.InfoService;
import com.service.info.impl.InfoServiceImpl;
@WebServlet("/Info")
public class InfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4675506854550492717L;
    private InfoService ifs=new InfoServiceImpl();
    //private int result;
    private Pager pg=new Pager();
    private CategoryService cs=new CategoryServiceImpl();
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
			//展示全部数据
			showInfo(req,resp);
		}else if("find_id".equals(op)){
			//通过类别便号查询数据
			find_idInfo(req,resp);
		}else if("find_name".equals(op)){
			//通过书名模糊查询查询数据
			find_nameInfo(req,resp);
		}
	}
	//通过书名模糊查询查询数据
	@SuppressWarnings("unchecked")
	private void find_nameInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
    		//接收id
        	String name=req.getParameter("condition");
			//获取页码
			String pageIndex=req.getParameter("pageIndex");
			int currpage=1; 
			//获取数据总条数
			int totalCount=ifs.getCount(name, 0);
			pg.setTotalCount(totalCount);
			if(pageIndex==null || "".equals(pageIndex)) {
				currpage=1;
			}else {
				int num=Integer.parseInt(pageIndex);
				if(num<=0) {
					currpage=1;
				}else if(num>=pg.getTotalPages()) {
					currpage=pg.getTotalPages();
				}else {
					currpage=num;
				}
			}
			pg.setCurrPage(currpage);
			int form=(currpage-1)*pg.getTotalPages();
			@SuppressWarnings("rawtypes")
			List lists=ifs.getAll(form, pg.getPageSize(), name, 0);
			pg.setPageLists(lists);
			req.getSession().setAttribute("pg", pg);
			req.getSession().setAttribute("option", "name");
			req.getRequestDispatcher("user/index.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//通过类别便号查询数据
    @SuppressWarnings("unchecked")
	private void find_idInfo(HttpServletRequest req, HttpServletResponse resp) {
    	try {
    		//接收id
        	int id=Integer.parseInt(req.getParameter("id"));
			//获取页码
			String pageIndex=req.getParameter("pageIndex");
			int currpage=1; 
			//获取数据总条数
			int totalCount=ifs.getCount(null, id);
			pg.setTotalCount(totalCount);
			if(pageIndex==null || "".equals(pageIndex)) {
				currpage=1;
			}else {
				int num=Integer.parseInt(pageIndex);
				if(num<=0) {
					currpage=1;
				}else if(num>=pg.getTotalPages()) {
					currpage=pg.getTotalPages();
				}else {
					currpage=num;
				}
			}
			pg.setCurrPage(currpage);
			int form=(currpage-1)*pg.getTotalPages();
			@SuppressWarnings("rawtypes")
			List lists=ifs.getAll(form, pg.getPageSize(), null, id);
			pg.setPageLists(lists);
			req.getSession().setAttribute("pg", pg);
			req.getSession().setAttribute("option", "id");
			req.getRequestDispatcher("user/index.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//展示全部数据
	@SuppressWarnings("unchecked")
	private void showInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//获取页码
			String pageIndex=req.getParameter("pageIndex");
			int currpage=1; 
			//获取数据总条数
			int totalCount=ifs.getCount(null, 0);
			pg.setTotalCount(totalCount);
			if(pageIndex==null || "".equals(pageIndex)) {
				currpage=1;
			}else {
				int num=Integer.parseInt(pageIndex);
				if(num<=0) {
					currpage=1;
				}else if(num>=pg.getTotalPages()) {
					currpage=pg.getTotalPages();
				}else {
					currpage=num;
				}
			}
			pg.setCurrPage(currpage);
			int form=(currpage-1)*pg.getTotalPages();
			@SuppressWarnings("rawtypes")
			List lists=ifs.getAll(form, pg.getPageSize(), null, 0);
			pg.setPageLists(lists);
			req.getSession().setAttribute("category", cs.getCategory());
			req.getSession().setAttribute("pg", pg);
			req.getSession().setAttribute("option", "show");
			req.getRequestDispatcher("user/index.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
