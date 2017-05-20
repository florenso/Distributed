<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
        <form method="get" action="calculate">
            <table class = "center">
               <tr>
                  <td align="right">
                     x:
                  </td>
                  <td>
                  <input name="xvalue" value='${calc["xvalue"]}' size="15">
                  </td>
               </tr>
               <tr>
                  <td align="right">
                     y:
                  </td>
                  <td>
                     <input name="yvalue" value='${calc["yvalue"]}' size="15">
                  </td>
               </tr>
               <tr>
                  <td colspan = "2" align= "right">
                      <input type="submit" value=" tel op">
                  </td>
               </tr>   
            </table>
         </form>
   </body>
</html>