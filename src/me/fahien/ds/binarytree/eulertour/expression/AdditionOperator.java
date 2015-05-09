package me.fahien.ds.binarytree.eulertour.expression;

/** Addition operator
 * @author Fahien */
public class AdditionOperator extends ExpressionOperator {
	public Integer getValue() {
		return firstOperand + secondOperand;
	}

	public String toString() {
		return "+";
	}
}
