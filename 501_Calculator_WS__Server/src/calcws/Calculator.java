//Calculator.java
package calcws;

import javax.jws.WebService;

import java.math.BigInteger;

import javax.jws.WebParam;

@WebService
public class Calculator {
    
    public BigInteger add(@WebParam(name="a") BigInteger a, 
    		       @WebParam(name="b") BigInteger b) 
    {
    	System.out.println("request add(" + a + "," + b + ")");
    	BigInteger som = a.add(b);
    	System.out.println("return " + som);
        return som;
    }
}