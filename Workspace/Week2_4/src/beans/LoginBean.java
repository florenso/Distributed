package beans;

import java.util.Calendar;

@SuppressWarnings("serial")
public class LoginBean {
	String username;
	String password;
	String lastlogin;
	//Todo get a datestamp of last login date
	
	public void setUsername(String username)	{
		if(username == null)
			throw new NullPointerException();
		this.username = username;
	}
	
	public String getName()	{
		return username;
	}
	
	public void setPassword(String password)	{
		if(password == null)
			throw new NullPointerException();
		this.password = password;
	}
	
	public String getPassword()	{
		return password;
	}
	
	public void setLastlogin(String date)	{
		//SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		//cal.setTime(sdf.parse("Mon Mar 14 16:02:37 GMT 2011"));
		lastlogin = date;
	}
	
	public String getLastlogin()	{
		return lastlogin;
	}
}
