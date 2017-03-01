package com.stusystem.service;

import java.util.List;

import com.stusystem.dao.StudentDao;
import com.stusystem.entity.Student;

public class StudentService {
    private StudentDao dao=new StudentDao();
    
    public int addStudent(Student s){
    	int result=0;
    	Student student=dao.selStudentByIdcard(s.getStudent_idcard());
    	if (student==null) {
			result=dao.insertStudent(s);
		}else {
			result=-1;
		}
    	return result;
    }
    
    public int dropStudentById(int id,int pid,int cid){
    	return dao.delstudent(id, pid, cid);
    }
    
    public int editStudent(Student student,int pid,int cid){
    	return dao.updatestudent(student, pid, cid);
    }
    
    public Student getStudentById(int id){
    	return dao.selStudentById(id);
    }
    
    public List<Student> getStudentByPage(int pageindex,int pagesize){
    	return dao.selStudentByPage(pageindex, pagesize);
    }
    
    public List<Student> getStudentByClaPage(int pageindex,int pagesize,int classid){
    	return dao.selStudentByClaPage(pageindex, pagesize, classid);
    }
    
    public int getStudentCount(){
    	return dao.selStudentCount();
    }
    
    public int getStudentCountByClass(int classid){
    	return dao.selStudentCountByClass(classid);
    }
    
    public int getPageCount(int pagesize){
    	int pagecount=0;
    	int rscount=dao.selStudentCount();
    	if (rscount%pagesize==0) {
			pagecount=rscount/pagesize;
		}else {
			pagecount=rscount/pagesize+1;
		}
    	return pagecount;
    }
}
