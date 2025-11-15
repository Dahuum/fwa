
package fr._42.cinema.listeners;

import fr._42.cinema.config.ApplicationConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        
        // Create Spring context
        ApplicationContext springContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        // Make it available to all servlets
        servletContext.setAttribute("springContext", springContext);
        
        System.out.println("Spring ApplicationContext initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Spring ApplicationContext destroyed!");
    }
}