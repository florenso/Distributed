package servlets;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.CalculatorBean;
@SuppressWarnings("serial")
public class CalculateServlet extends HttpServlet
{
   public void init() throws ServletException
   {
      super.init();
      log("servlet init");
   }
   
   public void destroy()
   {
      log("servlet destroy");
      super.destroy();
   }
   
   public void doGet(HttpServletRequest request,HttpServletResponse response)
                                         throws ServletException, IOException
   {
	  Boolean validInput = false;
	  String username = request.getParameter("username");
	  String password = request.getParameter("password");
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy:HH-mm-ss");
	  log("username:\t" + username + ", password:\t" + password);
	  String file = "userdata.txt";
	  File f = new File(file);
	  List<String> lines = new ArrayList<String>();
	  
	  if(!f.exists())	{
		  log("File " + file + " does not exist!");
//		  FileOutputStream fos = new FileOutputStream(file);
		  Date today = Calendar.getInstance().getTime();
		  String dateStr = dateFormat.format(today);
//		  String writeNewUser = "<usertest> <passwordtest> <" + dateStr + ">";
//		  fos.write(writeNewUser.getBytes());
//		  fos.close();
		  try{
			  	log("Writing and creating file...");
			    PrintWriter writer = new PrintWriter(file, "UTF-8");
			    writer.println("<usertest> <passwordtest> <" + dateStr + ">");
			    writer.flush();
			    writer.close();
			    log("Writing to file complete!");
			} catch (IOException e) {
			   // do something
				e.printStackTrace();
			}
		  //throw new FileNotFoundException("Please create the file and fill it with user data format: <username> <password> <timestamp>");
	  }
	  else	{
		  log("File:\t" + file + " exists!");
		  log("Absolute path to file:\t" + f.getAbsolutePath());
	  }
	  String url = "";
	  String lastDate = "";
	  log("date:\t" + dateFormat);
	  //DateFormat format = new SimpleDateFormat("dd-MM-yy:HH-mm-ss", Locale.ENGLISH);
	  // Validate User
	  
	  try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    log("Reading file data...");
		    while ((line = br.readLine()) != null) {
		    	
		    	String splitStr[] = line.split(" ");
		    	log("Line:\t" + line);
		    	log("username:\t" + splitStr[0].substring(1, splitStr[0].length() - 1) + " ,password:\t" + splitStr[1].substring(1, splitStr[1].length() - 1));
		    	if(username.equals(splitStr[0].substring(1, splitStr[0].length() - 1)) && password.equals(splitStr[1].substring(1, splitStr[1].length() - 1)))	{
		    		// Username and password have been validated
		    		log("Correct username and password, match found!");
		    		Date dt = dateFormat.parse(splitStr[2].substring(1, splitStr[2].length() - 1));
		    		log("retrieved date:\t" + dt);
		    		// Go to next page
		    		lastDate = splitStr[2].substring(1, splitStr[2].length() - 1);
		    		validInput = true;
					Date today = Calendar.getInstance().getTime();
					String dateStr = dateFormat.format(today);
					line = splitStr[0] + " " + splitStr[1] + " <" + dateStr + ">";
		    	}
		       // process the line.
		    	lines.add(line);
		    }
		    br.close();
	  }catch(Exception e)	{
		  
	  }
	  // user, password match not found show failed and repeat
	  
	  CalculatorBean cb = new CalculatorBean();
	  cb.setUsernamevalue(username);
	  cb.setPasswordvalue(password);
	  cb.setLastdatevalue(lastDate);
	  request.setAttribute("calc", cb);
	  
	  url = "WEB-INF/jsp/calculate.jsp";
	  
	  if(validInput)	{
		  url = "WEB-INF/jsp/calculate.jsp";
		  RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		  dispatcher.forward(request, response);
		  f.delete();
		  PrintWriter writer = new PrintWriter(file, "UTF-8");
		  for(String line : lines)	{
			  writer.println(line);
		  }
		  writer.flush();
		  writer.close();    
	  }
	  else	{
		  url = "WEB-INF/jsp/formdemo.jsp";
		  RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		  dispatcher.forward(request, response);
	  }
//	  cb.setXvalue(sx);
//	  cb.setYvalue(sy);
//	  
//	  log("retrieved x and y values");
//	  if(cb.isValid())
//	  {
//		  request.setAttribute("calc", cb);
//		  url = "WEB-INF/jsp/calculate.jsp";
//	  }
//	  else	{
//		  
//		  
//		  request.setAttribute("calc", cb);
//		  url = "WEB-INF/jsp/formdemo.jsp";
//	  }
		  //url = "WEB-INF/error.html";
		  //Return to same html page but with error result
	  

  }
}

