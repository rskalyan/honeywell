package com.honeywell.test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

import com.honeywell.test.enumerations.Operator;
import com.honeywell.test.helper.ExpressionParser;

public class Calculator {
	public String calculate(String input) throws Exception{
		if (input == null || "".equals(input)) {
			throw new Exception("Invalid Expression");
		}
		
		float arg1 = 0, arg2 = 0;
        String sTmp;
        Deque<Float> stack = new ArrayDeque<Float>();
        StringTokenizer st = new StringTokenizer(ExpressionParser.parse(input));
        while(st.hasMoreTokens()) {
            try {
                sTmp = st.nextToken().trim();
                Operator operator = Operator.getOperator(sTmp.charAt(0));
                if (1 == sTmp.length() && operator != null) {
                    if (stack.size() < 2) {
                        throw new Exception("Invalid Expression");
                    }
                    arg2 = stack.pop();
                    arg1 = stack.pop();
                    switch (operator) {
                        case ADD:
                            arg1 += arg2;
                            break;
                        case SUBTRACT:
                            arg1 -= arg2;
                            break;
                        case DIVIDE:
                            arg1 /= arg2;
                            break;
                        case MULTIPLY:
                            arg1 *= arg2;
                            break;
                        default:
                            break;
                    }
                    stack.push(arg1);
                } else {
                    arg1 = Float.parseFloat(sTmp);
                    stack.push(arg1);
                }
            } catch (Exception e) {
                throw new Exception("Invalid Expression");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Invalid Expression");
        }

        return formatDecmial(stack.pop());
	}
	
	private String formatDecmial(float value) {
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(value);
	}
}
