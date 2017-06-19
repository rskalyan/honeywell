package com.honeywell.test;

import java.util.Scanner;

import com.honeywell.test.helper.ExpressionParser;

public class Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculator calculator = new Calculator();

		System.out.println("Type EXIT to exit");
		String input = sc.nextLine();
		while (input != null && !"EXIT".equals(input)) {
			try {
				System.out.println(calculator.calculate(input));
			} catch (Exception e) {
				System.out.println("Invalid Expression");
			}
			input = sc.nextLine();
		}
	}
}
