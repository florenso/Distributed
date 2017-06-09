package client;
import java.math.BigInteger;

import calcws.*;

class CalculatorClient {
	
	public static void main(String args[])
	{
    	CalculatorService service = new CalculatorService();
        Calculator calculatorProxy = service.getCalculatorPort();

        BigInteger a = new BigInteger("10");
        BigInteger b = new BigInteger("20");         
	    BigInteger result = calculatorProxy.add(a,b);
        System.out.println(""+ a + " + " + b + " = " + result);

        a = new BigInteger("4294967295999999999999999");
	    b = new BigInteger("41");
        result = calculatorProxy.add(a,b);
        
        System.out.println("DIT IS DUS GROTER DAN EEN UNSIGNED INT WANT DIE GAAN MAAR TOT 4294967295 ");
        System.out.println(""+ a + " + " + b + " = " + result);
	}
}