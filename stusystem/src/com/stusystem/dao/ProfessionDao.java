package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Profession;

public class ProfessionDao extends BaseDao{
  
	public int insertProfession(Profession p){
		int result=0;
		String sql="insert into professioninfo(pro_name,pro_abb)values(?,?)";
		Object[] values={p.getPro_name(),p.getPro_abb()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	public int delProfessionById(int id){
		int result=0;
		String sql="delete from professioninfo where pro_id=?";
		Object[] values={id};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	public int updateProfession(Profession p){
		int result=0;
		String sql="update professioninfo set pro_name=?,pro_abb=? where pro_id=?";
		Object[] values={p.getPro_name(),p.getPro_abb(),p.getPro_id()};
		result=super.exeUpdate(sql, values);
		return result;
	}
	
	public Profession selProfessionById(int id){
		Profession p = null;
		String sql="select * from professioninfo where pro_id=?";
		Object[] values={id};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if(rs.next()){
				p=new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	
	public Profession selPfofessionByNameAbb(String name,String abb){
		Profession p = null;
		String sql="select * from (select * from professioninfo where pro_name=?) as result where pro_abb=?";
		Object[] values={name,abb};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if(rs.next()){
				p=new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	
	public Profession selProfessionByName(String name){
		Profession p = null;
		String sql="select * from professioninfo where pro_name=?";
		Object[] values={name};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if(rs.next()){
				p=new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	
	public Profession selProfessionByAbbName(String name){
		Profession p = null;
		String sql="select * from professioninfo where pro_abb=?";
		Object[] values={name};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if(rs.next()){
				p=new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	
	public Profession selProfessionByAllName(String name,String abb){
		Profession p = null;
		String sql="select * from professioninfo where pro_name=? and pro_abb=? ";
		Object[] values={name,abb};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			if(rs.next()){
				p=new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return p;
	}
	
	public List<Profession> selAllProfession(){
		List<Profession> list=new ArrayList<Profession>();
		String sql="select * from professioninfo order by pro_id desc";
		ResultSet rs= super.exeQuery(sql, null);
		try {
			while(rs.next()){
				Profession p = new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	
	public List<Profession> selProfessionByPage(int pageindex,int pagesize){
		List<Profession> list=new ArrayList<Profession>();
		String sql="select * from professioninfo order by pro_id desc limit ?,?";
		Object[] values={(pageindex-1)*pagesize,pagesize};
		ResultSet rs= super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Profession p = new Profession();
				p.setPro_id(rs.getInt("pro_id"));
				p.setPro_name(rs.getString("pro_name"));
				p.setPro_abb(rs.getString("pro_abb"));
				p.setPro_classnum(rs.getInt("pro_classnum"));
				p.setPro_studentnum(rs.getInt("pro_studentnum"));
				p.setPro_teachernum(rs.getInt("pro_teachernum"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}	
		
	
	public int selProfessionCount(){
		int count=0;
		String sql="select count(*) as num from professioninfo";
		ResultSet rs = super.exeQuery(sql, null);
		try {
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return count;
	}
}
