package project1;

import java.util.*;

/**
 * 
 * @author Tony Toscano, Anand Patel, Adam Jasper
 *
 */
public class BigNumber {

	public ArrayList<Integer> digits;
	private final BigNumber ZERO = new BigNumber("00");
	private final BigNumber ONE = new BigNumber("01");

	/**
	 * Constructor of the BigNumber class
	 * 
	 * @param number
	 */
	public BigNumber(String number) {
		digits = new ArrayList<Integer>(number.length());
		checkNumber(number);
		if (number.matches("[0-9]+")) {
			for (int i = 0; i < number.length(); i++) {
				char c = number.charAt(i);
				String s = Character.toString(c);
				Integer digit = Integer.parseInt(s);
				digits.add(digit);
			}

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

		while (this.digits.size() > two.digits.size()) {
			checkNumber(two);
		}

		while (this.digits.size() < two.digits.size()) {
			checkNumber(this);
		}

		int carry = 0;
		BigNumber sum = new BigNumber("");
		String s = "";
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
			s += String.valueOf(digit);
		}

		for (int i = s.length() - 1; i >= 0; i--) {
			int rightSideUp = Integer.parseInt(Character.toString(s.charAt(i)));
			sum.digits.add(rightSideUp);
		}

		return sum;
	}

	/**
	 * 
	 * @param bigNumber
	 */
	private void checkNumber(BigNumber bigNumber) {
		int highOrderDigit = bigNumber.digits.get(0);
		if (highOrderDigit > 4) {
			bigNumber.digits.add(0, 9);
		}

		else {
			bigNumber.digits.add(0, 0);
		}

	}

	/**
	 * Subtract both BigNumbers
	 * 
	 * @param two
	 *            the second BigNumber in the equation
	 * @return the difference between both BigNumbers
	 */
	public BigNumber subtract(BigNumber two) {
		two.negate();
		return this.add(two);
	}

	/**
	 * Helper method for checking for a negative input
	 * 
	 * @param s
	 */
	private void checkNumber(String s) {
		int highOrderDigit;
		highOrderDigit = Integer.parseInt(Character.toString(s.charAt(0)));
		if (highOrderDigit > 4 || s.charAt(0) == '-') {
			// s.negate();
		}

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

//	public BigNumber multiply(BigNumber x) {
//		BigNumber product = new BigNumber(0);
//		// TODO check this. might/probably is off by 1
//		// also starts at 1 to ignore sign digit...if we do that
//		for (int i, j = 1; i < digits.length; i++, j++) {
//			// keeps counter right for each 10's place
//			int count = x[i] * Math.pow(10, j);
//			while (count != 0) {
//				product = product.add(digits);
//				count--;
//			}
//		}
//		return product;
//	}
//
//	public BigNumber divide(BigNumber x) {
//		BigNumber quotient = new BigNumber(0);
//		BigNumber temp = digits;
//		// TODO make sure compare to works this way
//		while (temp.compareTo(x) >= 0) {
//			temp = temp.subtract(x);
//			quotient = quotient.add(1);
//		}
//		// rest is remainder.
//		return quotient;
//	}
//
//	public BigNumber mod(BigNumber x) {
//		BigNumber temp = digits;
//		// TODO make sure compare to works this way
//		while (temp.compareTo(x) >= 0) {
//			temp = temp.subtract(x);
//		}
//		return temp;
//	}

	public ArrayList<BigNumber> factor(BigNumber x) {
		//return values
		ArrayList<BigNumber> factors = new ArrayList<BigNumber>();
		//prime to divide x by
		BigNumber prime = new BigNumber("02");
		//number of times x is divided by prime
		BigNumber counter = new BigNumber("00");
		//while x is > 1
		while(x.compareTo(ONE)==1){
			//while x%prime == 0 (prime divides in evenly)
			while ((x.mod(prime)).compareTo(ZERO)==0){
				//divide by the prime, and keep count of number of divisions
				x=x.divide(prime);
				counter=counter.add(ONE);
			}
			//adds the prime that was just divided by "counter" times to
			//the list of factors "counter" times.
			while(counter.compareTo(ZERO)==1){
				factors.add(prime);
				counter=counter.subtract(ONE);
			}
			//gets next prime after current prime number
			prime=getNextPrime(prime);
		}
		return factors;
	}
	
	private BigNumber getNextPrime(BigNumber x){
		if(checkPrime(x.add(new BigNumber("01")))==true){
			return x;
		}
		else return getNextPrime(x);
	}
	
	private boolean checkPrime(BigNumber x){
		//limit is a VERY rough estimate of when to stop checking primes (should be sqrt of x)
	    BigNumber limit = x.divide(new BigNumber("02"));
	    //start at 2, while i<=limit check it
	    for (BigNumber i = new BigNumber("02"); i.compareTo(limit)<1; i.add(new BigNumber("1"))) {
	    	//if it evenly divides by i, i is a factor, so it's not prime
	       if ((x.mod(i)) == 0){
	    	   return false;
	    }
	       //if it goes through all i's between 2 and limit without being evenly divided, it's prime
	    return true;
	    }
	}
}
