package com.capita.calc;
import java.text.DecimalFormat;

import com.capita.calc.exception.InvalidExpression;

public class StringCalculator {

	private static final String INVALID_EXPRESSION = "INVALID EXPRESSION";
	DecimalFormat df = new DecimalFormat("##.##");

	public double calculate(String equation) throws InvalidExpression {
  
		if(equation.matches("[0-9]+.*(\\+|-)(/|\\*)[0-9]") || equation.matches("[0-9]+.*(\\*|/)(\\+|-)[0-9]") || equation.matches("[0-9]+.*(\\+|-)(\\+|-)[0-9]") ||
				equation.matches("[0-9]+.*(/|\\*)(/|\\*)[0-9]") || equation.endsWith("(") || equation.startsWith(")")) {
			
			throw new InvalidExpression(INVALID_EXPRESSION);
			
		}
		
		if (equation.contains("(") || equation.contains(")")) {
					return bracketProcess(equation);
		}

		if (equation.contains("+")) {
			return additionProcess(equation);
		}

		if (equation.contains("-")) {
			return substractionprocess(equation);
		}

		if (equation.contains("*")) {
			return multiplicationProcess(equation);
		}

		if (equation.contains("/")) {
			return divisionProcess(equation);
		}

		if (equation.contains("^")) {
			return caretProcess(equation);
		}
		if (equation.matches("^[0-9]+(\\.[0-9]+)?$")) {

			return Double.parseDouble(equation);
		}

		return Double.NaN;
	}

	private double bracketProcess(String equation) throws InvalidExpression {
		int x = equation.indexOf("(");
		
		if(x != 0) {
			char c  = equation.charAt(x-1);
			char c1  = equation.charAt(x+1);
			Object s = c;
			Object s1 = c1;
			if(s.toString().matches("[0-9]") || s.toString().equals("(") || s1.toString().equals("(") ){
				throw new InvalidExpression(INVALID_EXPRESSION);
			}
		}
		
		int y = equation.indexOf(")");
		double result = 0.0;
		String brecketContents = equation.substring(x + 1, y);
		result = calculate(brecketContents);
		String outsideBracketContents = null;
		if (y == equation.length() - 1) {
			outsideBracketContents = equation.substring(0, x) + result + equation.substring(y, equation.length() - 1);
		} else {
			outsideBracketContents = equation.substring(0, x) + result + equation.substring(y + 1, equation.length());
		}
		//System.out.println(outsideBracketContents); // 7+(6*5^2+3-4/2) (8*5/8)-(3/1)-5
		result = calculate(outsideBracketContents);
		return roundOff(result);
	}

	private double roundOff(double result) throws InvalidExpression {
		String roundOff = df.format(result);
		result = calculate(roundOff);
		return result;
	}

	private double caretProcess(String equation) throws InvalidExpression {
		String[] components = equation.split("\\^");
		double result = 1.0;
		double val = calculate(components[1]);
		for (int i = 0; i < val; i++) {
			result *= calculate(components[0]);
		}
		return roundOff(result);
	}

	private double divisionProcess(String equation) throws InvalidExpression {
		String[] components = equation.split("/"); // Not a special char
		return calculate(components[0]) / calculate(components[1]);
	}

	private double substractionprocess(String equation) throws InvalidExpression {
		String[] components = equation.split("-"); // Not a special char
		double result = 0.0;
		if(components.length == 2 && components[0].equals("")) {
			return -roundOff(calculate(components[1]));
		} else {
		 result = calculate(components[0]);
	
		for (int i = 1; i < components.length; i++) {
			result -= calculate(components[i]);
		}
		}
		return roundOff(result);
	}

	private double multiplicationProcess(String equation) throws InvalidExpression {
		String[] components = equation.split("\\*");
		double result = calculate(components[0]);

		for (int i = 1; i < components.length; i++) {
			result *= calculate(components[i]);
		}
		return roundOff(result);

	}

	private double additionProcess(String equation) throws InvalidExpression {
	
		String[] components = equation.split("\\+");
		
		
		
		double result = calculate(components[0]);

		for (int i = 1; i < components.length; i++) {
			if (components[0].isEmpty() ||components[i].isEmpty()) {
				throw new InvalidExpression(INVALID_EXPRESSION);
			}
			
			result += calculate(components[i]);
		}
		return roundOff(result);
	}

}
