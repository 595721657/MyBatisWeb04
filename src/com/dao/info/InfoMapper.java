package com.dao.info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Info;

public interface InfoMapper {
     //查询数据库条数多条件筛选
     int getCount(@Param("name") String name,@Param("id") int categoryId);
     //通过书名模糊查询分页的方法多条件筛选
     List<Info> getAll(@Param("form") int currPage,@Param("pageSize") int pageSize,@Param("name") String name,@Param("id") int categoryId);
     //通过多条件删除
	 int delInfo(@Param("id") int id,@Param("cid") int cid);
	 //通过类别删除数据
	 List<Info> getByCid(@Param("id") int id,@Param("cid") int cid);
	 //增加数据
	 int addInfo(Info info);
	 //修改数据
	 int updateInfo(Info info);
}
