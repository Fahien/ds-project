package me.fahien.ds.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** Array Stack Test Case
 * @author Fahien */
public class ArrayStackTest {
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

	private boolean coupledParentheses(String string) {
		Stack<Character> stack = new ArrayStack<>(16);
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
		assertTrue(coupledParentheses(string));
	}

	@Test(dataProvider = "invalidData") public void testInvalidData(String string) {
		assertFalse(coupledParentheses(string));
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

	private boolean coupledParenthesesAndBracket(String string) {
		Stack<Character> stack = new ArrayStack<>();
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
		assertTrue(coupledParenthesesAndBracket(string));
	}

	@Test(dataProvider = "invalidSquareData") public void testInvalidSquareData(String string) {
		assertFalse(coupledParenthesesAndBracket(string));
	}

	private String reverse(String string) {
		Stack<Character> stack = new ArrayStack<>();
		for (int i = 0; i < string.length(); i++) {
			stack.push(string.charAt(i));
		}
		String reverse = "";
		while (!stack.isEmpty()) {
			reverse += stack.pop();
		}
		return reverse;
	}

	@Test(dataProvider = "validData") public void testReverse(String string){
		StringBuilder builder = new StringBuilder(string);
		assertEquals(builder.reverse().toString(), reverse(string));
	}

	@Test public void testUnionStack() {
		Stack<Integer> stack = new ArrayStack<>();
		for (int i = 1; i < 4; i++) {
			stack.push(i);
		}
		ArrayStack<Integer> array = new ArrayStack<>();
		for (int i = 4; i < 8; i++) {
			array.push(i);
		}
		array.union(stack);
		assertEquals("[1, 2, 3, 4, 5, 6, 7]", array.toString());
		assertEquals(new Integer(7), array.top());
	}
}