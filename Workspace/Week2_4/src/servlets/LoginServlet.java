package servlets;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException
   {
      log("entering doGet");
      // Creating a bean with the login credentials 
      LoginBean lb = new LoginBean();
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      lb.setUsername(username);
      lb.setPassword(password);
      
      String url = "";
      // Validating the login credentials
      
      HashMap<String, UserData> Users = readFile("accounts.acc");
      if(Users.containsKey(username))  {
         if(Users.get(username).password.equals(password))  {
        	 log("log in accepted");
        	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm", Locale.ENGLISH);
            Calendar cal = Calendar.getInstance();
        	Users.get(username).lastlogin = cal;
        	writeFile(Users, "accounts.acc");
        	
            url = "WEB-INF/jsp/validlogin.jsp";
            log("redirecting to validlogin webpage");
         }
         else{
        	 log("invalid password");
        	 url = "Week2_4.jsp";
         }
      }
      else  {
    	  log("invalid username");
         url = "Week2_4.jsp";
      }
      
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
      
   }
   private void writeFile(HashMap<String, UserData> Users, String file)	{
	   
	   Path path = FileSystems.getDefault().getPath("", file);
	   //Remove file
	   try {
		   
		    Files.delete(path);
		    
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	   //Write all users with updated date to file
	   String s = "";
	   
	    Iterator it = Users.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        UserData us = (UserData)pair.getValue();
	        
	        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
	        String formatted = format1.format(us.lastlogin.getTime());
	        
	        s += us.username + " " + us.password + " " + formatted + "\n";
	    }
	    
	    
	   byte[] lines = s.getBytes();
	try {
		Files.write(path, lines);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   private HashMap<String, UserData> readFile(String file)  {
      Path path = FileSystems.getDefault().getPath("", file);
      HashMap<String /* username */, UserData /* Userdata object */> Users= new HashMap<String, UserData>(); 
      
      Consumer<String> myConsumer = new Consumer<String>() {
         
         public String username = "INVALID";
         public String password = "INVALID";
         
         
         @Override
         public void accept(String t) {
            // TODO Auto-generated method stub
            String[] splittedString = t.split(" ");
            UserData newUser = new UserData();
            newUser.username = splittedString[0];
            newUser.password = splittedString[1];
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH/mm");
            try {
				newUser.lastlogin.setTime(sdf.parse(splittedString[2]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Users.putIfAbsent(splittedString[0], newUser);
            
         }
      };
      try {
         Files.readAllLines(path).forEach(myConsumer);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (NullPointerException e){
         
      }
      
      return Users;
      
   }

}
