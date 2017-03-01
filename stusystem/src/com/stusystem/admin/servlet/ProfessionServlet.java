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
import com.stusystem.service.ClassService;
import com.stusystem.service.ProfessionService;
import com.stusystem.utils.MyUtils;

public class ProfessionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("op");
		if ("list".equals(action)) {
            list(request, response);
		} else if ("doadd".equals(action)) {
			doadd(request, response);
		} else if ("dodel".equals(action)) {
			dodel(request, response);
		} else if ("doedit".equals(action)) {
			doedit(request, response);
		} else if ("toedit".equals(action)) {
			toedit(request, response);
		} else if ("clist".equals(action)) {
			clist(request, response);
		} 
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagestr = request.getParameter("page");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		ProfessionService service = new ProfessionService();
		int allnum=service.getAllProfessionNum();
		int pagecount = service.getPageCount(5);
		List<Profession> list = service.getProfessionByPage(pageindex, 5);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("professionlist.jsp").forward(request,
				response);
	}

	protected void doadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("proname");
		String abbname = request.getParameter("proabb");
		ProfessionService service = new ProfessionService();
		Profession p = new Profession();
		p.setPro_name(name);
		p.setPro_abb(abbname);
		int result = service.addProfession(p);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('添加失败');history.go(-1);</script>");
		} else if (result == -1) {
			out.print("<script>alert('专业名或简称已存在,请更换!');history.go(-1);</script>");
		} else {
			response.sendRedirect("doprofession?op=list");
		}
	}
	
	protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProfessionService service=new ProfessionService();
		int result=service.dropProfessionById(Integer.valueOf(id));
		PrintWriter out = response.getWriter();
		if (result==0) {
			out.print("<script>alert('删除失败');history.go(-1);</script>");
		}else if (result==-1) {
			out.print("<script>alert('该专业下还有班级,删除失败');history.go(-1);</script>");
		}else{
			response.sendRedirect("doprofession?op=list");
		}
	}
	
	protected void toedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ProfessionService service=new ProfessionService();
		Profession p = service.getProfessionByid(Integer.valueOf(id));
		request.setAttribute("pro", p);
		request.getRequestDispatcher("professionedit.jsp").forward(request, response);
	}
	
	protected void doedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("proname");
		String abbname=request.getParameter("proabb");
		String id=request.getParameter("id");
		ProfessionService service=new ProfessionService();
		Profession p = new Profession();
		p.setPro_name(name);
		p.setPro_abb(abbname);
		p.setPro_id(Integer.valueOf(id));
		PrintWriter out = response.getWriter();
		int result=service.editProfession(p);
		if (result==0) {
			out.print("<script>alert('修改失败');history.go(-1);</script>");
		}else if(result==-1){
			out.print("<script>alert('和其它专业名称或简称冲突,请更换!');history.go(-1);</script>");
		}else {
			response.sendRedirect("doprofession?op=list");
		}
	}
	
	protected void clist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagestr = request.getParameter("page");
		String id = request.getParameter("id");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		ClassService service = new ClassService();
		int allnum=service.getAllClassNumByPro(Integer.valueOf(id));
		int pagecount = service.getPageCount(MyUtils.pagesize,Integer.valueOf(id));
		if (pageindex>pagecount) {
			pageindex=pagecount;
		}
		List<Class> list = service.getClassByProPage(pageindex, MyUtils.pagesize, Integer.valueOf(id));
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("classlist.jsp").forward(request,
				response);
    }
}
