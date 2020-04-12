package com.service.info;

import java.util.List;

import com.entity.Info;

public interface InfoService {
	 int getCount(String name,int categoryId);
     //通过书名模糊查询分页的方法多条件筛选
     List<Info> getAll(int currPage,int pageSize,String name,int categoryId);
     //通过类别或者id查询数据
	 List<Info> getByCid(int id,int cid);
	 //通过类别或者id删除数据
	 boolean delInfo(int id,int cid);
	 //增加数据
	 boolean addInfo(Info info);
	 //修改数据
	 boolean updateInfo(Info info);
}
