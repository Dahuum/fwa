
package fr._42.cinema.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        // Public URLs (allowed without login)
        boolean isPublicUrl = path.endsWith("/signUp") || 
                              path.endsWith("/signIn") ||
                              path.endsWith("/logout") ||
                              path.equals("/Cinema/") ||
                              path.contains("/images/");  // ← Allow image serving
        
        // If logged in and trying to access signUp/signIn → redirect to profile
        if (isLoggedIn && (path.endsWith("/signUp") || path.endsWith("/signIn"))) {
            resp.sendRedirect("/Cinema/profile");
            return;
        }
        
        // If NOT logged in and trying to access protected page → 403
        if (!isPublicUrl && !isLoggedIn) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        
        // Everything OK → continue to servlet
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("AuthenticationFilter initialized!");
    }

    @Override
    public void destroy() {
        System.out.println("AuthenticationFilter destroyed!");
    }
}