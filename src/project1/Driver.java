package project1;

import java.util.*;
/**  Test the BigNumber class.
 *   Cryptography Project 1
 *   @author (sdb)
 *   @version (Sep 2012)
 */
public class Driver
{	
  public static void main (String [] args)
     {
	Scanner scanner = new Scanner (System.in);
	BigNumber x,y;

	System.out.println ("Enter a BigNumber, or hit Enter to terminate");
	String line = scanner.nextLine();

	while (line.length() > 0)
	  {	x = new BigNumber (line);
		System.out.println ("Enter a second BigNumber");
		line = scanner.nextLine();
		y = new BigNumber (line);

		System.out.println ("Sum: " + x.add(y));
		System.out.println ("Sum: " + y.add(x));
		System.out.println ("First - Second: " + x.subtract(y));
		System.out.println ("Second - First: " + y.subtract(x));
		System.out.println ("Product: " + x.multiply(y));
		System.out.println ("Product: " + y.multiply(x));
		System.out.println ("First / Second: " + x.divide(y));
		System.out.println ("Second / First: " + y.divide(x));
		System.out.println ("First % Second: " + x.mod(y));
		System.out.println ("Second % First: " + y.mod(x));
		System.out.println("Factors of first: " + x.factorsAsString(x.factor()));
		System.out.println("Factors of second: " + y.factorsAsString(y.factor()));
//	   
//		line = scanner.nextLine();
	   }
		scanner.close();
     }
}
	
