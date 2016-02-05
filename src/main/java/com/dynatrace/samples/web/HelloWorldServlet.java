package com.dynatrace.samples.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynatrace.samples.web.dao.DestinationDao;
import com.dynatrace.samples.web.listener.PersistenceListener;
import com.dynatrace.samples.web.model.Destination;

public class HelloWorldServlet extends HttpServlet {

  /**
   * Respond to a GET request for the content produced by this servlet.
   * 
   * @param request
   *          The servlet request we are processing
   * @param response
   *          The servlet response we are producing
   * 
   * @exception IOException
   *              if an input/output error occurs
   * @exception ServletException
   *              if a servlet error occurs
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute(PersistenceListener.EMF_ATTR_NAME);
    List<Destination> destinations = null;
    DestinationDao dao = new DestinationDao(emf);
    
    if (request.getParameter("create") != null) {
      dao.storeDestination(TestDataCreator.createTestData());
    } else if (request.getParameter("delete") != null) {
      dao.removeDestinations();
    }
    
    try {
      destinations = dao.getAllDestinations();
    } catch (Exception e) {
      e.printStackTrace();
      getServletContext().getRequestDispatcher("/error.jsp");
    }

    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.println("<html>");
    writer.println("<head>");
    writer.println("<title>Travel Destinations</title>");
    writer.println("</head>");
    writer.println("<body bgcolor=white>");

    writer.println("<table border=\"0\" cellpadding=\"10\">");
    writer.println("<th>Available destinations</th>");
    for (Destination destination : destinations) {
      writer.println("<tr>");
      writer.println("<td>");
      writer.print(destination.getTitle());
      writer.println("</td>");
      writer.println("</tr>");
    }
    writer.println("</table>");

    writer.println("<a href=\"?create\">Create Test Entries</a>");
    writer.println("<br/>");
    writer.println("<a href=\"?delete\">Remove all data</a>");
    writer.println("<br/>");
    writer.println("That's all!");
    writer.println("</body>");
    writer.println("</html>");
  }
}
