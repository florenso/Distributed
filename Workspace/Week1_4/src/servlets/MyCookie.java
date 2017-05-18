package servlets;

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
