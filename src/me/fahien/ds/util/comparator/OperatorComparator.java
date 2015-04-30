package me.fahien.ds.util.comparator;

import java.util.Comparator;

import me.fahien.ds.util.operator.SimpleOperator;

/** Comparator for operators
 * @author Fahien */
public class OperatorComparator implements Comparator<SimpleOperator> {
	@Override public int compare(SimpleOperator simpleOperator1, SimpleOperator simpleOperator2) {
		return simpleOperator1.compareWith(simpleOperator2);
	}
}
