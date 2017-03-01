package com.stusystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req=(HttpServletRequest) request;
		 HttpServletResponse res=(HttpServletResponse) response;
		 HttpSession session=req.getSession();
		 Object object=session.getAttribute("loginuser");
		 String path=req.getRequestURI();
		 if (object==null) {
			res.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
			chain.doFilter(req, res);
		}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
	}
}
