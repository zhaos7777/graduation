package com.stusystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stusystem.entity.Class;
import com.stusystem.entity.Profession;
import com.stusystem.entity.Teacher;

public class ClassDao extends BaseDao {
   
	public int insertClass(Class c){
		int result=0;
		String sql="insert into classinfo(class_name,class_proid,class_teacherid)values(?,?,?)";
		Object[] values={c.getClass_name(),c.getProfession().getPro_id(),c.getTeacher().getTeacher_id()};
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_classnum=pro_classnum+1 where pro_id=?";
			Object[] values1={c.getProfession().getPro_id()};
			result=super.exeUpdate(sql, values1);
			if (result!=0) {
				sql="update teacherinfo set teacher_classnum=teacher_classnum+1 where teacher_id=?";
				Object[] values2={c.getTeacher().getTeacher_id()};
				result=super.exeUpdate(sql, values2);
		    }
		}
		return result;
	}
	
	public int delClassById(int id,int pid,int tid){
		int result=0;
		String sql="delete from classinfo where class_id=?";
		Object[] values={id};
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_classnum=pro_classnum-1 where pro_id=?";
			Object[] value={pid};
			result=super.exeUpdate(sql, value);		
			if (result!=0) {
				sql="update teacherinfo set teacher_classnum=teacher_classnum-1 where teacher_id=?";
				Object[] values2={tid};
				result=super.exeUpdate(sql, values2);
		    }
		}
		return result;
		
	}
	
	public int updateClass(Class c,int pid,int tid){
		int result=0;
		String sql="update classinfo set class_name=?,class_proid=?,class_teacherid=? where class_id=?";
		Object[] values={c.getClass_name(),c.getProfession().getPro_id(),c.getTeacher().getTeacher_id(),c.getClass_id()};
		result=super.exeUpdate(sql, values);
		if (result!=0) {
			sql="update professioninfo set pro_classnum=pro_classnum+1 where pro_id=?";
			Object[] value={c.getProfession().getPro_id()};
			result=super.exeUpdate(sql, value);
			if (result!=0) {
				sql="update professioninfo set pro_classnum=pro_classnum-1 where pro_id=?";
				Object[] value1={pid};
				result=super.exeUpdate(sql, value1);	
				if (result!=0) {
					sql="update teacherinfo set teacher_classnum=teacher_classnum-1 where teacher_id=?";
					Object[] value2={tid};
					result=super.exeUpdate(sql, value2);	
					if (result!=0) {
						sql="update teacherinfo set teacher_classnum=teacher_classnum+1 where teacher_id=?";
						Object[] value3={c.getTeacher().getTeacher_id()};
						result=super.exeUpdate(sql, value3);	
					}
				}	
			}
		}
		return result;
	}
	
	public List<Class> selClassByPor(int pid){
		String sql="select * from classinfo";
		List values=new ArrayList();
		if (pid!=0) {
			sql=sql+" where class_proid=?";
			values.add(pid);
		}
		sql=sql+" order by class_id desc";
		List<Class> list=new ArrayList<Class>();
		ResultSet rs=super.exeQuery(sql, values.toArray());
		try {
			while(rs.next()){
				Class c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int id=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(id);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Class> selClassByTeacher(int teacherid){
		String sql="select * from classinfo";
		List values=new ArrayList();
		if (teacherid!=0) {
			sql=sql+" where class_teacherid=?";
			values.add(teacherid);
		}
		sql=sql+" order by class_id desc";
		List<Class> list=new ArrayList<Class>();
		ResultSet rs=super.exeQuery(sql, values.toArray());
		try {
			while(rs.next()){
				Class c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int id=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(id);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public int selClassCount(int pid){
		int count=0;
		String sql="select count(*) as num from classinfo";
		List values=new ArrayList();
		if (pid!=0) {
			sql=sql+" where class_proid=?";
			values.add(pid);
		}
		ResultSet rs = super.exeQuery(sql, values.toArray());
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
	
	public int selClassCountByTeacher(int teacherid){
		int count=0;
		String sql="select count(*) as num from classinfo";
		List values=new ArrayList();
		if (teacherid!=0) {
			sql=sql+" where class_teacherid=?";
			values.add(teacherid);
		}
		ResultSet rs = super.exeQuery(sql, values.toArray());
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
	
	public int selClassCountByPro(int pid){
		int count=0;
		String sql="select count(*) as num from classinfo where class_proid=?";
		Object[] values={pid};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return count;
	}
	
	public Class selClassByName(String name){
		Class c=null;
		String sql="select * from classinfo where class_name=?";
		Object[] values={name};
		ResultSet rs =super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int pid=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				c.setProfession(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return c;
	}
	
	public Class selClassById(int id){
		Class c=null;
		String sql="select * from classinfo where class_id=?";
		Object[] values={id};
		ResultSet rs =super.exeQuery(sql, values);
		try {
			if (rs.next()) {
				c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int pid=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				c.setProfession(p);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return c;
	}
	
	public List<Class> selAllClass(){
		List<Class> list=new ArrayList<Class>();
		String sql="select * from classinfo order by class_id desc";
		ResultSet rs=super.exeQuery(sql, null);
		try {
			while(rs.next()){
				Class c= new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int pid=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Class> selClassByPage(int pageindex,int pagesize){
		List<Class> list=new ArrayList<Class>();
		String sql="select * from classinfo order by class_id desc limit ?,?";
		Object[] values={(pageindex-1)*pagesize,pagesize};
		ResultSet rs=super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Class c= new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int pid=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(pid);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Class> selClassByProPage(int pageindex,int pagesize,int pid){
		String sql="select * from classinfo where class_proid=? order by class_id desc limit ?,?";
		Object[] values={pid,(pageindex-1)*pagesize,pagesize};
		List<Class> list=new ArrayList<Class>();
		ResultSet rs=super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Class c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int id=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(id);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public List<Class> selClassByTeacherPage(int pageindex,int pagesize,int teacherid){
		String sql="select * from classinfo where class_teacherid=? order by class_id desc limit ?,?";
		Object[] values={teacherid,(pageindex-1)*pagesize,pagesize};
		List<Class> list=new ArrayList<Class>();
		ResultSet rs=super.exeQuery(sql, values);
		try {
			while(rs.next()){
				Class c=new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				c.setClass_studentnum(rs.getInt("class_studentnum"));
				int tid=rs.getInt("class_teacherid");
				TeacherDao tdao=new TeacherDao();
				Teacher t=tdao.selTeacherById(tid);
				c.setTeacher(t);
				int id=rs.getInt("class_proid");
				ProfessionDao pdao=new ProfessionDao();
				Profession p=pdao.selProfessionById(id);
				c.setProfession(p);
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return list;
	}
	
	public int selClassCount(){
		int count=0;
		String sql="select count(*) as num from classinfo";
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
