package com.dao.orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Orders;

public interface OrdersMapper {
       //添加到购物车
	   int addOrders(Orders order);
	   //查询订单的所有
	   List<Orders> getAll(@Param("uid") String uid);
}
