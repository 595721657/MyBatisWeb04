package com.service.category.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.commons.MyBatisUtils;
import com.dao.category.CategoryMapper;
import com.entity.Category;
import com.entity.Info;
import com.service.category.CategoryService;
import com.service.info.InfoService;
import com.service.info.impl.InfoServiceImpl;

public class CategoryServiceImpl implements CategoryService {
    private SqlSession sqlsession;
    private List<Category> list;
    private CategoryMapper mapper;
    private int result;
    private boolean isPass;
    private InfoService info=new InfoServiceImpl();
    //查询所有
	@Override
	public List<Category> getCategory() {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(CategoryMapper.class);
		list=mapper.getCategory();
		MyBatisUtils.closeSqlSession(sqlsession);
		return list;
	}
	//删除数据
	@Override
	public boolean delCategory(int id) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(CategoryMapper.class);
		List<Info> lists=info.getByCid(0,id);
		//先判断子表是否有数据
		if(lists.isEmpty()) {
			//没有直接删除
			result=mapper.delCategory(id);
			if(result>0) {
				sqlsession.commit();
				MyBatisUtils.closeSqlSession(sqlsession);
				return true;
			}else {
			    return false;
			}
		}else {	
				//先删子表
			    isPass=info.delInfo(0,id);
			    result=mapper.delCategory(id);
			    //再删主表
			    if(isPass && result>0) {
					sqlsession.commit();
					MyBatisUtils.closeSqlSession(sqlsession);
					return true;
				}else {
				    return false;
				}
		}
	}
	//增加数据
	@Override
	public boolean addCategory(String name) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(CategoryMapper.class);
		result=mapper.addCategory(name);
		if(result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			return false;
		}
	}

}
