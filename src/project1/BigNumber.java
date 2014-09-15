package project1;

import java.util.*;

/**
 * 
 * @author Tony Toscano, Anand Patel, Adam Jasper
 *
 */
public class BigNumber {
	
	ArrayList <String> digits;

	/**
	 * Constructor of the BigNumber class
	 * @param number 
	 */
	public BigNumber(String number)
	{
		digits = new ArrayList<String>();
		if(number.matches("[0-9]+") && number.length() > 2){
			for(int i = 0; i < number.length(); i++) {
				char c = number.charAt(i);
				String charToString = Character.toString(c);
				digits.add(charToString);
			}
//			digits = Arrays.asList(number);
		}
		
	}
	
	public void add(BigNumber two)
	{
//		StringBuilder buf = new StringBuilder();
//		int carry = 0;
//		for(int i = digits.size() - 1; i > 0; i--){
//			for(int j = two.digits.size() - 1; j > 0; j--){
//				int digit1 = i < 0 ? 0 : Integer.parseInt((digits.get(i)));
//				int digit2 = j < 0 ? 0 : Integer.parseInt((two.digits.get(j)));
//				int digit = digit1 + digit2 + carry;
//				
//			}
//		}
	}
	
	
}
