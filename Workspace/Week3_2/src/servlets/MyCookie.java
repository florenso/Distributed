package servlets;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
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
public class MyCookie extends HttpServlet {

	private String laaste_login = "";
	int logins = 0;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	      String query = request.getParameter("query");
		
		String query_result = "";
	      if (query != null ) 
	      {
	  		try {
				//OPEN DATABASE
				File dir = new File("database");
				dir.mkdir();
				Class.forName("org.sqlite.JDBC");
				String url = "jdbc:sqlite:database/week3_1.sqlite";
				Connection con = DriverManager.getConnection(url);

				
				//TODO doe querry dingetjes
				PreparedStatement stmt1 = con.prepareStatement(query);
				if(stmt1.execute()){
					java.sql.ResultSet rs = stmt1.getResultSet();
					//todo make table
					//query_result = "here should be a table with stuff";
					query_result ="<table style=\"width:100%\">";
					ResultSetMetaData metadata = rs.getMetaData();
					int columnCount = metadata.getColumnCount();
					query_result +=  "<tr>";
					for (int i = 1; i <= columnCount; i++) {
						query_result += "<th>" + metadata.getColumnName(i) + "</th>";
					}
					query_result += "</tr>";
					
					while(rs.next()){
					query_result +=  "<tr>";
					for (int i = 1; i <= columnCount; i++) {
						query_result += "<td>" + rs.getString(i) + "</td>";
					}
					query_result += "</tr>";
					}
				
					query_result += "</table>";
				}else{
					query_result = "query executed";
				}
				
				
				//CLOSE DATABASE
				con.close();
			}
	  		catch (SQLException e) {
				query_result = "oeps, somthing went wrong. <br> " + e.getMessage();
				;
			} catch (ClassNotFoundException e) {
				query_result = "org.sqlite.JDBC not found....";
				e.printStackTrace();
			}
 
	      }else{
	      query = "select LOGINS, LAASTE_LOGIN, WACHTWOORD FROM USERS";
	      }
		

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();		
		
		String page_string = 		
				"<html>" +
				"<head>" +
				"<style>" +
				"table, th, td {" +
				"	border: 1px solid black;" +
				"	border-collapse: collapse;" +
				"}" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<h1>Database query voor database van week 3.1</h1>" +
				"<form action=\"EersteServlet_url\" method=\"get\">Query :" +
				    "<input name=\"query\" size=\"150\" type=\"text\" value=\"" + query + "\" />" +
				    "<input type=\"submit\" value=\"Execute Query\" />" +
				"</form>" +
				"<hr />" +
				"</body>" +
				"</html>" +
			query_result ;
		out.print(page_string);
		
		
	}

}
