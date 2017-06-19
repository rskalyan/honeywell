package com.honeywell.test.enumerations;

public enum Operator {
	ADD('+', 1), SUBTRACT('-', 1), MULTIPLY('*', 2), DIVIDE('/', 2);

	private char operation;
	private int priority;

	private Operator(char operation, int priority) {
		this.operation = operation;
		this.priority = priority;
	}

	public char getOperation() {
		return operation;
	}

	public int getPriority() {
		return priority;
	}

	public static Operator getOperator(char operation) {
		Operator result = null;
		for (Operator o : Operator.values()) {
			if (o.operation == operation) {
				result = o;
				break;
			}
		}
		return result;
	}
}
