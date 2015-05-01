package me.fahien.ds.util.operator;

/** Enum for operators
 * @author Fahien */
public enum SimpleOperator implements Operator {
	PLUS('+', 0) {
		@Override public int apply(int a, int b) {
			return a + b;
		}
	},
	MINUS('-', 0) {
		@Override public int apply(int a, int b) {
			return a - b;
		}
	},
	TIMES('*', 1) {
		@Override public int apply(int a, int b) {
			return a * b;
		}
	},
	OBELUS('/', 1) {
		@Override public int apply(int a, int b) {
			return a / b;
		}
	},
	OPEN_PARENTHESIS('(',2) {
		@Override public int apply(int a, int b) {
			return 0;
		}
	},
	CLOSED_PARENTHESIS(')',2) {
		@Override public int apply(int a, int b) { return 0; }
	};

	private Character symbol;
	private int precedence;

	private SimpleOperator(Character symbol, int precedence) {
		this.symbol = symbol;
		this.precedence = precedence;
	}

	public int compareWith(SimpleOperator simpleOperator) {
		return this.precedence - simpleOperator.precedence;
	}

	public static SimpleOperator fromCharacter(Character symbol) {
		for (SimpleOperator simpleOperator : SimpleOperator.values()) {
			if (symbol.equals(simpleOperator.symbol)) {
				return simpleOperator;
			}
		}
		throw new IllegalArgumentException("No operator with symbol " + symbol);
	}

	public String toString() { return symbol.toString(); }
}
