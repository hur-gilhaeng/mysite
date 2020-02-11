package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoadListrner implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	ServletContext context = servletContextEvent.getServletContext();
    	//context.setAttribute(name, object); 이 문장은 설명용으로 사용하지 않는다. 삭제 예정 2020-02-05;
    	String contextConfigLocation = context.getInitParameter("contextConfigLocation");
    	
    	System.out.println("Application Starts... : "+contextConfigLocation);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
}
