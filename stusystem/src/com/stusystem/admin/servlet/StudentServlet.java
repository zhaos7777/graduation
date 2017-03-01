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
import com.stusystem.entity.Student;
import com.stusystem.service.ClassService;
import com.stusystem.service.ProfessionService;
import com.stusystem.service.SexService;
import com.stusystem.service.StudentService;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String action=request.getParameter("op");
    	if ("toadd1".equals(action)) {
			toadd1(request, response);
		}else if ("toadd2".equals(action)) {
			toadd2(request, response);
		}else if ("doadd".equals(action)) {
			doadd(request, response);
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
		StudentService service=new StudentService();
		int allnum=service.getStudentCount();
		int pagecount = service.getPageCount(5);
		if (pageindex>pagecount) {
			pageindex=pagecount;
		}
		List<Student> list = service.getStudentByPage(pageindex, 5);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("studentlist.jsp").forward(request,
				response);
    	
    }
    
    protected void toadd1(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	ProfessionService pservice=new ProfessionService();
    	List<Profession> pro=pservice.getAllProfession();
        request.setAttribute("pro", pro);
        request.getRequestDispatcher("studentadd1.jsp").forward(request, response);;
    	
    }
    
    protected void toadd2(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String p=request.getParameter("selPro");
    	ProfessionService pservice=new ProfessionService();
    	Profession pro=pservice.getProfessionByid(Integer.valueOf(p));
    	ClassService cservice=new ClassService();
    	List<Class> c=cservice.getAllClassByPro(Integer.valueOf(p));
    	SexService sservice=new SexService();
    	List<Sex> s=sservice.getAllSex();
        request.setAttribute("c", c);
        request.setAttribute("s", s);
        request.setAttribute("pro", pro);
        request.getRequestDispatcher("studentadd2.jsp").forward(request, response);;
    	
    }
    
    protected void doadd(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String name=request.getParameter("name");
    	String age=request.getParameter("age");
    	String sexid=request.getParameter("sex");
    	String id=request.getParameter("id");
    	String home=request.getParameter("home");
    	String selclass=request.getParameter("selClass");
    	String pid=request.getParameter("professionid");
    	Sex sex=new Sex();
    	sex.setSex_id(Integer.valueOf(sexid));
    	Class cla=new Class();
    	cla.setClass_id(Integer.valueOf(selclass));
    	Profession pro=new Profession();
    	pro.setPro_id(Integer.valueOf(pid));
    	Student student=new Student();
    	student.setStudent_name(name);
    	student.setStudent_age(Integer.valueOf(age));
    	student.setSex(sex);
    	student.setStudent_home(home);
    	student.setStudent_idcard(id);
    	student.setCla(cla);
    	student.setProfession(pro);
    	StudentService service=new StudentService();
    	int result=service.addStudent(student);
    	PrintWriter out=response.getWriter();
    	if (result==0) {
			out.print("<script>alert('添加失败');history.go(-1);<script>");
		}else if (result==-1) {
			out.print("<script>alert('该同学已经被添加,请更换');history.go(-1);<script>");
		}else {
			response.sendRedirect("dostudent?op=list");
		}
    }
    
    protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pid=request.getParameter("pid");
		String cid=request.getParameter("cid");
		StudentService service=new StudentService();
		int result=service.dropStudentById(Integer.valueOf(id),Integer.valueOf(pid),Integer.valueOf(cid));
		PrintWriter out = response.getWriter();
		if (result==0) {
			out.print("<script>alert('删除失败');history.go(-1);</script>");
		}else{
			response.sendRedirect("dostudent?op=list");
		}
	}
    
    protected void toedit1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pid=request.getParameter("pid");
		String cid=request.getParameter("cid");
		ProfessionService pservice=new ProfessionService();
    	List<Profession> profession=pservice.getAllProfession();
    	StudentService sservice=new StudentService();
    	Student student=sservice.getStudentById(Integer.valueOf(id));
    	 request.setAttribute("pro", profession);
         request.setAttribute("s", student);
         request.setAttribute("pid", pid);
         request.setAttribute("cid", cid);
         request.getRequestDispatcher("studentedit1.jsp").forward(request, response);
	}
    
    protected void toedit2(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String p=request.getParameter("selPro");
    	String pid=request.getParameter("pid");
		String cid=request.getParameter("cid");
		String id = request.getParameter("id");
    	ProfessionService pservice=new ProfessionService();
    	Profession pro=pservice.getProfessionByid(Integer.valueOf(p));
    	ClassService cservice=new ClassService();
    	List<Class> c=cservice.getAllClassByPro(Integer.valueOf(p));
    	SexService sservice=new SexService();
    	List<Sex> s=sservice.getAllSex();
    	StudentService stuservice=new StudentService();
    	Student stu=stuservice.getStudentById(Integer.valueOf(id));
        request.setAttribute("c", c);
        request.setAttribute("s", s);
        request.setAttribute("pro", pro);
        request.setAttribute("stu", stu);
        request.setAttribute("pid", pid);
        request.setAttribute("cid", cid);
        request.getRequestDispatcher("studentedit2.jsp").forward(request, response);;
    	
    }
    
    protected void doedit(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String name=request.getParameter("name");
    	String age=request.getParameter("age");
    	String sexid=request.getParameter("sex");
    	String id=request.getParameter("id");
    	String home=request.getParameter("home");
    	String selclass=request.getParameter("selClass");
    	String proid=request.getParameter("professionid");
    	String pid=request.getParameter("pid");
		String cid=request.getParameter("cid");
		String stuid=request.getParameter("stuid");
    	Sex sex=new Sex();
    	sex.setSex_id(Integer.valueOf(sexid));
    	Class cla=new Class();
    	cla.setClass_id(Integer.valueOf(selclass));
    	Profession pro=new Profession();
    	pro.setPro_id(Integer.valueOf(proid));
    	Student student=new Student();
    	student.setStudent_id(Integer.valueOf(stuid));
    	student.setStudent_name(name);
    	student.setStudent_age(Integer.valueOf(age));
    	student.setSex(sex);
    	student.setStudent_home(home);
    	student.setStudent_idcard(id);
    	student.setCla(cla);
    	student.setProfession(pro);
    	StudentService service=new StudentService();
    	int result=service.editStudent(student, Integer.valueOf(pid), Integer.valueOf(cid));
    	PrintWriter out=response.getWriter();
    	if (result==0) {
			out.print("<script>alert('修改失败');history.go(-1);<script>");
		}else if (result==-1) {
			out.print("<script>alert('该同学已存在,请更换');history.go(-1);<script>");
		}else {
			response.sendRedirect("dostudent?op=list");
		}
    }
}
