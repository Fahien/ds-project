package me.fahien.ds.binarytree;

/** Maintains the results of callings to EulerTour method
 * @author Fahien */
public class TourResult<R> {
	private R left;
	private R right;
	private R out;

	public R getLeft() {
		return left;
	}

	public void setLeft(R left) {
		this.left = left;
	}

	public R getRight() {
		return right;
	}

	public void setRight(R right) {
		this.right = right;
	}

	public R getOut() {
		return out;
	}

	public void setOut(R out) {
		this.out = out;
	}
}
