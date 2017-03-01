package com.stusystem.teacher.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stusystem.entity.Class;
import com.stusystem.entity.Profession;
import com.stusystem.entity.Student;
import com.stusystem.entity.Teacher;
import com.stusystem.service.ClassService;
import com.stusystem.service.ProfessionService;
import com.stusystem.service.StudentService;
import com.stusystem.service.TeacherService;

public class ClassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String action=request.getParameter("op");
    	if ("list".equals(action)) {
			list(request, response);
		}else if ("slist".equals(action)) {
			slist(request, response);
		}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	doGet(request, response);
    }
    
    protected void list(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String pagestr = request.getParameter("page");
    	String tname = request.getParameter("tname");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		ClassService service = new ClassService();
		TeacherService tservice = new TeacherService();
		Teacher teacher = tservice.getTeacherByName(tname);
		int allnum=service.getClassNumByTeacher(teacher.getTeacher_id());
		int pagecount = service.getPageCount(5);
		if (pageindex>pagecount) {
			pageindex=pagecount;
		}
		List<Class> list = service.getClassByTeacherPage(pageindex, 5,teacher.getTeacher_id());
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("classlist.jsp").forward(request,
				response);
    }
    
    protected void slist(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String pagestr = request.getParameter("page");
    	String id = request.getParameter("id");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		StudentService service=new StudentService();
		int allnum=service.getStudentCountByClass(Integer.valueOf(id));
		int pagecount = service.getPageCount(5);
		if (pageindex>pagecount) {
			pageindex=pagecount;
		}
		List<Student> list = service.getStudentByClaPage(pageindex, 5, Integer.valueOf(id));
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("studentlist.jsp").forward(request,
				response);
    	
    }
}
