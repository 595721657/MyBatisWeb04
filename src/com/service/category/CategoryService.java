package com.service.category;

import java.util.List;

import com.entity.Category;

public interface CategoryService {
       //查询全部
	   List<Category> getCategory();
	   //删除数据
	   boolean delCategory(int cid);
	   //添加数据
	   boolean addCategory(String name);
}
