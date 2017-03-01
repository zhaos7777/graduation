package com.stusystem.admin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.stusystem.entity.News;
import com.stusystem.entity.Topic;
import com.stusystem.service.AdminService;
import com.stusystem.service.NewsService;
import com.stusystem.service.TopicService;
import com.stusystem.utils.MyUtils;

public class NewsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getParameter("op");
		if ("list".equals(action)) {
            list(request, response);
		}else if ("doadd".equals(action)) {
            doadd(request, response);
		}else if ("toadd".equals(action)) {
            toadd(request, response);
		}else if ("dodel".equals(action)) {
            dodel(request, response);
		}else if ("toedit".equals(action)) {
            toedit(request, response);
		}else if ("doedit".equals(action)) {
            doedit(request, response);
		}else{
			list(request, response);
		}    
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		int allnum=service.getNewsCount();
		List<News> list = service.getNewsByPage(pageindex, MyUtils.pagesize);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("newslist.jsp").forward(request, response);
	}
	
	protected void toadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 TopicService tservice=new TopicService();
		 List<Topic> topics= tservice.getAllTopics();
		 request.setAttribute("topics", topics);
		 request.getRequestDispatcher("newsadd.jsp").forward(request, response);
	}
	
	protected void doadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("context");
		String topicid = request.getParameter("selTopic");
		Topic t = new Topic();
		t.setTopicid(Integer.valueOf(topicid));
		News news = new News();
		news.setTitle(title);
		news.setAuthor(author);
		news.setTopic(t);
		news.setContent(content);
		NewsService service = new NewsService();
		int result = service.addNews(news);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('Ìí¼ÓÊ§°Ü'); history.go(-1);</script>");
		} else if (result == -1) {
			out.print("<script>alert('ÒÑ¾­´æÔÚ,Çë¸ü»»!'); history.go(-1)</script>");
		} else {
			response.sendRedirect("donews?op=list");
		}
	}
	
	protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		  String id= request.getParameter("id");
		  String tid = request.getParameter("tid");
		  NewsService service=new NewsService();
		  int result=service.dropNewsById(Integer.valueOf(id),Integer.valueOf(tid));
		  PrintWriter out = response.getWriter();
		  if(result==0){
		    out.print("<script>alert('É¾³ýÊ§°Ü');history.go(-1);</script>");
		  }else{
		    response.sendRedirect("donews?op=list");
		  }
	}
	
	protected void toedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		NewsService service=new NewsService();
		News news= service.getNewsById(Integer.valueOf(id));
		 TopicService tservice=new TopicService();
		 List<Topic> topics= tservice.getAllTopics();
		 request.setAttribute("news", news);
		 request.setAttribute("topics", topics);
		 request.getRequestDispatcher("newsedit.jsp").forward(request, response);
	}
	
	protected void doedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("context");
		String topicid = request.getParameter("selTopic");
		String newsid = request.getParameter("newsid");
		String tid= request.getParameter("tid");
		Topic t = new Topic();
		t.setTopicid(Integer.valueOf(topicid));
		News news = new News();
		news.setTitle(title);
		news.setAuthor(author);
		news.setTopic(t);
		news.setContent(content);
		news.setNewsid(Integer.valueOf(newsid));
		NewsService service = new NewsService();
		int result = service.editNews(news,Integer.valueOf(tid));
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('ÐÞ¸ÄÊ§°Ü'); history.go(-1);</script>");
		}else {
			response.sendRedirect("donews?op=list");
		}
	}
}
