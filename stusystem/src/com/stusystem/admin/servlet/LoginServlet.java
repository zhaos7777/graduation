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
    	// TODO �Զ����ɵķ������
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
			if (admin.getType().equals("����Ա")) {
				response.sendRedirect("admin/main.jsp");
			}else if (admin.getType().equals("��ʦ")) {
				response.sendRedirect("teacher/main.jsp");
			}else if(admin.getType().equals("ѧ��")){
				response.sendRedirect("student/main.jsp");
			}
			
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print("<script>alert('�û�����������û����ʹ���');history.go(-1);</script>");
		}
    }
}
