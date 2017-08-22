package client;
import java.math.BigInteger;

import calcws.*;

class CalculatorClient {
	
	public static void main(String args[])
	{
    	CalculatorService service = new CalculatorService();
        Calculator calculatorProxy = service.getCalculatorPort();

        BigInteger a = new BigInteger("987654321987654321000000000");
        BigInteger b = new BigInteger("987654321987654321000000000");
          
        BigInteger result = calculatorProxy.add(a,b);
        System.out.println(""+ a + " + " + b + " = " + result);
	}
}