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
	public List<Orders> getAll(String uid,int bid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(OrdersMapper.class);
		list=mapper.getAll(uid,bid);
		MyBatisUtils.closeSqlSession(sqlsession);
		return list;
	}
    //修改数据
	@Override
	public boolean updateOrders(String oid, double id,double curpirce) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(OrdersMapper.class);
		result=mapper.updateOrders(oid, id,curpirce);
		if(result>0) {
			sqlsession.commit();
			MyBatisUtils.closeSqlSession(sqlsession);
			return true;
		}else {
			return false;
		}
	}
    //计算用户总价
	@Override
	public double getAllPrice(String oid) {
		sqlsession=MyBatisUtils.createSqlSession();
		mapper=sqlsession.getMapper(OrdersMapper.class);
		double price=mapper.getAllPrice(oid);
		MyBatisUtils.closeSqlSession(sqlsession);
		return price;
	}

}
