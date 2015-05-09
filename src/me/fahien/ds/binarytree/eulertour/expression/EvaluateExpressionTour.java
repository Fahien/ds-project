package me.fahien.ds.binarytree.eulertour.expression;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.BinaryTree;
import me.fahien.ds.binarytree.eulertour.EulerTour;
import me.fahien.ds.binarytree.eulertour.TourResult;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.position.Position;

/** Evaluates an expression using the Euler tour
 * @author Fahien */
public class EvaluateExpressionTour extends EulerTour<ExpressionTerm, Integer> {
	private static final Logger logger = Logger.getLogger(EvaluateExpressionTour.class.getName());

	public EvaluateExpressionTour(BinaryTree<ExpressionTerm> tree) {
		super(tree);
	}

	@Override public Integer execute() {
		try {
			return eulerTour(tree.root());
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
			return 0;
		}
	}

	@Override protected void visitRight(Position<ExpressionTerm> node, TourResult<Integer> result) {
		ExpressionTerm term = node.getElement();
		if (tree.isInternal(node)) {
			ExpressionOperator operator = (ExpressionOperator) term;
			operator.setOperands(result.getLeft(), result.getRight());
		}
		result.setOut(term.getValue());
	}
}
