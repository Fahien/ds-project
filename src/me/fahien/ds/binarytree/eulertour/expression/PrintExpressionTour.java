package me.fahien.ds.binarytree.eulertour.expression;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.BinaryTree;
import me.fahien.ds.binarytree.eulertour.EulerTour;
import me.fahien.ds.binarytree.eulertour.TourResult;
import me.fahien.ds.exception.EmptyTreeException;
import me.fahien.ds.util.position.Position;

/** Prints an expression using the Euler tour
 * @author Fahien */
public class PrintExpressionTour extends EulerTour<ExpressionTerm, String> {
	private static final Logger logger = Logger.getLogger(PrintExpressionTour.class.getName());
	private String string = "";

	public PrintExpressionTour(BinaryTree<ExpressionTerm> tree) {
		super(tree);
	}

	@Override public String execute() {
		try {
			eulerTour(tree.root());
			string += "\n";
		} catch (EmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		return string;
	}

	@Override protected void visitLeft(Position<ExpressionTerm> node, TourResult<String> result) {
		if (tree.isInternal(node)) {
			string += "(";
		}
	}

	@Override protected void visitBelow(Position<ExpressionTerm> node, TourResult<String> result) {
		string += node.getElement();
	}

	@Override protected void visitRight(Position<ExpressionTerm> node, TourResult<String> result) {
		if (tree.isInternal(node)) {
			string += ")";
		}
	}
}