package servlets;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		
		
	      String naam = request.getParameter("username");
	      String wachtwoord = request.getParameter("password");
		
		
	      //check if login page
	      if (naam == null || wachtwoord == null) 
	      {
	    	  log("login null");
	    	  //no cookies, error page
	    	  PrintWriter out = response.getWriter();
	    	  String Login_page = "<html>" +
	    			   "<head>" +
	    			      "<title>Login Page</title>" +
	    			      "<link href=\"mycss.css\" rel=\"stylesheet\" type=\"text/css\">" +
	    			   "</head>" +
	    			   "<body>" +
	    			      "<hr>" +
	    			      "<br>" +
	    			      "<form method=\"get\" action=\"EersteServlet_url\">" +
	    			         "<table class = \"center\">" +
	    			            "<tr>" +
	    			               "<td align=\"right\">username:</td>" +
	    			               "<td><input name=\"username\" value=\"\" size=\"15\"></td>" +
	    			            "</tr>" +
	    			            "<tr>" +
	    			               "<td align=\"right\">password:</td>" +
	    			               "<td><input name=\"password\" value=\"\" size=\"15\"></td>" +
	    			            "</tr>" +
	    			            "<tr>" +
	    			               "<td colspan = \"2\" align= \"right\"><input type=\"submit\" value=\" login \"></td>" +
	    			            "</tr>" +
	    			         "</table>" +
	    			      "</form>" +
	    			   "</body>" +
	    			"</html>";
	    	  out.print(Login_page);
	    	  return;  
	      }
		
		
		try {
			//OPEN DATABASE
			File dir = new File("database");
			dir.mkdir();
			// maak een mapje database naast je eclipse.exe
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:database/week3_1.sqlite";
			Connection con = DriverManager.getConnection(url);


			//CREATE TABLE
			CreateTable(con);

			if(!CheckLogin(con,naam,wachtwoord)){
				con.close();//close db if password is wrong
				
		    	  PrintWriter out = response.getWriter();
		    	  String Login_page = "<html>" +
		    			   "<head>" +
		    			      "<title>Login Page</title>" +
		    			      "<link href=\"mycss.css\" rel=\"stylesheet\" type=\"text/css\">" +
		    			   "</head>" +
		    			   "<body>" +
		    			      "<hr>" +
		    			      "<br>" +
		    			      "<form method=\"get\" action=\"EersteServlet_url\">" +
		    			         "<table class = \"center\">" +
		    			            "<tr>" +
		    			               "<td align=\"right\">username:</td>" +
		    			               "<td><input name=\"username\" value=\"" + naam + "\" size=\"15\"></td>" +
		    			            "</tr>" +
		    			            "<tr>" +
		    			               "<td align=\"right\">password:</td>" +
		    			               "<td><input name=\"password\" value=\"\" size=\"15\"></td>" + " Wrong password"+
		    			            "</tr>" +
		    			            "<tr>" +
		    			               "<td colspan = \"2\" align= \"right\"><input type=\"submit\" value=\" login \"></td>" +
		    			            "</tr>" +
		    			         "</table>" +
		    			      "</form>" +
		    			   "</body>" +
		    			"</html>";
		    	  out.print(Login_page);
		    	  return;  
			}
			
			//GET LOGINS AND LAASTE_LOGIN from a USER
			UpdateLastLogin(con, naam, wachtwoord);
			
			//CLOSE DATABASE
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String bericht1 = "laaste_login: " + laaste_login ;
		String bericht2 = "logins: " + logins;
		

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();		
		
		String page_string = "<!doctype html public \"-//w3c//dtd html 4.0 Transitional//EN\">" +
		"<html>" +
		   "<head>" +
		       "<title>" +
		          "LaasteLogin" +
		      "</title>" +
		   "</head>" +
		   "<body bgcolor=\"#8AAFED\">" +
		      "<center>" +
		         "<h1>" +
		            "Hallo " + naam +
		            "<br>" +
					bericht1 +
					"<br>" +
					bericht2 +
		         "</h1>" +
		      "</center>" +
		   "</body>" +
		"</html>";
		
		out.print(page_string);
		
		
	}

	private void UpdateLastLogin(Connection con, String naam, String wachtwoord) throws SQLException {

		
		//UPDATE VALUES of a user.
		Calendar c = new GregorianCalendar();
		PreparedStatement pstmt1 = null;
		String query1 = "UPDATE USERS SET LOGINS = ? , LAASTE_LOGIN = ? WHERE NAAM = ? ;";
		pstmt1 = con.prepareStatement(query1);
		pstmt1.setInt(1, ++logins);
		pstmt1.setString(2, "" + c.getTime());
		pstmt1.setString(3, naam);
		pstmt1.executeUpdate();
	}

	private boolean CheckLogin(Connection con, String naam, String wachtwoord) throws SQLException {	
		String dp_wachtwoord = "";
		PreparedStatement stmt1 = con.prepareStatement("select LOGINS, LAASTE_LOGIN, WACHTWOORD FROM USERS WHERE NAAM = ?;");
		stmt1.setString(1, naam);
		java.sql.ResultSet rs = stmt1.executeQuery();
		//java.sql.ResultSetMetaData rsmd;
		//rsmd = 
		rs.getMetaData();
		if(rs.next()){
			logins = rs.getInt(1);
			laaste_login = rs.getString(2);
			dp_wachtwoord = rs.getString(3);
		}else{
			//INSERT USER
			InsertUser(con, naam, wachtwoord);
			logins = 0;
			laaste_login = "never loged in before";
			return true;
		}	
		
		return dp_wachtwoord.equals(wachtwoord);
		
	}

	private void InsertUser(Connection con, String naam, String wachtwoord) throws SQLException {
		Calendar c = new GregorianCalendar();
		PreparedStatement pstmt = null;
		String query = "insert or ignore into USERS(NAAM,WACHTWOORD,LOGINS,LAASTE_LOGIN)\n"
				+ "values(?, ?, ?, ?)";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, naam);
		pstmt.setString(2, wachtwoord);
		pstmt.setInt(3, 0);
		pstmt.setString(4, "" + c.getTime());
		pstmt.executeUpdate();
	}

	private void CreateTable(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create table if not exists USERS(" + "LIDNR integer not null,"
				+ "NAAM text not null," + "WACHTWOORD text not null," + "LOGINS integer not null,"
				+ "LAASTE_LOGIN text not null," + "primary key (LIDNR)," + "unique(NAAM));");
	}
}
