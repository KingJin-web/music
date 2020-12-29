package com.yc.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.common.util.DBHelper;

public class SearchDao {

	public List<Map<String, Object>> listname(String name) throws SQLException{
		String sql="select * from sq_song where singer=?";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql, name);
	}
	
	public List<Map<String, Object>> listsong(String name) throws SQLException{
		String sql="select * from sq_song where name=?";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql, name);
	}
	
}
