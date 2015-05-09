package me.fahien.ds.binarytree;

import org.testng.annotations.Test;

import java.util.logging.Logger;

import me.fahien.ds.binarytree.eulertour.expression.AdditionOperator;
import me.fahien.ds.binarytree.eulertour.expression.EvaluateExpressionTour;
import me.fahien.ds.binarytree.eulertour.expression.ExpressionTerm;
import me.fahien.ds.binarytree.eulertour.expression.ExpressionVariable;
import me.fahien.ds.binarytree.eulertour.expression.PrintExpressionTour;
import me.fahien.ds.binarytree.eulertour.height.HeightTour;
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

		PrintExpressionTour printTour = new PrintExpressionTour(tree);
		logger.info("Expression: " + printTour.execute());

		EvaluateExpressionTour evaluateTour = new EvaluateExpressionTour(tree);
		Integer result = evaluateTour.execute();
		assertEquals(result, new Integer(10));
		logger.info("Result: " + result);
	}

	@Test public void testHeightTour() {
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
		try {
			tree.addRoot(0);
		} catch (NonEmptyTreeException e) {
			logger.warning(e.getMessage());
		}
		PositionList<Integer> list = new NodePositionList<>();
		list.addLast(1);
		list.addLast(2);
		tree.attachLeaves(list);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		tree.attachLeaves(list);

		HeightTour<Integer> heightTour = new HeightTour<>(tree);
		Integer rootHeight = heightTour.execute();
		assertEquals(rootHeight, new Integer(2));
	}
}
