package com.service.info.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.commons.MyBatisUtils;
import com.dao.info.InfoMapper;
import com.entity.Info;
import com.service.info.InfoService;

public class InfoServiceImpl implements InfoService {
    private SqlSession sqlsession;
    private InfoMapper mapper;
    private List<Info> list;
    private int result;
    //查询数据库条数(多条件)
	@Override
	public int getCount(String name, int categoryId) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		result=mapper.getCount(name, categoryId);
		MyBatisUtils.closeSqlSession(sqlsession);
		return result;
	}
	//查询数据库分页数据(多条件)
	@Override
	public List<Info> getAll(int currPage, int pageSize, String name, int categoryId) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		list=mapper.getAll(currPage, pageSize, name, categoryId);
		MyBatisUtils.closeSqlSession(sqlsession);
		return list;
	}
	//通过类别查询数据
	@Override
	public List<Info> getByCid(int id,int cid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		list=mapper.getByCid(id,cid);
		MyBatisUtils.closeSqlSession(sqlsession);
		return list;
	}
    //通过类别删处数据
	@Override
	public boolean delInfo(int id,int cid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		result=mapper.delInfo(id,cid);
		if (result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			return false;
		  }
	}
	//增加数据
	@Override
	public boolean addInfo(Info info) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		result=mapper.addInfo(info);
		if (result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			return false;
		  }
	}
	//修改数据
	@Override
	public boolean updateInfo(Info info) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(InfoMapper.class);
		result=mapper.updateInfo(info);
		if (result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			return false;
		  }
	}
}
