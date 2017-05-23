package servlets;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//MyCookie.java
import javax.servlet.http.Cookie;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.IOException;

import javax.servlet.ServletException;

import java.io.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MyCookie extends HttpServlet
{
   public void doGet(HttpServletRequest request,HttpServletResponse response)
                                         throws ServletException, IOException
   {

	   try {
		Class.forName("org.sqlite.JDBC");
	    String url = "jdbc:sqlite:database/week3_1.sqlite";
	    Connection con = DriverManager.getConnection (url);
	    
	    java.sql.ResultSet rs;
	    Statement stmt = con.createStatement();
	    int i = stmt.executeUpdate ("create table if not exists USERS("+
	    		"LIDNR integer not null,"+
	    		"NAAM text not null,"+
	    		"WACHTWOORD text not null,"+
	    		"LOGINS integer not null,"+
	    		"LAASTE_LOGIN text not null,"+
	    		"primary key (LIDNR),"+
	    		"unique(NAAM));");
		
	    
	    con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	   
	   
	   Calendar c = new GregorianCalendar();
       System.out.println(c.getTime());
	
       Cookie cookie = new Cookie("MyDate", ""+c.getTime());
       response.addCookie(cookie);
	   
      response.setContentType("text/html");
      
      PrintWriter out = response.getWriter();
      String s1 =
      "<!doctype html public \"-//w3c//dtd html 4.0 Transitional//EN\">\n" +
      "<html>\n" +
      "   <head>\n" +
      "       <title>\n" +
      "          MyCookie\n" +
      "       </title>\n" +
      "   </head>\n" +
      "   <body bgcolor=\"#8AAFED\">\n" +
      "      <center>\n" +
      "         <h1>\n" +
      "            MyCookie\n" +
      "<br>\n"; 
      Cookie[] cookies = request.getCookies();
      String s2;
      if (cookies == null) 
      {
   	   	s2 = "No cookies";
      } 
      else 
      {
    	  s2 = cookie.getValue();
      }
      
     String s3 = "\n" +
      "         </h1>\n" +
      "      </center>\n" +
      "   </body>\n" +
      "</html>\n";

      out.print(s1+s2+s3); 
   }
}
