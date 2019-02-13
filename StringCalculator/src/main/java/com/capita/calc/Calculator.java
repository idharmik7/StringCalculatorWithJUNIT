package com.capita.calc;
import java.util.Scanner;
import java.util.logging.Logger;

import com.capita.calc.exception.InvalidExpression;

public class Calculator {
    
	private final static Logger logger = Logger.getLogger("Calculator.class");
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		System.out.println("Please Enter equation");
		
		String equation = sc.next();
		
		sc.close();
		
		StringCalculator stringCalculator = new StringCalculator();
		try {
		 double result = stringCalculator.calculate(equation);
			System.out.println("RESULT : "+result);
		} catch (InvalidExpression e) {
			logger.info(e.getMessage());
		}
	}
}
