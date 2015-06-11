package me.fahien.ds.set;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/** Set Test
 * @author Fahien */
public class SetTest {

	@Test public void simpleTest() {
		OrderedListSet<Integer> setA = new OrderedListSet<>();
		OrderedListSet<Integer> setB = new OrderedListSet<>();
		setA.fastInsert(1);
		setA.fastInsert(2);
		setA.fastInsert(4);
		setA.fastInsert(6);

		setB.fastInsert(3);
		setB.fastInsert(4);
		setB.fastInsert(5);
		setB.fastInsert(7);
		setB.fastInsert(10);

		Set<Integer> setC = setA.union(setB);
		assertEquals(setC.toString(), "[1, 2, 3, 4, 5, 6, 7, 10]");
		assertEquals(setA.toString(), "[1, 2, 4, 6]");
		assertEquals(setB.toString(), "[3, 4, 5, 7, 10]");

		setC = setA.intersect(setB);
		assertEquals(setC.toString(), "[4]");
		assertEquals(setA.toString(), "[1, 2, 4, 6]");
		assertEquals(setB.toString(), "[3, 4, 5, 7, 10]");

		setC = setA.subtract(setB);
		assertEquals(setC.toString(), "[1, 2, 6]");
		assertEquals(setA.toString(), "[1, 2, 4, 6]");
		assertEquals(setB.toString(), "[3, 4, 5, 7, 10]");
	}
}