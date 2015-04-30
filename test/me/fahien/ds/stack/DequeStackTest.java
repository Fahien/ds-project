package me.fahien.ds.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** DequeStack Test case
 * @author Fahien */
public class DequeStackTest {
	@Test public void simpleTest() {
		Stack<Integer> stack = new DequeStack<>();
		stack.push(1);
		assertEquals(stack.top(), new Integer(1));
	}

	@DataProvider public Object[][] validData() {
		return new String[][]{
				new String[]{"x((abc)())"}, new String[]{"(as(df))()"}
		};
	}

	@DataProvider public Object[][] invalidData() {
		return new String[][]{
				new String[]{"x((abc)())("}, new String[]{"(as(df)))("}
		};
	}

	private boolean coupledBracket(String string) {
		Stack<Character> stack = new DequeStack<>();
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '(') {
				stack.push(string.charAt(i));
			}
			else if (string.charAt(i) == ')') {
				if (stack.isEmpty()) return false;
				else stack.pop();
			}
		}
		return stack.isEmpty();
	}

	@Test(dataProvider = "validData") public void testValidData(String string) {
		assertTrue(coupledBracket(string));
	}

	@Test(dataProvider = "invalidData") public void testInvalidData(String string) {
		assertFalse(coupledBracket(string));
	}


	@DataProvider public Object[][] validSquareData() {
		return new String[][]{
				new String[]{"x[(abc)()][()]"}, new String[]{"(as(df))[()]"}
		};
	}

	@DataProvider public Object[][] invalidSquareData() {
		return new String[][]{
				new String[]{"x([(abc)())("}, new String[]{"(as(df)))]("}
		};
	}

	private boolean coupledSquareBracket(String string) {
		Stack<Character> stack = new DequeStack<>();
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == '(' || string.charAt(i) == '[') {
				stack.push(string.charAt(i));
			}
			else if (string.charAt(i) == ')') {
				if (stack.isEmpty()) return false;
				else if (stack.pop() != '(') return false;
			}
			else if (string.charAt(i) == ']') {
				if (stack.isEmpty()) return false;
				else if (stack.pop() != '[') return false;
			}
		}
		return stack.isEmpty();
	}

	@Test(dataProvider = "validSquareData") public void testValidSquareData(String string) {
		assertTrue(coupledSquareBracket(string));
	}

	@Test(dataProvider = "invalidSquareData") public void testInvalidSquareData(String string) {
		assertFalse(coupledSquareBracket(string));
	}
}
