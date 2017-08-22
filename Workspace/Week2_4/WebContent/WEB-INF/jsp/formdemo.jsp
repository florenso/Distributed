<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
<jsp:useBean id="calculator" class= "beans.CalculatorBean" />
<jsp:setProperty name = "calculator" property = "*" />
-->
<%! 
	public void jspInit()
	{
	    log("doFormDemo init");
	}   
%>

<html>
   <head>
      <title>calculate form page</title>
      <link href="mycss.css" rel="stylesheet" type="text/css"> 
   </head>
   <body>
         calculate form page
         '${calc["stringinvoer"]}'
         <hr>
         <br>
        	<!--          
    <jsp:getProperty name = "calculator" property = "usernamevalue" /> 
    <jsp:getProperty name = "calculator" property = "passwordvalue" />
    -->
        <form method="get" action="calculate">
            <table class = "center">
               <tr>
                  <td align="right">
                     usertest:
                  </td>
                  <td>
                  <input name="username" value=nothing size="15">
                  </td>
               </tr>
               <tr>
                  <td align="right">
                     passwordtest:
                  </td>
                  <td>
                     <input name="password" value=nothing size="15">
                  </td>
               </tr>
               <tr>
                  <td colspan = "2" align= "right">
                      <input type="submit" value="submit">
                  </td>
               </tr>   
            </table>
         </form>
   </body>
</html>