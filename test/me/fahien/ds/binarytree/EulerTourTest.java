package me.fahien.ds.binarytree;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.expression.AdditionOperator;
import me.fahien.ds.binarytree.expression.EvaluateExpressionTour;
import me.fahien.ds.binarytree.expression.ExpressionTerm;
import me.fahien.ds.binarytree.expression.ExpressionVariable;
import me.fahien.ds.binarytree.expression.PrintExpressionTour;
import me.fahien.ds.exception.NonEmptyTreeException;
import me.fahien.ds.positionlist.NodePositionList;
import me.fahien.ds.positionlist.PositionList;

import static org.testng.AssertJUnit.assertEquals;

/** Euler Tour Test Case
 * @author Fahien */
public class EulerTourTest {
	private static final Logger logger = Logger.getLogger(BinaryTreeTest.class.getName());

	@Test public void testEulerTour() {
		LinkedBinaryTree<ExpressionTerm> tree = new LinkedBinaryTree<>();
		try {
			tree.addRoot(new AdditionOperator());
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		PositionList<ExpressionTerm> list = new NodePositionList<>();
		list.addLast(new AdditionOperator());
		list.addLast(new AdditionOperator());
		tree.attachLeaves(list);

		list = new NodePositionList<>();
		list.addLast(new ExpressionVariable(1));
		list.addLast(new ExpressionVariable(2));
		list.addLast(new ExpressionVariable(3));
		list.addLast(new ExpressionVariable(4));
		tree.attachLeaves(list);

		PrintExpressionTour printTour = new PrintExpressionTour();
		logger.info("Expression: " + printTour.execute(tree));

		EvaluateExpressionTour evaluateTour = new EvaluateExpressionTour();
		Integer result = evaluateTour.execute(tree);
		assertEquals(result, new Integer(10));
		logger.info("Result: " + result);
	}
}
