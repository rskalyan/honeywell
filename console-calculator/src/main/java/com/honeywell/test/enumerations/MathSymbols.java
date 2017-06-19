package com.honeywell.test.enumerations;

public enum MathSymbols {
	PI(3.1415927f),
	E(2.71828f);
	
	private float value;
	
	private MathSymbols(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}
}
