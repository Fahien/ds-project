package me.fahien.ds.binarytree.expression;

/** Expression operator
 * @author Fahien */
public class ExpressionOperator extends ExpressionTerm {
	protected Integer firstOperand;
	protected Integer secondOperand;

	public void setOperands(Integer first, Integer second) {
		firstOperand = first;
		secondOperand = second;
	}
}