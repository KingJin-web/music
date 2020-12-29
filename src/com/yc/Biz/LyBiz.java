package com.yc.Biz;

import java.sql.SQLException;

import com.yc.Dao.LyDao;
import com.yc.common.biz.BizException;
import com.yc.common.util.Utils;

public class LyBiz {

	private LyDao ld=new LyDao();
	public void insertly(String name,String content) throws BizException {
		Utils.checkNull(name, "用户名不能为空");
		Utils.checkNull(content, "内容不能为空");
		
		try {
			ld.addly(name, content);
		} catch (SQLException e) {
			throw new BizException("系统繁忙请联系管理员");
		}
		
		
		
	}
}
