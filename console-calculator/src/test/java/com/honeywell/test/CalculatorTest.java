package com.honeywell.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

public class CalculatorTest {

	Calculator calculator = new Calculator();

	@Test
	public void testCalculate() throws Exception {
		List<String> inputs = new ArrayList<String>();
		inputs.add("1+2-3");
		inputs.add("12*2-4*2");
		inputs.add("5+2*3");
		inputs.add("-3-6-9");
		inputs.add("10/3");
		inputs.add("1+PI-E"); // consider math symbols
		inputs.add("3*-2+3-7"); // consider negative numbers
		inputs.add("1 + 2 - 3"); // ignore white spaces

		List<String> expected = new ArrayList<String>();
		expected.add("0");
		expected.add("16");
		expected.add("11");
		expected.add("-18");
		expected.add("3.3333");
		expected.add("1.4233");
		expected.add("-10");
		expected.add("0");

		for (int i = 0; i < inputs.size(); i++) {
			Assert.assertEquals(expected.get(i), calculator.calculate(inputs.get(i)));
		}

		// Test for invalid Expressions

		try {
			calculator.calculate("");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("*1+2");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("1+2/");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("1*+-2");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("1*2EPI");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("1*2E");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}

		try {
			calculator.calculate("1*2+W");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Invalid Expression", e.getMessage());
		}
	}

}
