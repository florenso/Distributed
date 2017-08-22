//Calculator.java
package calcws;

import java.math.BigInteger;

import javax.jws.WebService;

@WebService
public class Calculator {
    
    public BigInteger add(BigInteger a, BigInteger b) {
    	System.out.println("request add(" + a + "," + b + ")");
//    	int som = a+b;
    	BigInteger sum = a.add(b);
    	System.out.println("return " + sum);
        return sum;
    }
}