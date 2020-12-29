package dao;
import java.sql.SQLException;


import java.util.List;
import java.util.Map;

import common.util.DBHelper;

public class SearchDao {

	public List<Map<String, Object>> listname(String name) throws SQLException{
		String sql="select * from sq_song where singer=?";
		 
		return DBHelper.selectListMap(sql, name);
	}
	
	public List<Map<String, Object>> listsong(String name) throws SQLException{
		String sql="select * from sq_song where name=?";
		 
		return DBHelper.selectListMap(sql, name);
	}
	
}
