package it.mdnv.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


// @WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void destroy() {
		System.out.println("[JPAFilter][destroy]");
		this.entityManagerFactory.close();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("[JPAFilter][doFilter]");
		EntityManager entityManager = this.entityManagerFactory
				.createEntityManager();
		req.setAttribute("entityManager", entityManager);

		entityManager.getTransaction().begin();

		fc.doFilter(req, res);

		try {
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new ServletException(e.toString());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("[JPAFilter][init]");
		this.entityManagerFactory = Persistence.createEntityManagerFactory("mdnv");
	}
}