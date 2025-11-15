
package fr._42.cinema.servlets;

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
import fr._42.cinema.models.AuthenticationHistory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        this.usersService = springContext.getBean(UsersService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Sign In - Cinema</title>");
        out.println("    <style>");
        out.println("        body { font-family: Arial; max-width: 400px; margin: 50px auto; padding: 20px; background: #f0f0f0; }");
        out.println("        .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        out.println("        h1 { color: #333; text-align: center; }");
        out.println("        input { width: 100%; padding: 10px; margin: 10px 0; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }");
        out.println("        button { width: 100%; padding: 10px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }");
        out.println("        button:hover { background: #218838; }");
        out.println("        .link { text-align: center; margin-top: 15px; }");
        out.println("        .link a { color: #007bff; text-decoration: none; }");
        out.println("        .error { color: red; text-align: center; margin-bottom: 10px; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🎬 Sign In</h1>");
        
        // Show error if exists
        String error = req.getParameter("error");
        if (error != null) {
            out.println("        <div class='error'>Invalid email or password!</div>");
        }
        
        out.println("        <form method='POST' action='/Cinema/signIn'>");
        out.println("            <input type='email' name='email' placeholder='Email' required>");
        out.println("            <input type='password' name='password' placeholder='Password' required>");
        out.println("            <button type='submit'>Sign In</button>");
        out.println("        </form>");
        out.println("        <div class='link'>");
        out.println("            <a href='/Cinema/signUp'>Don't have an account? Sign Up</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = usersService.signIn(email, password);

        if (user != null) {
            // Authentication successful - create session
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
           
            /* save login history */
            String ipAddress = req.getRemoteAddr();  // Get user's IP
            usersService.saveAuthenticationHistory(user.getId(), ipAddress);
            
            // Redirect to profile
            resp.sendRedirect("/Cinema/profile");
        } else {
            // Authentication failed - redirect back to login with error
            resp.sendRedirect("/Cinema/signIn?error=true");
        }
    }
}