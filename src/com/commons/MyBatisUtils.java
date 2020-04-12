package com.commons;
/**
 * 负责读取配置文件 创建执行对象 关闭执行对象
 * MyBatis工具类
 * @author 黄龙
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	   private static SqlSessionFactory fac;
       //书写一个静态代码块，加载配置文件
	   static {
		  try {
			//读取核心配置文件
			InputStream is= Resources.getResourceAsStream("mybatis-config.xml");
		    fac=new SqlSessionFactoryBuilder().build(is);   
		  } catch (IOException e) {
			e.printStackTrace();
		}
	 }
	   //创建一个sqlsession对象
	   public static SqlSession createSqlSession() {
		   //openSession方法中的参数
		   //如果不是true，它表示事务开启
		   //false 它表示事务关闭
		   SqlSession sqlsession=fac.openSession(false);
		   return sqlsession;
	   }
	   //创建一个关闭sqlsession的方法
	   public static void closeSqlSession(SqlSession sqlsession) {
		   if(sqlsession!=null) {
			   sqlsession.close();
		   }
	   }
	    // 定义一个专门负责日期格式转换的对象
		static Date  date = new Date();
		static String time_id="";
		static SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
	    public static String GetId() {	
	    	String id[]=format.format(date).split("-");
			for (int i = id.length-1; i>=0; i--) {
				time_id=id[i]+time_id;
			}   
			return time_id;
		}
	    //获得系统当前时间的方法
	    public static Date getDate() {   
			return date;   	
	    }
}
