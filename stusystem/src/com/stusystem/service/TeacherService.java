package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.TeacherDao;
import com.stusystem.entity.Class;
import com.stusystem.entity.Teacher;

public class TeacherService {
    private TeacherDao dao= new TeacherDao();
    
    public int addTeacher(Teacher t){
		return dao.insertTeacher(t);
	}
    
    public int dropTeacher(int id,int pid){
    	return dao.delTeacherById(id, pid);
    }
    
    public int editTeacher(Teacher t,int pid){
    	return dao.updateTeacher(t, pid);
    }
    
    public Teacher getTeacherByid(int id){
    	return dao.selTeacherById(id);
    }
    
    public Teacher getTeacherByName(String name){
    	return dao.selTeacherByName(name);
    }
    
    public List<Teacher> getTeacherByPro(int pid){
    	return dao.selAllTeacherByPro(pid);
    }
    
    public List<Teacher> getTeacherByPage(int pageindex,int pagesize){
    	return dao.selTeacherByPage(pageindex, pagesize);
    } 
    
    public int getAllTeacherCount(){
    	return dao.selTeacherCount();
    }
    
    public int getPageCount(int pagesize){
		int pagecount=0;
		int rscount=dao.selTeacherCount();
		if (rscount % pagesize == 0) {
			pagecount = rscount / pagesize;
		} else {
			pagecount = rscount / pagesize + 1;
		}
		return pagecount;
	}
}
