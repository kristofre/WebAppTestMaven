package com.compuware.apm.samples.web.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PersistenceListener implements ServletContextListener {
  public static String EMF_ATTR_NAME = "emf";

  public void contextInitialized(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    context.setAttribute(EMF_ATTR_NAME,  entityManagerFactory);
  }

  public void contextDestroyed(ServletContextEvent sce) {
    ServletContext context = sce.getServletContext();
    EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute(EMF_ATTR_NAME);
    if (emf != null) {
      emf.close();
    }
  }
}
