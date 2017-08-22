package beans;

public class CalculatorBean 
{
	String stringinvoer = "";
    int status = 1;  // 1: success 0: error
    
    String username;
    String password;
    String lastdate;
    public String getStringinvoer()	{
    	return stringinvoer;
    }
    public void setUsernamevalue(String sx)
    {
    	username = sx;
    }
    
    public String getUsernamevalue()
    {
    	return username;
    }   
    
    public void setPasswordvalue(String sy)
    {
    	password = sy;
    }
    
    public String getPasswordvalue()
    {
    	return password;
    }  
    public void setLastdatevalue(String ld)
    {
    	lastdate = ld;
    }
    
    public String getLastdatevalue()
    {
    	return lastdate;
    }   
    public String getSum() 
    {
        return username + password; 
    }
    
    public boolean isValid() 
    {
        return status == 1; 
    }
}
