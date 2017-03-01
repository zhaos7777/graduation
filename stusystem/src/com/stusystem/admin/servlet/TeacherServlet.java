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
import com.stusystem.entity.Sex;
import com.stusystem.entity.Teacher;
import com.stusystem.service.ClassService;
import com.stusystem.service.ProfessionService;
import com.stusystem.service.SexService;
import com.stusystem.service.TeacherService;
import com.stusystem.utils.MyUtils;

public class TeacherServlet extends HttpServlet {

	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
	    	response.setContentType("text/html;charset=utf-8");
	    	String action=request.getParameter("op");
	    	if ("doadd".equals(action)) {
				doadd(request, response);
			}else if ("toadd".equals(action)) {
				toadd(request, response);
			}else if ("list".equals(action)) {
				list(request, response);
			}else if ("dodel".equals(action)) {
				dodel(request, response);
			}else if ("toedit".equals(action)) {
				toedit(request, response);
			}else if ("doedit".equals(action)) {
				doedit(request, response);
			}else if ("clist".equals(action)) {
				clist(request, response);
			}
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
	    	doGet(request, response);
	    }
	 
	 protected void list(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
			String pagestr = request.getParameter("page");
			int pageindex = 1;
			if (pagestr != null && !"".equals(pagestr)) {
				pageindex = Integer.valueOf(pagestr);
			}
			TeacherService service = new TeacherService();
			int allnum=service.getAllTeacherCount();
			int pagecount = service.getPageCount(MyUtils.pagesize);
			if (pageindex>pagecount) {
				pageindex=pagecount;
			}
			List<Teacher> list = service.getTeacherByPage(pageindex, MyUtils.pagesize);
			request.setAttribute("list", list);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("pageindex", pageindex);
			request.setAttribute("allnum", allnum);
			request.getRequestDispatcher("teacherlist.jsp").forward(request,
					response);
	    }
	  
	 
	 protected void toadd(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
		    ProfessionService pservice= new ProfessionService();
	    	List<Profession> p=pservice.getAllProfession();
	    	SexService sservice=new SexService();
	    	List<Sex> s=sservice.getAllSex();
	    	request.setAttribute("pro", p);
	    	request.setAttribute("sex", s);
	    	request.getRequestDispatcher("teacheradd.jsp").forward(request, response);
	    }
	 
	 protected void doadd(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
		    String id=request.getParameter("selPro");
	    	String name=request.getParameter("name");
	    	String age=request.getParameter("age");
	    	String sex=request.getParameter("sex");
	    	Profession p=new Profession();
	    	p.setPro_id(Integer.valueOf(id));
	    	Sex s=new Sex();
	    	s.setSex_id(Integer.valueOf(sex));
	    	TeacherService service=new TeacherService();
	    	Teacher teacher=new Teacher();
	        teacher.setTeacher_name(name);
	        teacher.setTeacher_age(Integer.valueOf(age));
	        teacher.setPro(p);
	        teacher.setSex(s);
	    	int result=service.addTeacher(teacher);
	    	PrintWriter out = response.getWriter();
	    	if (result==0) {
				out.print("<script>alert('添加失败!');history.go(-1);</script>");
			}else if (result==-1) {
				out.print("<script>alert('班级已存在,请更换!');history.go(-1);</script>");
			}else {
				response.sendRedirect("doteacher?op=list");
			}
	    }
	 
	  protected void dodel(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String id = request.getParameter("id");
			String pid=request.getParameter("pid");
			TeacherService service=new TeacherService();
			int result=service.dropTeacher(Integer.valueOf(id),Integer.valueOf(pid));
			PrintWriter out = response.getWriter();
			if (result==0) {
				out.print("<script>alert('删除失败');history.go(-1);</script>");
			}else{
				response.sendRedirect("doteacher?op=list");
			}
		}
	  
	  protected void toedit(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
	    	// TODO 自动生成的方法存根
	    	String id=request.getParameter("id");
	    	String pid=request.getParameter("pid");
	    	TeacherService service=new TeacherService();
	    	Teacher t=service.getTeacherByid(Integer.valueOf(id));
	    	ProfessionService pservice=new ProfessionService();
	    	List<Profession> profession=pservice.getAllProfession();
	    	SexService sservice=new SexService();
	    	List<Sex> s=sservice.getAllSex();
	    	request.setAttribute("sex", s);
	    	request.setAttribute("t", t);
	    	request.setAttribute("pro", profession);
	    	request.setAttribute("pid", pid);
	    	request.getRequestDispatcher("teacheredit.jsp").forward(request, response);
	    }
	  
	  protected void doedit(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
	    	// TODO 自动生成的方法存根    	
		    String proid=request.getParameter("selPro");
	    	String name=request.getParameter("name");
	    	String age=request.getParameter("age");
	    	String sex=request.getParameter("sex");
	    	String id=request.getParameter("id");
	    	String pid=request.getParameter("pid");
	    	Profession p=new Profession();
	    	p.setPro_id(Integer.valueOf(proid));
	    	Sex s=new Sex();
	    	s.setSex_id(Integer.valueOf(sex));
	    	TeacherService service=new TeacherService();
	    	Teacher teacher=new Teacher();
	    	teacher.setTeacher_id(Integer.valueOf(id));
	        teacher.setTeacher_name(name);
	        teacher.setTeacher_age(Integer.valueOf(age));
	        teacher.setPro(p);
	        teacher.setSex(s);
	        int result=service.editTeacher(teacher,Integer.valueOf(pid));
	        PrintWriter out=response.getWriter();
	        if (result==0) {
				out.print("<script>alert('修改失败');history.go(-1);</script>");
			}else {
				response.sendRedirect("doteacher?op=list");
			}
	    }
	  
	  protected void clist(HttpServletRequest request, HttpServletResponse response)
	    		throws ServletException, IOException {
	    	String pagestr = request.getParameter("page");
	    	String tid = request.getParameter("id");
			int pageindex = 1;
			if (pagestr != null && !"".equals(pagestr)) {
				pageindex = Integer.valueOf(pagestr);
			}
			ClassService service = new ClassService();
			int allnum=service.getClassNumByTeacher(Integer.valueOf(tid));
			int pagecount = service.getPageCountByTeacher(MyUtils.pagesize, Integer.valueOf(tid));
			if (pageindex>pagecount) {
				pageindex=pagecount;
			}
			List<Class> list = service.getClassByTeacherPage(pageindex, MyUtils.pagesize, Integer.valueOf(tid));
			request.setAttribute("list", list);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("pageindex", pageindex);
			request.setAttribute("allnum", allnum);
			request.getRequestDispatcher("classlist.jsp").forward(request,
					response);
	    }
}
