package com.stusystem.teacher.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stusystem.entity.Message;
import com.stusystem.service.MessageService;
import com.stusystem.utils.MyUtils;

public class MessageServlet extends HttpServlet {
	
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
		request.setCharacterEncoding("utf-8");
		String pagestr = request.getParameter("page");
		int pageindex = 1;
		if (pagestr != null && !"".equals(pagestr)) {
			pageindex = Integer.valueOf(pagestr);
		}
		MessageService service = new MessageService();
		int pagecount = service.getPageCount(MyUtils.pagesize);
		if (pageindex > pagecount) {
			pageindex = pagecount;
		}
		int allnum=service.getCount();
		List<Message> list = service.getAllMessageByPage(pageindex, MyUtils.pagesize);
		request.setAttribute("list", list);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pageindex", pageindex);
		request.setAttribute("allnum", allnum);
		request.getRequestDispatcher("messagelist.jsp").forward(request, response);
	}
	
	protected void toadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 request.getRequestDispatcher("messageadd.jsp").forward(request, response);
	}
	
	protected void doadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("context");
		Message message= new Message();
		message.setTitle(title);
		message.setContent(content);
		MessageService service = new MessageService();
		int result = service.addMessage(message);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('Ìí¼ÓÊ§°Ü'); history.go(-1);</script>");
		} else if (result == -1) {
			out.print("<script>alert('ÒÑ¾­´æÔÚ,Çë¸ü»»!'); history.go(-1)</script>");
		} else {
			response.sendRedirect("domessage?op=list");
		}
	}
	
	protected void dodel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		  String id= request.getParameter("id");
		  MessageService service = new MessageService();
		  int result=service.dropMessage(Integer.valueOf(id));
		  PrintWriter out = response.getWriter();
		  if(result==0){
		    out.print("<script>alert('É¾³ýÊ§°Ü');history.go(-1);</script>");
		  }else{
		    response.sendRedirect("domessage?op=list");
		  }
	}
	
	protected void toedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("id");
		 MessageService service = new MessageService();
		 Message message = service.getMessageById(Integer.valueOf(id));
		 request.setAttribute("message", message);
		 request.getRequestDispatcher("messageedit.jsp").forward(request, response);
	}
	
	protected void doedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("context");
		String messageid = request.getParameter("messageid");
		Message message = new Message();
		message.setTitle(title);
		message.setContent(content);
		message.setId(Integer.valueOf(messageid));
		MessageService service = new MessageService();
		int result = service.editMessage(message);
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.print("<script>alert('ÐÞ¸ÄÊ§°Ü'); history.go(-1);</script>");
		}else {
			response.sendRedirect("domessage?op=list");
		}
	}
}
