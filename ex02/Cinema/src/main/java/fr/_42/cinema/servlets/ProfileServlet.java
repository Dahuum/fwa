
package fr._42.cinema.servlets;

import fr._42.cinema.models.AuthenticationHistory;
import fr._42.cinema.models.Image;
import fr._42.cinema.models.User;
import fr._42.cinema.services.UsersService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/Cinema/signIn");
            return;
        }

        User user = (User) session.getAttribute("user");
        
        // Get login history from database
        List<AuthenticationHistory> loginHistory = usersService.getAuthenticationHistory(user.getId());
        
        // Get user images from database
        List<Image> userImages = usersService.getUserImages(user.getId());
        
        // Set attributes for JSP
        req.setAttribute("user", user);
        req.setAttribute("loginHistory", loginHistory);
        req.setAttribute("userImages", userImages);
        
        // Forward to JSP
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }
}