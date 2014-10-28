package project2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * The second part of the SDES class
 * 
 * @author Tony Toscano
 *
 */
public class SDES {
	private boolean[] tenKeys;
	
	

	/**
	 * Expand and/or permute and/or select from the bit array, inp,
	 * producing an expanded/permuted/selected bit array.
	 * @param inp	A bit array represented as booleans, true=1, false=0
	 * @param epv	an expansion and/or permutation and/or selection vector;
	 * all numbers must be in the range 0..inp.length, i.e.
	 * they must be valid subscripts for inp.
	 * @return the permuted/expanded/selected bit array
	 * @throws IndexOutOfBoundsException if an element in epv is less than 0 or
	 * greater than inp.length
	 */
	public boolean[] expPerm(boolean[] inp, int[] epv)
	{
		// Key assumption inp[] will always have a length of 4
		// 1. Create a new array with 8 containers for booleans
		boolean[] expPerm = new boolean[7];
		// 2. Loop through the epv array, and retrieve the ith element
		for(int i = 0; i < epv.length; i++){
			int digitNum = epv[i];
			// a. Inspect ith element for a valid subscript 
			boolean withinTheRange = checkIntForRange(digitNum,inp.length);
			// b. If true, process the digitNum, retrieve the boolean value at inp[bitPosition]
			// and add it to the expPerm array
			if(withinTheRange){
				int bitPosition = digitNum;
				boolean boolValue = inp[bitPosition];
				expPerm[i] = boolValue;
			}
			// c. Else False, it's not a valid subscript
			else {
				throw new IndexOutOfBoundsException();
			}
		}
		// 3. Return expPerm which will contain inp's inputs in the positions that epv gave.
		return expPerm;
	}
	
	/**
	 * Get a ten bit key from the keyboard.  Store it as an array of booleans in a field.
	 * @param scanner	The Scanner from which the input will be processed.
	 */
	public void getKey10(java.util.Scanner scanner) {
		// Key assumption, the key will always be 10 digits long
		// 1. Initialize an array that will hold the 10-bit key
		boolean[] key = new boolean[10];
//		Pattern p = Pattern.compile("/^[01]?$");
//		scanner.findInLine(p);
//		MatchResult result = scanner.match();
//		for (int i = 0; i < result.groupCount(); i++){
//			String s = result.group(i);
//			int digit = Integer.parseInt(s);
//			if(digit == 0)
//			{
//				key.add(false);
//			} else {
//				key.add(true);
//			}
//			
//			tenKeys = toPrimitiveArray(key);
		
	}
//	/**
//	 * 
//	 * @param inp
//	 * @param epv
//	 * @return
//	 */
//	public boolean[] expPerm(boolean[] inp, int[] epv) {
//		ArrayList<Boolean> ep = new ArrayList<Boolean>();
//		for (int i = 0; i < epv.length; i++) {
//			int verify = epv[i];
//			int inputLength = inp.length;
//			boolean withinTheRange = checkIntForRange(verify, inputLength);
//			if (withinTheRange) {
//				int bitPosition = verify;
//				boolean boolValue = inp[bitPosition];
//				ep.add(boolValue);
//			}
//		}
//		boolean[] outputArray = toPrimitiveArray(ep);
//		return outputArray;
//	}


//		Matcher m = p.matcher(scanner.)
//		String s = scanner.findInLine(p);
//		while (m.find()) {
//		// digits.add(Integer.parseInt(m.group()));
//		s = m.group();	// Will find only numbers in the String and group them together
//		}


//			System.out.println(result.group(i));
			


	public java.lang.String byteArrayToString(byte[] inp) {
		String decodeUTF8 = null;
		try {
			decodeUTF8 = new String(inp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decodeUTF8;
	}

	public boolean[] lh(boolean[] inp) {
		boolean[] lh = new boolean[inp.length / 2];
		for (int i = 0; i < inp.length / 2; i++) {
			boolean donor = inp[i];
			lh[i] = donor;
		}
		return lh;
	}

	public boolean[] xor(boolean[] x, boolean[] y) {
		ArrayList<Boolean> xor = new ArrayList<Boolean>();
		for (int i = 0; i < x.length && i < y.length; i++) {
//			if (x[i] == y[i]) {
//				xor.add(false);
//			} else {
//				xor.add(true);
//			}
			xor.add(x[i] ^ y[i]);
		}
		boolean[] outputArray = toPrimitiveArray(xor);
		return outputArray;
	}

	public boolean[] rh(boolean[] inp) {
		ArrayList<Boolean> rh = new ArrayList<Boolean>();
		for (int i = inp.length / 2; i < inp.length / 2; i++) {
			boolean donor = inp[i];
			rh.add(donor);
		}
		boolean[] outputArray = toPrimitiveArray(rh);
		return outputArray;
	}

	public boolean[] concat(boolean[] x, boolean[] y) {
		int xLen = x.length;
		int yLen = y.length;
		boolean[] c = new boolean[xLen + yLen];
		System.arraycopy(x, 0, c, 0, xLen);
		System.arraycopy(y, 0, c, xLen, yLen);
		return c;

	}

	public byte bitArrayToByte(boolean[] inp) {
		byte b = 0;
		for (int i = 0; i < inp.length; i++) {
			{
				boolean value = inp[i];
				b <<= 1;
				if (value == true) {
					b |= 1;
				}

			}

		}
		return b;
	}
	
	/**
	 * 
	 * @param b
	 * @param size
	 * @return
	 */
//	public boolean[] byteToBitArray(byte b, int size)
//	{
//		ArrayList <Boolean> bits = new ArrayList<Boolean>();
//		while(b >= 0){
//	}
//
	/**
	 * 
	 * @param booleanList
	 * @return
	 */
	private boolean[] toPrimitiveArray(ArrayList<Boolean> booleanList) {
		boolean[] primitives = new boolean[booleanList.size()];
		int index = 0;
		for (Boolean object : booleanList) {
			primitives[index++] = object;
		}
		return primitives;
	}

	/**
	 * 
	 * @param inspectedElement
	 * @param inputLength
	 * @return
	 */
	private boolean checkIntForRange(int inspectedElement, int inputLength) {
		if (inspectedElement <= inputLength && inspectedElement >= 0)
			return true;
		else
			return false;
	}

}
