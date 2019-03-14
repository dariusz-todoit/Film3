package com.example;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FilmServlet")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @EJB
  FilmInterface hw;  
	
  public FilmServlet() {
    super();
  }

	
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String result = "Servlet asks EJB and gets response: ";
    
    
    
    for (Object[] row : hw.getNotes()) {
      result = result + "\n " + (Integer) row[0] + " " + (String) row[1] + " " + (Integer) row[2];
    }
    
   
    
    /*
    
    result = result + "\n\n Criteria API";
    
    for (Note n : hw.getNotesWithCriteriaApi ()) {
      result = result + "\n " + n.noteId + " " + n.note;
    } 
    
    */
        
    response.getWriter().append(result);
	}

}
