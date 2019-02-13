package com.capita.calc;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.capita.calc.StringCalculator;
import com.capita.calc.exception.InvalidExpression;

@FixMethodOrder(MethodSorters.JVM)
public class StringCalculaterTest {

	private static double DELTA = 0.01;
	private StringCalculator stringCalator;
    private  static int x = 1;
	@Before
	public void beforeEveryTest() {
		stringCalator = new StringCalculator();
	}

	
	
	@Test
	public void featureTest2() throws InvalidExpression {
		String equation = "2";
		double expectedResult = 2.0;

		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
	}

	@Test
	public void additionOperationOnTwoOperandsTest() throws InvalidExpression {
		String equation = "1+1";
		double expectedResult = 2.0;

		double actualResult = stringCalator.calculate(equation);

		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);

	}

	@Test
	public void additionOperationOnMoreThanTwoOperandsTest() throws InvalidExpression {
		String equation = "1+1+1";
		double expectedResult = 3.0;

		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);

	}
	
	@Test
	public void multiplicationOperationTest() throws InvalidExpression {
		String equation = "2*2";
		double expectedResult = 4.0;
		
		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
	}
	
	@Test
	public void substractionOperationTest() throws InvalidExpression {
		String equation = "2-1";
		double expectedResult  = 1.0;
		
		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
	}
	
	@Test
	public void divisionOperationTest() throws InvalidExpression {
		String equation = "10/3";
		double expectedResult = 3.33;
		
		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
	}
	
	@Test
	public void caretOperationTest() throws InvalidExpression {
		String equation = "5^3";
		double expectedResult = 125.0;
		
		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
	}
	
	@Test
	public void featureTest1() throws InvalidExpression {
		String equation = "7+(6*5^2+3-4/2)";
		double expectedResult = 158.0;

		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
		
	}
	
	@Test
	public void featureTest() throws InvalidExpression {
		String equation = "(8*5/8)-(3/1)-5";
		double expectedResult = -3.0;

		double actualResult = stringCalator.calculate(equation);
		System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+expectedResult);
		assertEquals(expectedResult, actualResult, DELTA);
		
	}
	
	@Test
	public void twoConsicutiveOperatorExceptionTest(){
		String equation = "1*+2";
		try {
			stringCalator.calculate(equation);
		} catch (InvalidExpression e) {
		   assertEquals("INVALID EXPRESSION", e.getMessage());
		   System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+e.getMessage());
		}
	}
	
	@Test
	public void bracketOperationTest2(){
		String equation = "7+(67(56*2))";
		try {
			stringCalator.calculate(equation);
		} catch (InvalidExpression e) {
		   assertEquals("INVALID EXPRESSION", e.getMessage());
		   System.out.println("Test case "+ x++ + ": Input :"+equation+"  "+"Output :"+e.getMessage());
		}
	}
}
