<%@page import="java.io.Console,java.util.GregorianCalendar,java.util.Calendar,java.io.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%! 
Cookie cookie;
String s = "def";
public void myTest(HttpServletRequest request, HttpServletResponse response){

	
	Calendar c = new GregorianCalendar();
	cookie = new Cookie("MyDate", ""+c.getTime());
	log("reqeusting cookies...");
    Cookie[] cookies = request.getCookies();
    if (cookies == null) 
    {
 	   	s = "Generating new cookie";
 	    response.addCookie(cookie);
    } 
    else 
    {
  	  s = cookie.getValue();
  	  
    }

}
public void doGet(HttpServletRequest request2,HttpServletResponse response2)
        throws ServletException, IOException
{

	
}
public void randomTest()	{
	log("test ran");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
<% 
	myTest(request, response);
%>
</title>
</head>
<body>
<h1>
<% 
out.print(s);
%>
</h1>
</body>
</html>