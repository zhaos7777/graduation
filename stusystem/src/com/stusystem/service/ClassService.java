package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.ClassDao;
import com.stusystem.dao.StudentDao;
import com.stusystem.dao.TeacherDao;
import com.stusystem.entity.Class;
import com.stusystem.entity.Student;
import com.stusystem.entity.Teacher;

public class ClassService {

	private ClassDao dao=new ClassDao();
	
	public int addClass(Class c){
		int result=0;
		Class cc=dao.selClassByName(c.getClass_name());
		if (cc==null ) {
			result=dao.insertClass(c);
		}else {
			result=-1;
		}
		return result;
	}
	
	public int dropClassById(int id,int pid,int tid){
		int result=0;
		StudentDao sdao=new StudentDao();
		int ss = sdao.selStudentCountByClass(id);
		if (ss==0) {
			result= dao.delClassById(id,pid,tid);
		}else {
			result=-1;
		}
		return result;
	}
	
	public int editClass(Class c,int pid,int tid){
		int result=0;
		Class cc=dao.selClassByName(c.getClass_name());
		if (cc==null) {
			result = dao.updateClass(c, pid, tid);
		}else {
			if (cc.getClass_id()==c.getClass_id()) {
				result = dao.updateClass(c, pid, tid);
			}
			else {
				result = -1;
			}
		}
		return result;
	}
	
	public Class getClassById(int id){
		return dao.selClassById(id);
	}
	
	public int getAllClassNum() {
		int rscount = dao.selClassCount();
		return rscount;
	}
	
	public int getClassNumByTeacher(int teacherid) {
		int rscount = dao.selClassCountByTeacher(teacherid);
		return rscount;
	}
	
	public int getAllClassNumByPro(int pid) {
		return  dao.selClassCountByPro(pid);
		
	}
	
	public List<Class> getAllClass(){
		return dao.selAllClass();
	}
	
	public List<Class> getAllClassByPro(int pid){
		return dao.selClassByPor(pid);
	}
	
	public List<Class> getClassByPage(int pageindex,int pagesize){
		return dao.selClassByPage(pageindex, pagesize);
	}
	
	public List<Class> getClassByProPage(int pageindex,int pagesize,int pid){
		return dao.selClassByProPage(pageindex, pagesize, pid);
	}
	
	public List<Class> getClassByTeacherPage(int pageindex,int pagesize,int teacherid){
		return dao.selClassByTeacherPage(pageindex, pagesize, teacherid);
	}
	
	public int getPageCount(int pagesize){
		int pagecount=0;
		int rscount=dao.selClassCount();
		if (rscount % pagesize == 0) {
			pagecount = rscount / pagesize;
		} else {
			pagecount = rscount / pagesize + 1;
		}
		return pagecount;
	}
	
	public int getPageCount(int pagesize,int pid){
		int pagecount=0;
		int rscount=dao.selClassCount(pid);
		if (rscount % pagesize == 0) {
			pagecount = rscount / pagesize;
		} else {
			pagecount = rscount / pagesize + 1;
		}
		return pagecount;
	}
	
	public int getPageCountByTeacher(int pagesize,int teacherid){
		int pagecount=0;
		int rscount=dao.selClassCountByTeacher(teacherid);
		if (rscount % pagesize == 0) {
			pagecount = rscount / pagesize;
		} else {
			pagecount = rscount / pagesize + 1;
		}
		return pagecount;
	}
}
