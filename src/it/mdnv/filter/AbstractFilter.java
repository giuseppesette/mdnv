package it.mdnv.filter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AbstractFilter {

	public AbstractFilter() {
		super();
	}

	protected void doLogin(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		System.out.println("[AbstractFilter][doLogin] - ");
		//RequestDispatcher rd = req.getRequestDispatcher("/pages/public/login.xhtml");
		RequestDispatcher rd = req.getRequestDispatcher("/faces/login.xhtml");
		rd.forward(request, response);
	}
	
	protected void accessDenied(ServletRequest request, ServletResponse response, HttpServletRequest req) throws ServletException, IOException {
		System.out.println("[AbstractFilter][accessDenied] - ");
		//RequestDispatcher rd = req.getRequestDispatcher("/pages/public/accessDenied.xhtml");
		RequestDispatcher rd = req.getRequestDispatcher("/faces/accessDenied.xhtml");
		rd.forward(request, response);
	}
}