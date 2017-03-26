package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class IndexFilter
 */
@WebFilter("/indexFilter")
public class IndexFilter implements Filter {

	public IndexFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String pathInfo = req.getRequestURI().substring(req.getContextPath().length() + 1);
		if (!pathInfo.isEmpty() && !pathInfo.endsWith(".css") && !pathInfo.endsWith(".js")
				&& !pathInfo.endsWith("createUrl") && !pathInfo.endsWith("index")
				&& !pathInfo.endsWith("login") && !pathInfo.endsWith("redirect") && !pathInfo.endsWith("logout")) {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        res.setDateHeader("Expires", 0);
			req.setAttribute("url", pathInfo);
			req.getRequestDispatcher("redirect").forward(request, response);
		} else {
			res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	        res.setDateHeader("Expires", 0);
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
