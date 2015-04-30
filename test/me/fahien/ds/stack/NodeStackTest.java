package me.fahien.ds.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.logging.Logger;

import me.fahien.ds.util.comparator.OperatorComparator;
import me.fahien.ds.util.operator.SimpleOperator;

import static org.testng.Assert.*;

/** NodeStack Test Case
 * @author Fahien */
public class NodeStackTest {
	private static final Logger logger = Logger.getLogger(NodeStackTest.class.getName());

	@DataProvider public Object[][] expressions() {
		return new String[][]{
				new String[]{"3+4*6-8"}
		};
	}

	private int evaluate(String expression) {
		Stack<Integer> values = new NodeStack<>();
		Stack<SimpleOperator> operators = new NodeStack<>();
		Comparator<SimpleOperator> comparator = new OperatorComparator();
		for (Character c : expression.toCharArray()) {
			if (Character.isDigit(c)) {
				values.push(Character.getNumericValue(c));
			}
			else {
				SimpleOperator operator = SimpleOperator.fromCharacter(c);
				while (!operators.isEmpty() && comparator.compare(operator, operators.top()) <= 0) {
					int a = values.pop();
					int b = values.pop();
					SimpleOperator tempOperator = operators.pop();
					values.push(tempOperator.apply(b, a));
					logger.info(a + " " + tempOperator + " " + b + " = " + values.top());
				}
				operators.push(operator);
			}
		}
		while (!operators.isEmpty()) {
			int a = values.pop();
			int b = values.pop();
			SimpleOperator tempOperator = operators.pop();
			values.push(tempOperator.apply(b, a));
			logger.info(a + " " + tempOperator + " " + b + " = " + values.top());
		}
		return values.top();
	}

	@Test(dataProvider = "expressions") public void testEvaluate(String expression) {
		assertEquals(evaluate(expression), 19);
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
		Stack<Character> stack = new NodeStack<>();
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
		Stack<Character> stack = new NodeStack<>();
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
