package servletPkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
/**
 * Servlet implementation class CurrentTime
 */
public class CurrentTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CurrentTime() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("In CurrentTime servlet");
		PrintWriter out = response.getWriter();
		String zone = request.getParameter("zone");
	    Date date = new Date();
	    DateFormat df = DateFormat.getInstance();
	
	    if (zone != null)
	    {
	    	TimeZone tz = TimeZone.getTimeZone(zone);
	    	df.setTimeZone(tz);
	    }
		
	    out.println(df.format(date));
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
