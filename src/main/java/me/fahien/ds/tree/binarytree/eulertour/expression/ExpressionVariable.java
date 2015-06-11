package me.fahien.ds.tree.binarytree.eulertour.expression;

/** Expression variable
 * @author Fahien */
public class ExpressionVariable extends ExpressionTerm {
	protected Integer variable;

	public ExpressionVariable(Integer variable) {
		this.variable = variable;
	}

	@Override public Integer getValue() {
		return variable;
	}

	@Override public String toString() {
		return variable.toString();
	}
}
