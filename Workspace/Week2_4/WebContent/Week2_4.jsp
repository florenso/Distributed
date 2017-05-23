<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
   <head>
      <title>Login Page</title>
      <link href="mycss.css" rel="stylesheet" type="text/css"> 
   </head>
   <body>
         <hr>
         <br>
        <form method="get" action="LoginServlet">
            <table class = "center">
               <tr>
                  <td align="right">
                     username:
                  </td>
                  <td>
                  <input name="username" value="" size="15">
                  </td>
               </tr>
               <tr>
                  <td align="right">
                     password:
                  </td>
                  <td>
                  <input name="password" value="" size="15">
                  </td>
               </tr>
               <tr>
                  <td colspan = "2" align= "right">
                      <input type="submit" value=" login ">
                  </td>
               </tr>   
            </table>
         </form>
   </body>
</html>