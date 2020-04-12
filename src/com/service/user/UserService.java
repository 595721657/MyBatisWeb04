package com.service.user;

import com.entity.User;

public interface UserService {
	   //定义一个用户登录的方法
	   boolean isValidation(String userId,String userpsw);
	   //定义一个用户注册的方法
	   boolean saveUser(User user);
	   //定义一个通过id查询数据的方法 返回role
	   int  getByRole(String userid);
}
