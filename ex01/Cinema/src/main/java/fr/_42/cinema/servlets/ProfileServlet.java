
package fr._42.cinema.servlets;

import fr._42.cinema.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            // Not authenticated - redirect to sign in
            resp.sendRedirect("/Cinema/signIn");
            return;
        }

        User user = (User) session.getAttribute("user");
        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>Profile - Cinema</title>");
        out.println("    <style>");
        out.println("        body { font-family: Arial; max-width: 600px; margin: 50px auto; padding: 20px; background: #f0f0f0; }");
        out.println("        .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }");
        out.println("        h1 { color: #333; text-align: center; }");
        out.println("        .info { margin: 20px 0; }");
        out.println("        .info p { font-size: 18px; margin: 10px 0; }");
        out.println("        .info strong { color: #007bff; }");
        out.println("        .logout { text-align: center; margin-top: 30px; }");
        out.println("        .logout a { display: inline-block; padding: 10px 20px; background: #dc3545; color: white; text-decoration: none; border-radius: 4px; }");
        out.println("        .logout a:hover { background: #c82333; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🎬 Profile</h1>");
        out.println("        <div class='info'>");
        out.println("            <p><strong>First Name:</strong> " + user.getFirstName() + "</p>");
        out.println("            <p><strong>Last Name:</strong> " + user.getLastName() + "</p>");
        out.println("            <p><strong>Email:</strong> " + user.getEmail() + "</p>");
        out.println("        </div>");
        out.println("        <div class='logout'>");
        out.println("            <a href='/Cinema/logout'>Logout</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
        
        out.close();
    }
}