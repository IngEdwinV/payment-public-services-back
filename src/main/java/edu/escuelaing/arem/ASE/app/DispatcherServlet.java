package edu.escuelaing.arem.ASE.app;

import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class DispatcherServlet extends DefaultServlet{
    private static final long serialVersionUID = 1L;

    
    public DispatcherServlet(AnnotationConfigWebApplicationContext context) {
        super();
    }
}
