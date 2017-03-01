package com.stusystem.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stusystem.entity.AdminInfo;
import com.stusystem.service.AdminService;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	// TODO 自动生成的方法存根
    	request.setCharacterEncoding("utf-8");
    	String name=request.getParameter("txtUsername");
    	String pwd=request.getParameter("txtPassword");
    	String type=request.getParameter("type");
    	
    	AdminInfo admin=new AdminInfo(name,pwd,type);
    	AdminService service=new AdminService();
    	boolean result=service.login(admin);
    	if (result) {
			admin=service.getAdminByUsername(name);
			HttpSession session=request.getSession();
			session.setAttribute("loginuser", admin);
			if (admin.getType().equals("管理员")) {
				response.sendRedirect("admin/main.jsp");
			}else if (admin.getType().equals("教师")) {
				response.sendRedirect("teacher/main.jsp");
			}else if(admin.getType().equals("学生")){
				response.sendRedirect("student/main.jsp");
			}
			
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print("<script>alert('用户名、密码或用户类型错误');history.go(-1);</script>");
		}
    }
}
