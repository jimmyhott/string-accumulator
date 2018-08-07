package epamTest;

import org.junit.Test;
import org.junit.Assert;


public class StringAccumulatorTest {
	
	StringAccumulator stringAccmulator = new StringAccumulator();

	@Test
	public void testEmptyString() {
		
		try {
			Assert.assertTrue("0 is expecetd if an empty string is provided", stringAccmulator.add("")==0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	@Test
	public void testSingleNumber() {
		
		String inputNumber = "100";
		int expectedValue = 100;
		
		try {
			Assert.assertTrue("Expected value is not correct", stringAccmulator.add(inputNumber)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void testDualNumbers() {
		
		String inputNumbers = "100,200";
		int expectedValue = 300;
		
		try {
			Assert.assertTrue("Expected value is not correct", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void testMultipleNumbers() {
		
		String inputNumbers = "100, 200 ,300,500, 600";
		int expectedValue = 1700;
		
		try {
			Assert.assertTrue("Expected value is not correct", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	@Test
	public void testMixedDefaultDelimiters() {
		
		String inputNumbers = "1,2\n 5,7,\n,10";
		int expectedValue = 25;
		
		try {
			Assert.assertTrue("Expected value is not correct", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
	}
	
	@Test
	public void testNegativeNumbers() {
		
		String inputNumbers = "1,2\n -5,-7,\n,10";
		
		try {
			stringAccmulator.add(inputNumbers);
		} catch (Exception e) {
			Assert.assertTrue("Exception must be thrown if one or more numbers are empty", e!=null && e.getMessage().contains("negatives not allowed"));
		}
	}
	
	@Test
	public void testSingleCustomDelimiter() {
		
		String inputNumbers = "//xx\n1xx2xx5xx7xx10xx";
		int expectedValue = 25;
		
		try {
			 Assert.assertTrue("Single custom delimiter failed", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMultipleCustomDelimiters() {
		
		String inputNumbers = "//xx|yy\n1xx2yy5yy7xx10";
		int expectedValue = 25;
		
		try {
			 Assert.assertTrue("Multiple custom delimiters failed", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testMultipleCustomDelimitersVariedLength() {
		
		String inputNumbers = "//xx|zzzz\n1zzzz2xx5zzzz7xx10";
		int expectedValue = 25;
		
		try {
			 Assert.assertTrue("Multiple custom delimiters failed", stringAccmulator.add(inputNumbers)==expectedValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}