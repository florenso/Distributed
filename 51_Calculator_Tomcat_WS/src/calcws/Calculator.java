//Calculator.java
package calcws;

import java.math.BigInteger;

import javax.jws.WebService;

@WebService
public class Calculator {
    
    public BigInteger add(BigInteger a, BigInteger b) {
    	System.out.println("request add(" + a + "," + b + ")");
    	BigInteger som = a.add(b);
    	System.out.println("return " + som);
        return som;
    }
}