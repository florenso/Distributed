package beans;

public class CalculatorBean 
{
	String stringinvoer = "";
    int xvalue = 0;
    int yvalue = 0;
    int status = 1;  // 1: success 0: error
    public String getStringinvoer()	{
    	return stringinvoer;
    }
    public void setXvalue(String sx)
    {
    	try
    	{
    		xvalue = Integer.parseInt(sx);
    	}
    	catch(Exception e)
    	{
    		stringinvoer = "x is ongeldig!";
    		status = 0;
    	}
    }
    
    public int getXvalue()
    {
    	return xvalue;
    }   
    
    public void setYvalue(String sy)
    {
    	try
    	{
    		yvalue = Integer.parseInt(sy);
    	}
    	catch(Exception e)
    	{
    		status = 0;
    		stringinvoer = "y is ongeldig!";
    	}    	
    }
    
    public int getYvalue()
    {
    	return yvalue;
    }  
        
    public int getSum() 
    {
        return xvalue + yvalue; 
    }
    
    public boolean isValid() 
    {
        return status == 1; 
    }
}
