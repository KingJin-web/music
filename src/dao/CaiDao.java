package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import common.util.DBHelper;
import common.util.DBHelper.ResultSetMapper;

public class CaiDao {

	public void zanadd(String uname,String singername) throws SQLException {
		String sql="insert into sq_cai values(null,?,?,now())";
		DBHelper dbh=new DBHelper();
		dbh.update(sql,uname,singername);
	}
	
	public List<Map<String , Object>> getzans(String singername) throws SQLException {
		String sql="SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	(\n" +
				"		SELECT\n" +
				"			count(*) cnt,\n" +
				"			singername\n" +
				"		FROM\n" +
				"			sq_cai\n" +
				"		GROUP BY\n" +
				"			singername\n" +
				"	) c\n" +
				"WHERE\n" +
				"	c.singername = ?";
		DBHelper dbh=new DBHelper();
		return dbh.selectListMap(sql, singername);
	}
	
	/**
	 * 	检测用户对歌手是否点赞
	 * @param singername
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public int getcnt(String singername,String name) throws SQLException {
		String sql="select count(*) cnt from sq_cai where singername=? and uname=?";
		DBHelper dbh=new DBHelper();
		List<Integer> list=dbh.selectList(sql, new  ResultSetMapper<Integer>() {

			@Override
			public Integer map(ResultSet rs) throws SQLException {
				
				return rs.getInt(1);
			}
		}, singername,name);
		return list.get(0);
	}

	public void qxzan(String uname, String singername) throws SQLException {
		String sql="DELETE \n" +
				"FROM\n" +
				"	sq_cai\n" +
				"WHERE\n" +
				"	singername = ?\n" +
				"AND uname = ?";
		DBHelper dbh=new DBHelper();
		dbh.update(sql,singername,uname);
	}
	
}
