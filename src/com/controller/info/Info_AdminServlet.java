package com.controller.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.entity.Info;
import com.service.info.InfoService;
import com.service.info.impl.InfoServiceImpl;
@WebServlet("/Info_Admin")
public class Info_AdminServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7848164917448042942L;
	private InfoService ifs=new InfoServiceImpl();
	//private CategoryService cs=new CategoryServiceImpl();
	private List<Info> list;
	private boolean isPass;
	private Info infos;
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
			//展示所有的图书信息
			showInfo(req,resp);
		}else if("del".equals(op)) {
			//删除图书信息
			delInfo(req,resp);
		}else if("find".equals(op)) {
			//通过id查询图书信息
			findInfo(req,resp);
		}else if("update".equals(op)) {
			//通过id修改图书信息
			updateInfo(req,resp);
		}else if("add".equals(op)) {
			//增加图书信息
			addInfo(req,resp);
		}else if("show_other".equals(op)) {
			//增加图书信息
			show_otherInfo(req,resp);
		}
	}
	//展示admin_home数据
	private void show_otherInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			/*
			 * List<Category> lists=cs.getCategory();
			 * req.getSession().setAttribute("category", lists);
			 */
			req.getRequestDispatcher("admin/admin-home.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//增加图书信息
	private void addInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String bookName=null; 
			String author=null;
			String categoryId=null;
			String publisher=null; 
			String price=null;
			String photo=null;
			// 设置上传的文件路径
			String filePath = this.getServletContext().getRealPath("/static/file");
			// 验证表单是否是采用的Multipart/form-data的格式进行文件上传 enctype的值
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			// 判断是否是采用的二进制文件流的形式做文件上传
			if(isMultipart) {
				// 创建一个用于文件上传的工厂对象
				FileItemFactory fac = new DiskFileItemFactory();
				// 利用工厂对象创建一个用于解析文件上传的对象
				ServletFileUpload upload = new ServletFileUpload(fac);
				try {
					// 使用文件上传对象来获得表单中的所有请求
					List<FileItem> items = upload.parseRequest(req);
					// 遍历整个集合 Iterator
					Iterator<FileItem> it = items.iterator();
					// 遍历整个的items集合
					while (it.hasNext()) {// 集合中是否有元素
						// 获得表单中的元素
						FileItem item = it.next();// 取出集合中元素
						// getFieldName() 获得表单的name值
						//System.out.println(item.getFieldName());
						// 判断比表单中的元素是上传元素表单还是普通文本表单
						if(item.isFormField()) {// 它是一个普通表单
							String name = item.getFieldName();// 得到表单的name值
							// 根据name值，为上面的变量赋值
							switch (name) {
							case "bookName":
								bookName = item.getString("UTF-8");
								break;
							case "author":
								author = item.getString("UTF-8");
								break;
							case "categoryId":
								categoryId = item.getString("UTF-8");
								break;
							case "publisher":
								publisher = item.getString("UTF-8");
								break;
							case "price":
								price = item.getString("UTF-8");
								break;
							}
						}else {
							// 它是上传元素表单
							// 保存上传文件的名称
							photo = item.getName();
							// 生成一个随机的唯一标识值
							UUID rand = UUID.randomUUID();
							photo=rand+photo;
							// 创建一个文件对象，来保存这个要上传的文件内容
							File saveFile = new File(filePath, photo);
							// 做文件上传
							// 调用item对象的write方法，将文件写入到服务器
							item.write(saveFile);
							photo="static/file/"+photo;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				infos=new Info(bookName, author, Integer.parseInt(categoryId), publisher, Double.parseDouble(price), photo);
			    isPass=ifs.addInfo(infos);
			    if(isPass) {
			    	//添加成功
			    	resp.sendRedirect("Info_Admin?op=show");
			    }else {
			    	resp.sendRedirect("admin/admin-home.jsp");
			    }
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//通过id修改图书信息
	private void updateInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id=null;
			String bookName=null; 
			String author=null;
			String categoryId=null;
			String publisher=null; 
			String price=null;
			String photo=null;
			// 设置上传的文件路径
			String filePath = this.getServletContext().getRealPath("/static/file");
			// 验证表单是否是采用的Multipart/form-data的格式进行文件上传 enctype的值
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			// 判断是否是采用的二进制文件流的形式做文件上传
			if(isMultipart) {
				// 创建一个用于文件上传的工厂对象
				FileItemFactory fac = new DiskFileItemFactory();
				// 利用工厂对象创建一个用于解析文件上传的对象
				ServletFileUpload upload = new ServletFileUpload(fac);
				try {
					// 使用文件上传对象来获得表单中的所有请求
					List<FileItem> items = upload.parseRequest(req);
					// 遍历整个集合 Iterator
					Iterator<FileItem> it = items.iterator();
					// 遍历整个的items集合
					while (it.hasNext()) {// 集合中是否有元素
						// 获得表单中的元素
						FileItem item = it.next();// 取出集合中元素
						// getFieldName() 获得表单的name值
						//System.out.println(item.getFieldName());
						// 判断比表单中的元素是上传元素表单还是普通文本表单
						if(item.isFormField()) {// 它是一个普通表单
							String name = item.getFieldName();// 得到表单的name值
							// 根据name值，为上面的变量赋值
							switch (name) {
							case "id":
								id = item.getString("UTF-8");
								break;
							case "bookName":
								bookName = item.getString("UTF-8");
								break;
							case "author":
								author = item.getString("UTF-8");
								break;
							case "categoryId":
								categoryId = item.getString("UTF-8");
								break;
							case "publisher":
								publisher = item.getString("UTF-8");
								break;
							case "price":
								price = item.getString("UTF-8");
								break;
							}
						}else {
							// 它是上传元素表单
							// 保存上传文件的名称
							photo = item.getName();
							// 生成一个随机的唯一标识值
							UUID rand = UUID.randomUUID();
							photo=rand+photo;
							// 创建一个文件对象，来保存这个要上传的文件内容
							File saveFile = new File(filePath, photo);
							// 做文件上传
							// 调用item对象的write方法，将文件写入到服务器
							item.write(saveFile);
							photo="static/file/"+photo;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				infos=new Info(Integer.parseInt(id),bookName, author, Integer.parseInt(categoryId), publisher, Double.parseDouble(price), photo);
			    isPass=ifs.updateInfo(infos);
			    if(isPass) {
			    	//添加成功
			    	resp.sendRedirect("Info_Admin?op=show");
			    }else {
			    	resp.sendRedirect("admin/admin-edit.jsp");
			    }
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//通过id查询图书信息
	private void findInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			list=ifs.getByCid(id,0);
			req.getSession().setAttribute("info_list", list);
			req.getRequestDispatcher("admin/book-edit.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//删除图书信息
	private void delInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			isPass=ifs.delInfo(id,0);
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

	//展示所有的图书信息
	private void showInfo(HttpServletRequest req, HttpServletResponse resp) {
		try {
			list=ifs.getByCid(0,0);
			req.getSession().setAttribute("info", list);
			req.getRequestDispatcher("admin/book-mgr.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
