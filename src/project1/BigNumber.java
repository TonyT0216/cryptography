package project1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Tony Toscano, Anand Patel, Adam Jasper
 *
 */
public class BigNumber {

	public ArrayList<Integer> digits;

	// private final BigNumber ZERO = new BigNumber("00");
	// private final BigNumber ONE = new BigNumber("01");

	/**
	 * Constructor of the BigNumber class
	 * 
	 * @param number
	 *            a String of numbers
	 */
	public BigNumber(String number) {
		digits = new ArrayList<Integer>(number.length()); // Initialize an
															// ArrayList of
															// Integers
		inspectString(number); // Check the string for numbers
	}

	/**
	 * 
	 * @param bn
	 */
	public BigNumber(BigNumber bn) {
		digits = new ArrayList<Integer>(bn.digits.size());
		for (int i = 0; i < bn.digits.size(); i++) {
			int num = bn.digits.get(i);
			digits.add(num);
		}
	}

	/**
	 * Helper method -- inspects the string for Objects other than letters The
	 * string will be processed, and then inserted into the ArrayList one
	 * character at a time.
	 * 
	 * @param number
	 *            the string that will be inspected
	 */
	private void inspectString(String number) {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(number);
		String s = " ";
		while (m.find()) {
			// digits.add(Integer.parseInt(m.group()));
			s = m.group();
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			String str = Character.toString(c);
			Integer digit = Integer.parseInt(str);
			digits.add(digit);
		}
	}

	/**
	 * This method will add two BigNumbers
	 * 
	 * @param two
	 *            the second BigNumber
	 * @return the sum of the two BigNumbers
	 */
	public BigNumber add(BigNumber two) {

		checkForDigitAmount(this, two); // Check to see if both BigNumbers being
										// added have a difference in the number
										// of digits
		int carry = 0;
		// BigNumber sum = new BigNumber("");
		StringBuilder sb = new StringBuilder();
		for (int i = digits.size() - 1, j = two.digits.size() - 1; (i >= 0 && j >= 0)
				|| carry != 0; i--, j--) {
			int digit1 = i < 0 ? 0 : (digits.get(i));
			int digit2 = j < 0 ? 0 : (two.digits.get(j));
			int digit = digit1 + digit2 + carry;

			if (digit > 9) {
				carry = 1;
				digit -= 10;
			} else {
				carry = 0;
			}
			sb.append(digit);
		}
		sb.reverse();
		String s = sb.toString();
		BigNumber sum = new BigNumber(s);
		// for (int i = s.length() - 1; i >= 0; i--) {
		// int rightSideUp = Integer.parseInt(Character.toString(s.charAt(i)));
		// sum.digits.add(rightSideUp);
		// }

		return sum;
	}

	/**
	 * Subtract both BigNumbers
	 * 
	 * @param two
	 *            the second BigNumber in the equation
	 * @return the difference between both BigNumbers
	 */
	public BigNumber subtract(BigNumber two) {
		BigNumber tensC = new BigNumber(two);
		tensC.negate();
		tensC = this.add(tensC);
//		return this.add(tensC);
		
		for(int i = tensC.size(); tensC.size() > digits.size(); i--)
		{
			tensC.removeHighestOrder();
		}
		return tensC;
	}

	private void removeHighestOrder() {
		// TODO Auto-generated method stub
		digits.remove(0);
	}

	// /**
	// * Helper method for checking for a negative input
	// *
	// * @param s
	// */
	// private void checkNumber(String s) {
	// int highOrderDigit;
	// highOrderDigit = Integer.parseInt(Character.toString(s.charAt(0)));
	// if (highOrderDigit > 4 || s.charAt(0) == '-') {
	// // s.negate();
	// }
	//
	// }

	private int size()
	{
		return digits.size();
	}
	/**
	 * @Override toString method
	 */
	public String toString() {
		String s = "";
		for (Integer digit : digits) {
			s = s + digit.toString();
		}
		// System.out.println(digit);}
		return s;
	}

	/**
	 * Calls toString on both of the BigNumbers and compares to see if they are
	 * equal
	 * 
	 * @param b
	 * @returns
	 */
	public boolean equals(BigNumber b) {
		if (toString().equals(b.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param y
	 * @returns: 0 if the BigNumbers are the same -1 if the passed in number is
	 *           smaller 1 if the passed in number is larger 2 if there is an
	 *           error
	 */
	int compareTo(BigNumber y) {
		if (equals(y)) {
			return 0;
		}
		if (digits.size() > y.digits.size()) {
			return 1;
		}
		if (digits.size() < y.digits.size()) {
			return -1;
		}
		if (digits.size() == y.digits.size()) {
			for (int i = 0; i < digits.size(); i++) {
				if (digits.get(i).compareTo(y.digits.get(i)) == 0)
					;
				else {
					return digits.get(i).compareTo(y.digits.get(i));
				}
			}
		}
		return 2;
	}

	/**
	 * negates the BigNumber
	 */
	public void negate() {
		boolean doneZeros = false;
		boolean doneFirst = false;
		for (int i = digits.size() - 1; i >= 0; i--) {
			if ((digits.get(i)) > 0) {
				doneZeros = true;
			}
			if (!doneFirst && doneZeros) {
				int temp = 10 - digits.get(i);
				digits.set(i, temp);
				// System.out.println("made it");
				doneFirst = true;
			} else if (doneFirst && doneZeros) {
				int temp = 9 - digits.get(i);
				digits.set(i, temp);
			}
		}
	}

	/**
	 * Removes extra zeroes from the front of BigNumbers
	 */
	public void normalize() {

		for (int j = 0; j <= digits.size() + 1; j++) {
			if (digits.get(0) == 0) {
				digits.remove(0);
			}
		}
	}

	/**
	 * A helper method to inspect both BigNumbers side by side to check and see
	 * if they are of equal digit length
	 * 
	 * @param bigNumber
	 *            A bigNumber of a certain length and value
	 * @param two
	 *            A bigNumber of a certain length and value
	 */
	private void checkForDigitAmount(BigNumber bigNumber, BigNumber two) {
		if (bigNumber.digits.size() == 1 && two.digits.size() == 1) {
			padNumbers(bigNumber);
			padNumbers(two);
		}
		while (bigNumber.digits.size() < two.digits.size()) {
			bigNumber.digits.add(0, 0);
		} // If bigNumber is smaller than two, make bigNumber equal in length to
			// two

		while (two.digits.size() < bigNumber.digits.size()) {
			two.digits.add(0, 0); // If two is smaller than bigNumber, make two
									// equal in length to bigNumber
		}

	}

	/**
	 * Multiplies a number with a number given as an argument
	 * 
	 * @param x
	 *            the second number to multiply by
	 * @returns the product of the two numbers
	 */
	public BigNumber multiply(BigNumber x) {
		BigNumber product = new BigNumber("00");
		// TODO check this. might/probably is off by 1
		for (int i=digits.size()-1, j=0; i>=0; i--, j++) {
			// keeps counter right for each 10's place
			int count = (int) ((digits.get((int) i)) * Math.pow(10, j));
			while (count > 0) {
				product = product.add(x);
				count--;
			}
		}
		return product;
	}
	//
	/**
	 * Divides a number by a number given as an argument
	 * 
	 * @param x
	 *            the divisor
	 * @returns the quotient
	 */
	// public BigNumber divide(BigNumber x) {
	// BigNumber quotient = new BigNumber(0);
	// BigNumber temp = digits;
	// // TODO make sure compare to works this way
	// while (temp.compareTo(x) >= 0) {
	// temp = temp.subtract(x);
	// quotient = quotient.add(1);
	// }
	// // rest is remainder.
	// return quotient;
	// }
	/**
	 * Mods a number by a number given as an argument
	 * 
	 * @param x
	 *            the modulous
	 * @returns the remainder after division
	 */
	// public BigNumber mod(BigNumber x) {
	// BigNumber temp = digits;
	// // TODO make sure compare to works this way
	// while (temp.compareTo(x) >= 0) {
	// temp = temp.subtract(x);
	// }
	// return temp;
	// }
	/**
	 * Creates a list of the prime factors of the given BigNumber
	 * 
	 * @param x
	 *            given BigNumber
	 * @returns the list of factors
	 */
	// public ArrayList<BigNumber> factor(BigNumber x) {
	// //return values
	// ArrayList<BigNumber> factors = new ArrayList<BigNumber>();
	// //prime to divide x by
	// BigNumber prime = new BigNumber("02");
	// //number of times x is divided by prime
	// BigNumber counter = new BigNumber("00");
	// //while x is > 1
	// while(x.compareTo(ONE)==1){
	// //while x%prime == 0 (prime divides in evenly)
	// while ((x.mod(prime)).compareTo(ZERO)==0){
	// //divide by the prime, and keep count of number of divisions
	// x=x.divide(prime);
	// counter=counter.add(ONE);
	// }
	// //adds the prime that was just divided by "counter" times to
	// //the list of factors "counter" times.
	// while(counter.compareTo(ZERO)==1){
	// factors.add(prime);
	// counter=counter.subtract(ONE);
	// }
	// //gets next prime after current prime number
	// prime=getNextPrime(prime);
	// }
	// return factors;
	// }
	//
	// private BigNumber getNextPrime(BigNumber x){
	// if(checkPrime(x.add(new BigNumber("01")))==true){
	// return x;
	// }
	// else return getNextPrime(x);
	// }

	// private boolean checkPrime(BigNumber x){
	// //limit is a VERY rough estimate of when to stop checking primes (should
	// be sqrt of x)
	// BigNumber limit = x.divide(new BigNumber("02"));
	// //start at 2, while i<=limit check it
	// for (BigNumber i = new BigNumber("02"); i.compareTo(limit)<1; i.add(new
	// BigNumber("1"))) {
	// //if it evenly divides by i, i is a factor, so it's not prime
	// if ((x.mod(i)) == 0){
	// return false;
	// }
	// //if it goes through all i's between 2 and limit without being evenly
	// divided, it's prime
	// return true;
	// }
	// }

	private void padNumbers(BigNumber bigNumber) {
		bigNumber.digits.add(0, 0);
		bigNumber.digits.add(0, 0);

	}

	/**
	 * 
	 * @return
	 */
	public int sign() {
		int highOrderDigit = digits.get(0);
		if (highOrderDigit > 4)
			return -1;
		else if (highOrderDigit <= 4 && highOrderDigit >= 1)
			return 1;
		else
			return 0;
	}
}
