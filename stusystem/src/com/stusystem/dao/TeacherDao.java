package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Class;
import com.stusystem.entity.Profession;
import com.stusystem.entity.Sex;
import com.stusystem.entity.Teacher;

public class TeacherDao extends BaseDao {

	public int insertTeacher(Teacher teacher) {
		int result=0;
		String sql = "insert into teacherinfo(teacher_name,teacher_age,teacher_sexid,teacher_proid)values(?,?,?,?)";
		Object[] values = { teacher.getTeacher_name(),
				teacher.getTeacher_age(), teacher.getSex().getSex_id(),
				teacher.getPro().getPro_id() };
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_teachernum=pro_teachernum+1 where pro_id=?";
			Object[] values1={teacher.getPro().getPro_id()};
			result=super.exeUpdate(sql, values1);			
		}
		return result;
	}
	
	public int delTeacherById(int id,int pid){
		int result=0;
		String sql="delete from teacherinfo where teacher_id=?";
		Object[] values={id};
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_teachernum=pro_teachernum-1 where pro_id=?";
			Object[] values1={pid};
			result=super.exeUpdate(sql, values1);			
		}
		return result;
	}
	
	public int updateTeacher(Teacher t,int pid){
		int result=0;
		String sql="update teacherinfo set teacher_name=?,teacher_age=?,teacher_sexid=?,teacher_proid=? where teacher_id=?";
		Object[] values={t.getTeacher_name(),t.getTeacher_age(),t.getSex().getSex_id(),t.getPro().getPro_id(),t.getTeacher_id()};
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_teachernum=pro_teachernum+1 where pro_id=?";
			Object[] values1={t.getPro().getPro_id()};
			result=super.exeUpdate(sql, values1);
			if (result!=0) {
				sql="update professioninfo set pro_teachernum=pro_teachernum-1 where pro_id=?";
				Object[] values2={pid};
				result=super.exeUpdate(sql, values2);	
			}
		}
		return result;
	}
	
	public Teacher selTeacherByName(String name){
		Teacher t=null;
		String sql="select * from teacherinfo where teacher_name=?";
		Object[] values={name};
		ResultSet rs =super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				t=new Teacher();
				t.setTeacher_id(rs.getInt("teacher_id"));
				t.setTeacher_name(rs.getString("teacher_name"));
				t.setTeacher_age(rs.getInt("teacher_age"));
				t.setTeacher_classnum(rs.getInt("teacher_classnum"));
				int sid=rs.getInt("teacher_sexid");
				SexDao sdao=new SexDao();
				Sex s=sdao.selSexById(sid);
				t.setSex(s);
				int pid=rs.getInt("teacher_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				t.setPro(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	
	public Teacher selTeacherById(int id){
		Teacher t=null;
		String sql="select * from teacherinfo where teacher_id=?";
		Object[] values={id};
		ResultSet rs =super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				t=new Teacher();
				t.setTeacher_id(rs.getInt("teacher_id"));
				t.setTeacher_name(rs.getString("teacher_name"));
				t.setTeacher_age(rs.getInt("teacher_age"));
				t.setTeacher_classnum(rs.getInt("teacher_classnum"));
				int sid=rs.getInt("teacher_sexid");
				SexDao sdao=new SexDao();
				Sex s=sdao.selSexById(sid);
				t.setSex(s);
				int pid=rs.getInt("teacher_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				t.setPro(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return t;
	}
	
	public List<Teacher> selAllTeacherByPro(int pid){
		List<Teacher> list=new ArrayList<Teacher>();
		String sql="select * from teacherinfo where teacher_proid=?";
		Object[] values={pid};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Teacher teacher=new Teacher();
				teacher.setTeacher_id(rs.getInt("teacher_id"));
				teacher.setTeacher_age(rs.getInt("teacher_age"));
				teacher.setTeacher_name(rs.getString("teacher_name"));
				teacher.setTeacher_classnum(rs.getInt("teacher_classnum"));
				int id = rs.getInt("teacher_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(id);
				teacher.setPro(p);
				int sid = rs.getInt("teacher_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				teacher.setSex(s);
				list.add(teacher);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Teacher> selTeacherByPage(int pageindex,int pagesize){
		List<Teacher> list=new ArrayList<Teacher>();
		String sql="select * from teacherinfo order by teacher_id desc limit ?,?";
		Object[] values={(pageindex-1)*pagesize,pagesize};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Teacher teacher=new Teacher();
				teacher.setTeacher_id(rs.getInt("teacher_id"));
				teacher.setTeacher_age(rs.getInt("teacher_age"));
				teacher.setTeacher_name(rs.getString("teacher_name"));
				teacher.setTeacher_classnum(rs.getInt("teacher_classnum"));
				int pid = rs.getInt("teacher_proid");
				ProfessionDao pdao = new ProfessionDao();
				Profession p = pdao.selProfessionById(pid);
				teacher.setPro(p);
				int sid = rs.getInt("teacher_sexid");
				SexDao sdao = new SexDao();
				Sex s = sdao.selSexById(sid);
				teacher.setSex(s);
				list.add(teacher);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public int selTeacherCount(){
		int count=0;
		String sql="select count(*) as num from teacherinfo";
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
