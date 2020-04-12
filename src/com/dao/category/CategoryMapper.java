package com.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Category;

public interface CategoryMapper {
        //查询所有数据
	   List<Category> getCategory();
	   //删除数据
	   int delCategory(@Param("id") int id);
	   //添加数据
	   int addCategory(@Param("name") String name);
}
