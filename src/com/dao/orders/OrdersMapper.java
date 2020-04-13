package com.dao.orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Orders;

public interface OrdersMapper {
       //添加到购物车
	   int addOrders(Orders order);
	   //查询订单的所有
	   List<Orders> getAll(@Param("uid") String uid,@Param("bid") int bid);
	   ///修改订单数据
	   int updateOrders(@Param("oid") String oid,@Param("id") double id,@Param("curprice") double curpirce);
       //查询用户的总价
	   double getAllPrice(@Param("oid") String oid);
}
