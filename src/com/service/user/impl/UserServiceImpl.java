package com.service.user.impl;

import org.apache.ibatis.session.SqlSession;

import com.commons.MyBatisUtils;
import com.dao.user.UserMapper;
import com.entity.User;
import com.service.user.UserService;

public class UserServiceImpl implements UserService {
    private SqlSession sqlsession;
    //private boolean isPass;
    private int result;
    private UserMapper mapper;
    //验证登录
	@Override	
	public boolean isValidation(String userId, String userpsw) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(UserMapper.class);
		result=mapper.isValidation(userId,userpsw);
		if(result>0) {
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			MyBatisUtils.closeSqlSession(sqlsession);
			return false;
		}
	}
    //添加用户
	@Override
	public boolean saveUser(User user) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(UserMapper.class);
		result=mapper.saveUser(user);
		if(result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			sqlsession.rollback();
			MyBatisUtils.closeSqlSession(sqlsession);
			return false;
		}
	}
	//查询用户角色
	@Override
	public int getByRole(String userid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(UserMapper.class);
		result=mapper.getByRole(userid);
		MyBatisUtils.closeSqlSession(sqlsession);
		return result;
	}

}
