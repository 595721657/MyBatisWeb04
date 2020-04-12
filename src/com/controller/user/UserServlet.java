package com.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.entity.User;
import com.service.user.UserService;
import com.service.user.impl.UserServiceImpl;
@WebServlet("/User")
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7831566624293177936L;
    private UserService us=new UserServiceImpl();
    private boolean isPass;
    private int result;
    private User user=new User();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置编码格式
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if("login".equals(op)) {
			  //验证登录的方法
			  validationUser(req,resp);
		}else if("add".equals(op)) {
			  //添加用户
			  addUser(req,resp);
		}
	}
	//添加用户
	private void addUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String userId=req.getParameter("loginId");
			String userPsw=req.getParameter("loginPsw");
			String userName=req.getParameter("name");
			int role=Integer.parseInt(req.getParameter("role"));
			String code=req.getParameter("code");
			String new_psw=req.getParameter("reLoginPsw");
			//获取后台的code
			String new_code=(String)req.getSession().getAttribute("yzm");
			user=new User(userId, DigestUtils.md5Hex(userPsw), userName, role);
			//验证验证码和密码输入是否正确
			if(new_code.equals(code) && new_psw.equals(userPsw)) {
				isPass=us.saveUser(user);
				//判断是否添加到数据库
				if(isPass) {
					req.getSession().setAttribute("name", userId);
					req.getRequestDispatcher("Info?op=show").forward(req, resp);
				}else {
					req.getRequestDispatcher("user/user-regist.jsp").forward(req, resp);
				}
			}else {
				req.getRequestDispatcher("user/user-regist.jsp").forward(req, resp);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 //验证登录的方法
	private void validationUser(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("UTF-8");
			String userId=req.getParameter("loginId");
			String userPsw=req.getParameter("loginPsw");
			isPass=us.isValidation(userId, userPsw);
			if(isPass) {
				result=us.getByRole(userId);
				req.getSession().setAttribute("name", userId);
				if(result==1) {
					req.getRequestDispatcher("Info?op=show").forward(req, resp);
				}else {
					req.getRequestDispatcher("admin/admin-home.jsp").forward(req, resp);
				}
			}else {
				req.getRequestDispatcher("user/user-login.jsp").forward(req, resp);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
