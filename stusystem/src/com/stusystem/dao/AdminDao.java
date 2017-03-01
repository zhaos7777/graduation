package com.stusystem.dao;

import java.sql.ResultSet;

import com.stusystem.entity.AdminInfo;

public class AdminDao extends BaseDao {
   
	public AdminInfo selAdminInfoByUsername(String username){
		AdminInfo admin = null;
		String sql = "select * from admininfo where username=?";
		Object[] values={username};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				admin=new AdminInfo();
				admin.setId(rs.getInt("id"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setType(rs.getString("type"));
				admin.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			super.closeAll();
		}
		return admin;
	}
}
