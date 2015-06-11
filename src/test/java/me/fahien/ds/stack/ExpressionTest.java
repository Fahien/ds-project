package me.fahien.ds.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.logging.Logger;

import me.fahien.ds.util.comparator.OperatorComparator;
import me.fahien.ds.util.operator.SimpleOperator;

import static org.testng.Assert.assertEquals;

/** Expression Test Case
 * @author Fahien */
public class ExpressionTest {
	private static final Logger logger = Logger.getLogger(ExpressionTest.class.getName());

	@DataProvider public Object[][] infixExpressions() {
		return new String[][]{
				new String[]{"3+4*6-8"}
		};
	}

	@Test(dataProvider = "infixExpressions") public void testInfixEvaluate(String expression) {
		assertEquals(infixEvaluate(expression), 19);
	}

	private int infixEvaluate(String expression) {
		Stack<Integer> values = new NodeStack<>();
		Stack<SimpleOperator> operators = new NodeStack<>();
		Comparator<SimpleOperator> comparator = new OperatorComparator<>();
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

	@DataProvider public Object[][] postfixExpressions() {
		return new String[][]{
				new String[]{"12+5*,15"}, new String[]{"125*+,11"}
		};
	}

	private int postfixEvaluate(String expression) {
		Stack<Integer> values = new NodeStack<>();
		for (Character c : expression.toCharArray()) {
			if (Character.isDigit(c)) {
				values.push(Character.getNumericValue(c));
			}
			else {
				SimpleOperator operator = SimpleOperator.fromCharacter(c);
				int a = values.pop();
				int b = values.pop();
				values.push(operator.apply(b, a));
				logger.info(a + " " + operator + " " + b + " = " + values.top());
			}
		}
		return values.top();
	}

	@Test(dataProvider = "postfixExpressions") public void testPostfixEvaluate(String expression) {
		String[] expressions = expression.split(",");
		if (expressions.length != 2) {
			throw new InvalidParameterException("Invalid form of expression: " + expression);
		}
		assertEquals(postfixEvaluate(expressions[0]), Integer.parseInt(expressions[1]));
	}

	@DataProvider public Object[][] infixToPostfixExpressions() {
		return new String[][]{
				new String[]{"3+4*6-8,346*+8-"}, new String[]{"1+2*5,125*+"}, new String[]{"(1+2)*5,12+5*"}
		};
	}

	@Test(dataProvider = "infixToPostfixExpressions") public void testToPostfix(String expression) {
		String[] expressions = expression.split(",");
		if (expressions.length != 2) {
			throw new InvalidParameterException("Invalid form of expression: " + expression);
		}
		assertEquals(toPostfix(expressions[0]), expressions[1]);
	}

	private String toPostfix(String infixExpression) {
		String postfixExpression = "";
		Stack<SimpleOperator> operators = new NodeStack<>();
		Comparator<SimpleOperator> comparator = new OperatorComparator<>();
		for (Character c : infixExpression.toCharArray()) {
			if (Character.isDigit(c)) {
				postfixExpression += c;
			}
			else if (c == '('){
				SimpleOperator operator = SimpleOperator.fromCharacter(c);
				operators.push(operator);
			}
			else if (c == ')') {
				while (!operators.isEmpty() && operators.top() != SimpleOperator.OPEN_PARENTHESIS) {
					postfixExpression += operators.pop();
				}
				operators.pop();
			}
			else {
				SimpleOperator operator = SimpleOperator.fromCharacter(c);
				while (!operators.isEmpty() &&
						operators.top() != SimpleOperator.OPEN_PARENTHESIS &&
						comparator.compare(operators.top(), operator) >= 0) {
					postfixExpression += operators.pop();
				}
				operators.push(operator);
			}
		}
		while (!operators.isEmpty()) {
			postfixExpression += operators.pop();
		}
		return postfixExpression;
	}
}
