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
		checkNumber(number);
		if(number.matches("[0-9]+")){
			for(int i = 0; i < number.length(); i++) {
				char c = number.charAt(i);
				String charToString = Character.toString(c);
				digits.add(charToString);
			}

		}
		
	}
	
	/**
	 * Add together two BigNumbers
	 * @param two	the second BigNumber to add to this
	 * @return the sum of both BigNumbers
	 */
	public String add(BigNumber two)
	{
		
		StringBuilder buf = new StringBuilder();
		int carry = 0;
		for(int i = digits.size() - 1,  j = two.digits.size() - 1; (i>=0 && j>=0) || carry != 0; i--,j--)
		{
				int digit1 = i < 0 ? 0 : Integer.parseInt((digits.get(i)));
				int digit2 = j < 0 ? 0 : Integer.parseInt((two.digits.get(j)));
				int digit = digit1 + digit2 + carry;
				
				if(digit > 9) {
					carry = 1;
					digit -= 10;
				} else {
					carry = 0;
				}
				buf.append(digit);
		}
		return buf.reverse().toString();
	}
	
	/**
	 * Subtract both BigNumbers
	 * 
	 * @param two the second BigNumber in the equation
	 * @return the difference between both BigNumbers
	 */
	public String subtract(BigNumber two)
	{
//		two.negate();
		return this.add(two);
	}
	
	/**
	 * Helper method for checking for a negative input
	 * @param s
	 */
	private void checkNumber(String s)
	{
		int highOrderDigit;
		highOrderDigit = Integer.parseInt(Character.toString(s.charAt(0)));
		if(highOrderDigit > 4 || s.charAt(0) == '-')
		{
//			s.negate();
		}
		
		
	}
	
	/**
	 * Helper method to check for number length of all 
	 * @param bn1
	 * @param bn2
	 */
	private void checkNumberLength(BigNumber bn1, BigNumber bn2)
	{
	
	}
	
//	public void negate()
//	{
//		
//	}
	
	
}
