package com.honeywell.test.helper;

import com.honeywell.test.enumerations.MathSymbols;
import com.honeywell.test.enumerations.Operator;

public class ExpressionParser {
	//Parsing expressions to computable format that handles operator priorities and Math symbols.
	public static String parse(String input) throws Exception {
		StringBuilder output = new StringBuilder("");
		StringBuilder stack = new StringBuilder("");
		StringBuilder temp = new StringBuilder("");
		char cIn, cTmp;
		Operator cInOperator, cTmpOperator;

		// Processing for negative numbers
		// Replacing 2-3 with 2 + (-3) and 2*-3 with 2 * (-3) 
		for (int j = 0; j < input.length(); j++) {
			if (j > 0 && input.charAt(j) == '-' && Operator.getOperator(input.charAt(j - 1)) == null) {
				temp.append('+').append('-');
			} else {
				temp.append(input.charAt(j));
			}
		}

		for (int i = 0; i < temp.length(); i++) {
			cIn = temp.charAt(i);
			cInOperator = Operator.getOperator(cIn);
			//Ignoring subtract operation as it is processed above
			if (cInOperator != null && !cInOperator.equals(Operator.SUBTRACT)) {
				while (stack.length() > 0) {
					cTmp = stack.substring(stack.length() - 1).charAt(0);
					cTmpOperator = Operator.getOperator(cTmp);
					if (cTmpOperator != null && (cInOperator.getPriority() <= cTmpOperator.getPriority())) {
						output.append(" ").append(cTmp).append(" ");
						stack.setLength(stack.length() - 1);
					} else {
						output.append(" ");
						break;
					}
				}
				output.append(" ");
				stack.append(cIn);
			} else if (MathSymbols.E.toString().equals(Character.toString(cIn))) {
				//Handling Errors for expressions like 1+2E
				if (i > 0 && Operator.getOperator(temp.charAt(i - 1)) == null) {
					throw new Exception("Invalid Expression");
				}
				//Handling Errors for expressions like 1+E2
				if (i < input.length() - 1 && Operator.getOperator(temp.charAt(i + 1)) == null) {
					throw new Exception("Invalid Expression");
				}
				output.append(MathSymbols.E.getValue());
			} else if (i + 2 <= temp.length()
					&& MathSymbols.PI.toString().equals(new String(temp.substring(i, i + 2)))) {
				//Handling Errors for expressions like 1+2PI
				if (i > 0 && Operator.getOperator(temp.charAt(i - 1)) == null) {
					throw new Exception("Invalid Expression");
				}
				//Handling Errors for expression like 2*PI3
				if (i < input.length() - 2 && Operator.getOperator(temp.charAt(i + 2)) == null) {
					throw new Exception("Invalid Expression");
				}
				output.append(MathSymbols.PI.getValue());
				i++;
			} else if (cIn == ' ') {
				// Do nothing to skip space
			} else {
				// This includes the negated values
				output.append(cIn);
			}
		}

		while (stack.length() > 0) {
			output.append(" ").append(stack.substring(stack.length() - 1));
			stack.setLength(stack.length() - 1);
		}

		return output.toString();
	}

}
