package project2;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class TestFailure {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testWithinTheRange() {
		boolean[] array = new boolean[4];
		array[0] = false;
		array[1] = true;
		array[2] = true;
		array[3] = false;
		int[] prop = new int[7];
		prop[0] = 1;
		prop[1] = 3;
		prop[2] = 0;
		prop[3] = 2;
		prop[4] = 2;
		prop[5] = 1;
		prop[6] = 0;
		SDES s = new SDES();
		boolean[] result = new boolean[] {true,false,false,true,true,true,false};
		assertEquals(result, s.expPerm(array, prop));
	}
	
	@Test
	public void testGetKey10()
	{
		Scanner scan = new Scanner(System.in);
		SDES s = new SDES();
		s.getKey10(scan);
	}

}
