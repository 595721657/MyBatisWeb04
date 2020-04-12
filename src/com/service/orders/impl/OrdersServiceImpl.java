package com.service.orders.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.commons.MyBatisUtils;
import com.dao.orders.OrdersMapper;
import com.entity.Orders;
import com.service.orders.OrdersService;

public class OrdersServiceImpl implements OrdersService {
    private OrdersMapper mapper;
    private int result;
    private List<Orders> list;
    private SqlSession sqlsession;
	@Override
	public boolean addOrders(Orders order) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(OrdersMapper.class);
		result=mapper.addOrders(order);
		if (result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Orders> getAll(String uid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(OrdersMapper.class);
		list=mapper.getAll(uid);
		MyBatisUtils.closeSqlSession(sqlsession);
		return list;
	}

}
