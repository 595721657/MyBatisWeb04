package com.service.orders;

import java.util.List;

import com.entity.Orders;

public interface OrdersService {
	   //添加到购物车
	   boolean addOrders(Orders order);
	   //查询订单的所有
	   List<Orders> getAll(String uid);
}
