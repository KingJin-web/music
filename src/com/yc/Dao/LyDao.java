package com.yc.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.common.util.DBHelper;

public class LyDao {

	public void addly(String name,String content) throws SQLException {
		String sql="insert into sq_ly values(null,?,?,null,now())";
		DBHelper dbh=new DBHelper();
		dbh.update(sql, name,content);
	}
	public List<Map<String, Object>> listly() throws SQLException{
		String sql="select * from sq_ly";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql);
		
	}
}
