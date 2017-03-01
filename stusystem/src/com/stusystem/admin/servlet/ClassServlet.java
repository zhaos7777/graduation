package com.stusystem.admin.servlet;

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
    	if ("doadd".equals(action)) {
			doadd(request, response);
		}else if ("toadd1".equals(action)) {
			toadd1(request, response);
		}else if ("toadd2".equals(action)) {
			toadd2(request, response);
		}else if ("list".equals(action)) {
			list(request, response);
		}else if ("dodel".equals(action)) {
			dodel(request, response);
		}else if ("toedit1".equals(action)) {
			toedit1(request, response);
		}else if ("toedit2".equals(action)) {
			toedit2(request, response);
		}else if ("doedit".equals(action)) {
			doedit(request, response);
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
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		ClassService service = new ClassService();
		int allnum=service.getAllClassNum();
		int pagecount = service.getPageCount(5);
		if (pageindex>pagecount) {
			pageindex=pagecount;
		}
		List<Class> list = service.getClassByPage(pageindex, 5);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("classlist.jsp").forward(request,
				response);
    }
    
    protected void toadd1(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	ProfessionService pservice= new ProfessionService();
    	List<Profession> p=pservice.getAllProfession();
    	request.setAttribute("pro", p);
    	request.getRequestDispatcher("classadd1.jsp").forward(request, response);
    }
    
    protected void toadd2(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	String id=request.getParameter("selPro");
    	TeacherService service = new TeacherService();
    	List<Teacher> t = service.getTeacherByPro(Integer.valueOf(id));
    	ProfessionService pservice=new ProfessionService();
    	Profession pro=pservice.getProfessionByid(Integer.valueOf(id));
    	request.setAttribute("t", t);
    	request.setAttribute("pro", pro);
    	request.getRequestDispatcher("classadd2.jsp").forward(request, response);
    }
    
    protected void doadd(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	String id=request.getParameter("selTeacher");
    	String classname=request.getParameter("classname");
    	String pid = request.getParameter("pid");
    	Profession p=new Profession();
    	p.setPro_id(Integer.valueOf(pid));
    	Teacher t=new Teacher();
    	t.setTeacher_id(Integer.valueOf(id));
    	ClassService service=new ClassService();
    	Class c=new Class();
        c.setProfession(p);
    	c.setClass_name(classname);
    	c.setTeacher(t);
    	int result=service.addClass(c);
    	PrintWriter out = response.getWriter();
    	if (result==0) {
			out.print("<script>alert('添加失败!');history.go(-1);</script>");
		}else if (result==-1) {
			out.print("<script>alert('班级已存在,请更换!');history.go(-1);</script>");
		}else {
			response.sendRedirect("doclass?op=list");
		}
    }
    
    protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pid=request.getParameter("pid");
		String tid=request.getParameter("tid");
		ClassService service=new ClassService();
		int result=service.dropClassById(Integer.valueOf(id),Integer.valueOf(pid),Integer.valueOf(tid));
		PrintWriter out = response.getWriter();
		if (result==0) {
			out.print("<script>alert('删除失败');history.go(-1);</script>");
		}else if (result==-1) {
			out.print("<script>alert('该班级还有学生,不能删除!');history.go(-1);</script>");
		}else{
			response.sendRedirect("doclass?op=list");
		}
	}
    
    protected void toedit1(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	String id=request.getParameter("id");
    	String pid=request.getParameter("pid");
    	String tid=request.getParameter("tid");
    	ClassService service=new ClassService();
    	Class c=service.getClassById(Integer.valueOf(id));
    	ProfessionService pservice=new ProfessionService();
    	List<Profession> profession=pservice.getAllProfession();
    	request.setAttribute("c", c);
    	request.setAttribute("pro", profession);
    	request.setAttribute("pid", pid);
    	request.setAttribute("tid", tid);
    	request.getRequestDispatcher("classedit1.jsp").forward(request, response);
    	
    }
    
    protected void toedit2(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	String id=request.getParameter("classid");
    	String pid=request.getParameter("pid");
    	String tid=request.getParameter("tid");
    	String selpid=request.getParameter("selPro");
    	ClassService service=new ClassService();
    	Class c=service.getClassById(Integer.valueOf(id));
        TeacherService sservice=new TeacherService();
        List<Teacher> t= sservice.getTeacherByPro(Integer.valueOf(selpid));
    	request.setAttribute("c", c);
        request.setAttribute("t", t);
    	request.setAttribute("pid", pid);
    	request.setAttribute("tid", tid);
    	request.setAttribute("selpid", selpid);
    	request.getRequestDispatcher("classedit2.jsp").forward(request, response);
    	
    }
    
    protected void doedit(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根    	
    	String seltid=request.getParameter("selTeacher");
    	String classname=request.getParameter("classname");
    	String classid=request.getParameter("classid");
    	String pid=request.getParameter("pid");
    	String tid=request.getParameter("tid");
    	String selpid=request.getParameter("selpid");
    	Profession p =new Profession();
    	p.setPro_id(Integer.valueOf(selpid));
    	Teacher t=new Teacher();
    	t.setTeacher_id(Integer.valueOf(seltid));
    	Class c=new Class();
    	c.setClass_id(Integer.valueOf(classid));
    	c.setClass_name(classname);
    	c.setTeacher(t);
    	c.setProfession(p);
    	ClassService service=new ClassService();
        int result=service.editClass(c,Integer.valueOf(pid),Integer.valueOf(tid));
        PrintWriter out=response.getWriter();
        if (result==0) {
			out.print("<script>alert('修改失败');history.go(-1);</script>");
		}else {
			response.sendRedirect("doclass?op=list");
		}
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
