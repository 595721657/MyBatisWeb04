package com.dao.user;

import org.apache.ibatis.annotations.Param;

import com.entity.User;

public interface UserMapper {
       //定义一个用户登录的方法
	   int isValidation(@Param("userid")String userId,@Param("psw") String userpsw);
	   //定义一个用户注册的方法
	   int saveUser(User user);
	   //定义一个通过id查询数据的方法 返回role
	   int  getByRole(@Param("id") String userid);
}
