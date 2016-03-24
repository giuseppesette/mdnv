package it.mdnv.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.mdnv.model.Utenti;

/**
 * Servlet Filter implementation class UserCheckFilter
 */
public class LoginCheckFilter extends AbstractFilter implements Filter {
	private static List<String> allowedURIs;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("[LoginCheckFilter][init] - ");
		if(allowedURIs == null){
			allowedURIs = new ArrayList<String>();
			allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			/**
			allowedURIs.add("/mdnvdemo/javax.faces.resource/main.css.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/theme.css.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/primefaces.js.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/primefaces.css.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/jquery/jquery.js.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/messages/messages.png.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
			allowedURIs.add("/mdnvdemo/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
			**/
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("[LoginCheckFilter][destroy] - ");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("[LoginCheckFilter][doFilter] - ");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.isNew()) {
			doLogin(request, response, req);
			return;
		}

		Utenti user = (Utenti) session.getAttribute("user");

		System.out.println("[LoginCheckFilter][doFilter] - URI: " + req.getRequestURI());
		
		if (user == null && !allowedURIs.contains(req.getRequestURI())) {
			System.out.println(req.getRequestURI());
			doLogin(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}
}