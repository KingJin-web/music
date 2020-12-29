package com.yc.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yc.common.util.DBHelper;

public class DetailDao {

	public List<Map<String, Object>> listdetail(String name) throws SQLException{
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	sq_singer a\n" +
				"LEFT JOIN (\n" +
				"	SELECT\n" +
				"		singer,\n" +
				"		count(NAME) total\n" +
				"	FROM\n" +
				"		sq_song\n" +
				"	GROUP BY\n" +
				"		singer\n" +
				") c ON a. NAME = c.singer\n" +
				"WHERE\n" +
				"	name = ?";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql, name);
		
	}
	
	public List<Map<String, Object>> listsong(String singer) throws SQLException{
		String sql="select * from sq_song where singer=?";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql, singer);
		
	}
	public List<Map<String, Object>> listzjsl() throws SQLException{
		String sql="select * from sq_song limit 52,8";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql);
		
	}
	public List<Map<String, Object>> listzjgx() throws SQLException{
		String sql="select * from sq_singer limit 38,10";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql);
		
	}
	
	
}
