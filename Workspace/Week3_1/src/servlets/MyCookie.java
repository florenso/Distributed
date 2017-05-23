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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Calendar c = new GregorianCalendar();
		
		String naam = "jan";
		String wachtwoord = "woop";
		
		
		
		try {
			//OPEN DATABASE
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:database/week3_1.sqlite";
			Connection con = DriverManager.getConnection(url);

			
			//CREATE TABLE
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create table if not exists USERS(" + "LIDNR integer not null," + "NAAM text not null,"
					+ "WACHTWOORD text not null," + "LOGINS integer not null," + "LAASTE_LOGIN text not null,"
					+ "primary key (LIDNR)," + "unique(NAAM));");

			
			//INSERT USER
			PreparedStatement pstmt = null;
			String query = "insert or ignore into USERS(NAAM,WACHTWOORD,LOGINS,LAASTE_LOGIN)\n" + "values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, naam);
			pstmt.setString(2, wachtwoord);
			pstmt.setInt(3, 0);
			pstmt.setString(4, "" + c.getTime());
			pstmt.executeUpdate();


			
			//GET LOGINS AND LAASTE_LOGIN from a USER
			int logins = 0;
			String laaste_login = "";
			
			PreparedStatement stmt1 = con.prepareStatement("select LOGINS, LAASTE_LOGIN FROM USERS WHERE NAAM = ?;");
			stmt1.setString(1, naam);
			java.sql.ResultSet rs = stmt1.executeQuery();
			java.sql.ResultSetMetaData rsmd;
			rsmd = rs.getMetaData();
			if(rs.next()){
				logins = rs.getInt(1);
				laaste_login = rs.getString(2);
			}
			

			//UPDATE VALUES of a user.
			PreparedStatement pstmt1 = null;
			String query1 = "UPDATE USERS SET LOGINS = ? , LAASTE_LOGIN = ? WHERE NAAM = ? ;";
			pstmt1 = con.prepareStatement(query1);
			pstmt1.setInt(1, ++logins);
			pstmt1.setString(2, "" + c.getTime());
			pstmt1.setString(3, naam);
			pstmt1.executeUpdate();

			
			//CLOSE DATABASE
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(c.getTime());

		// Cookie cookie = new Cookie("MyDate", ""+c.getTime());
		// response.addCookie(cookie);

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String s1 = "<!doctype html public \"-//w3c//dtd html 4.0 Transitional//EN\">\n" + "<html>\n" + "   <head>\n"
				+ "       <title>\n" + "          MyCookie\n" + "       </title>\n" + "   </head>\n"
				+ "   <body bgcolor=\"#8AAFED\">\n" + "      <center>\n" + "         <h1>\n" + "            MyCookie\n"
				+ "<br>\n";
		String s2 = "";
		String s3 = "\n" + "         </h1>\n" + "      </center>\n" + "   </body>\n" + "</html>\n";

		out.print(s1 + s2 + s3);
	}
}
