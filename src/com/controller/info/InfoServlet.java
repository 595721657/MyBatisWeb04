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
		}
	}
	//展示全部数据
	@SuppressWarnings("unchecked")
	private void showInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//获取页码
			String pageIndex=req.getParameter("pageIndex");
			String id=req.getParameter("id");
			String bookName=req.getParameter("condition");
			int	new_id=0;
			if(id==null || "".equals(id)) {
				new_id=0;
			}else {
				new_id=Integer.parseInt(id);
			}
			int currpage=1; 
			//获取数据总条数
			int totalCount=ifs.getCount(bookName, new_id);
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
			List lists=ifs.getAll(form, pg.getPageSize(), bookName, new_id);
			pg.setPageLists(lists);
			req.getSession().setAttribute("category", cs.getCategory());
			req.getSession().setAttribute("pg", pg);
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
