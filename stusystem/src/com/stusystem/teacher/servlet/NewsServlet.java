package com.stusystem.teacher.servlet;

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
		}else if ("content".equals(action)) {
            content(request, response);
		}
	}


	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String pagestr = request.getParameter("page");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		NewsService service = new NewsService();
		// 求总的页数pagecount
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
	
	
	protected void content(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		NewsService service = new NewsService();
		News news = service.getNewsById(Integer.valueOf(id));
		request.setAttribute("news", news);
		request.getRequestDispatcher("newsread.jsp").forward(request, response);
	}
	
}
