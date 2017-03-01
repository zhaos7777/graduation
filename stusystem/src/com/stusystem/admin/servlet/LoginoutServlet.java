package com.stusystem.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginoutServlet extends HttpServlet{
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   request.setCharacterEncoding("utf-8");
	   HttpSession session=request.getSession();
	   session.removeAttribute("loginuer");
	   response.sendRedirect("../login.jsp");
}
}
