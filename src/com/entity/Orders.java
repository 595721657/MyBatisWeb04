package com.entity;
/**
 * 订单实体
 * @author 黄龙
 * @@创建时间 2020年4月10日上午10:17:51
 */

import java.util.Date;

public class Orders {
       private String oid;
       private int bid;
       private double count;
       private double curPrice;
       private Date date;
       private String uid;
       private Info info;      
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(double curPrice) {
		this.curPrice = curPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Orders(String oid, int bid, double count, double curPrice, Date date, String uid) {
		super();
		this.oid = oid;
		this.bid = bid;
		this.count = count;
		this.curPrice = curPrice;
		this.date = date;
		this.uid = uid;
	}
	public Orders() {
		super();
	}
}
