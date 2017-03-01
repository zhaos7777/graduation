package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Sex;

public class SexDao extends BaseDao {
      public Sex selSexById(int id){
    	  Sex sex=null;
    	  String sql="select * from sexinfo where sex_id=?";
    	  Object[] values={id};
    	  ResultSet rs=super.exeQuery(sql, values);
    	  try {
			if (rs.next()) {
				sex=new Sex();
				sex.setSex_id(rs.getInt("sex_id"));
				sex.setSex_name(rs.getString("sex_name"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
    	  return sex;
      }
      
      public List<Sex> selAllSex(){
    	  List<Sex> list=new ArrayList<Sex>();
    	  String sql="select * from sexinfo order by sex_id asc";
    	  ResultSet rs=super.exeQuery(sql, null);
    	  try {
			while(rs.next()){
				  Sex s=new Sex();
				  s.setSex_id(rs.getInt("sex_id"));
				  s.setSex_name(rs.getString("sex_name"));
				  list.add(s);
			  }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
    	  return list;
      }
}
