package com.stusystem.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stusystem.entity.News;
import com.stusystem.entity.Topic;
import com.stusystem.service.NewsService;
import com.stusystem.service.TopicService;
import com.stusystem.utils.MyUtils;

public class TopicServlet extends HttpServlet {
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
		}else if ("dodel".equals(action)) {
			dodel(request, response);
		}else if ("toedit".equals(action)) {
			toedit(request, response);
		}else if("doedit".equals(action)){
			doedit(request, response);
		}else if("nlist".equals(action)){
			nlist(request, response);
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
		TopicService service = new TopicService();

		int allnum= service.getTopicCount();
		int pagecount = service.getPageCount(MyUtils.pagesize);
		if (pageindex > pagecount) {
			pageindex = pagecount;
		}
		List<Topic> list = service.getTopicByPage(pageindex,
				MyUtils.pagesize);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("topiclist.jsp")
				.forward(request, response);
	}

	protected void doadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String tname = request.getParameter("txtName");
		TopicService service = new TopicService();
		Topic t = new Topic();
		t.setTopicname(tname);
		int result = service.addTopic(t);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('添加失败!'); history.go(-1);</script>");
		} else if (result == -1) {
			out.print("<script>alert('该主题已存在,请更换!'); history.go(-1)</script>");
		} else {
			response.sendRedirect("dotopic?op=list");
		}
	}

	protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		TopicService service = new TopicService();
		int result = service.dropTopicById(Integer.valueOf(id));
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('删除失败!');history.go(-1);</script>");
		} else if (result == -1) {
			out.print("<script>alert('该主题下还有新闻,不能删除!');history.go(-1);</script>");
		} else {
			response.sendRedirect("dotopic?op=list");
		}
	}
	
	protected void toedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  String id = request.getParameter("id");
		  TopicService service=new TopicService();
		  Topic t=service.getTopicsById(Integer.valueOf(id));
		  request.setAttribute("topic", t);
		  request.getRequestDispatcher("topicedit.jsp")
			.forward(request, response);
	}
	
	protected void doedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		   request.setCharacterEncoding("utf-8");
		   String tname=request.getParameter("txtName");
		   TopicService service=new TopicService();
		   Topic t = new Topic();
		   t.setTopicname(tname);
		   String id =request.getParameter("id");
		   t.setTopicid(Integer.valueOf(id));
		   PrintWriter out = response.getWriter();
		   int result=service.editTopic(t);
		   if(result==0){
		       out.print("<script>alert('修改失败!'); history.go(-1);</script>");
		   }else if(result==-1){
		       out.print("<script>alert('该主题已存在,请更换!'); history.go(-1);</script>");
		   }else{
		       response.sendRedirect("dotopic?op=list");
		   }
	}
	
	protected void nlist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		  String tid=request.getParameter("id");
		  String pagestr = request.getParameter("page");
			int pageindex = 1;
			if (pagestr != null && !"".equals(pagestr)) {
				pageindex = Integer.valueOf(pagestr);
			}
			NewsService service = new NewsService();

			int pagecount = service.getPageCount(MyUtils.pagesize);
			if (pageindex > pagecount) {
				pageindex = pagecount;
			}
			int allnum=service.getNewsByTopic(Integer.valueOf(tid));
			List<News> list = service.search(pageindex, MyUtils.pagesize, Integer.valueOf(tid));
			request.setAttribute("list", list);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("pageindex", pageindex);
			request.setAttribute("allnum", allnum);
			request.getRequestDispatcher("newslist.jsp").forward(request, response);
	}
}
