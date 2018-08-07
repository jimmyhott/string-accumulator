package epamTest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.lang3.text.StrMatcher;


public class StringAccumulator {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String LINE_RETURN = "\n";
	private static final String CUSTOM_DELIMITERS_SEPARATOR = "\\|";	
	private static final int EMPTY_STRING_RETURN_VALUE = 0;
	private static final int MAX_NUMBER = 1000;
	
	private static String[] defaultDelimiters = {COMMA_DELIMITER, LINE_RETURN};	
	
	private static final String DELIMITERS_REGEX = "^\\/\\/(.*)\\n";
	
	/**
	 * Add numbers from a string that is dynamically delimited
	 *  
	 * @param inputString
	 * 		  the string that includes numbers to be added and the delimiters information
	 * @return the sum of numbers
	 * @throws Exception
	 *         If negative number is found, exception will be thrown
	 */
	public int add(String inputString) throws Exception {
		
		//if empty string, stop processing
		if (StringUtils.isEmpty(inputString)) {
			return EMPTY_STRING_RETURN_VALUE;
		}
		
		// use regex to check the custom delimiters
		Pattern pattern = Pattern.compile(DELIMITERS_REGEX);
		Matcher matcher = pattern.matcher(inputString);
		
		String[] delimeters = null;
		StrBuilder numbersList = new StrBuilder();;
		
		//If the custom delimiters list is found
		if (matcher.lookingAt()) {
			//get the first group then split by the separator character
			delimeters = matcher.group(1).split(CUSTOM_DELIMITERS_SEPARATOR);
			//string of numbers to be added follows the delimiters list  
			numbersList.append(inputString.substring(matcher.end()));
		} else {
			//if the custom delimiter is not found, only default delimiters are supported
			delimeters = defaultDelimiters;
			//string of numbers to be added is the original input string
			numbersList.append(inputString);
		}
		
		//If we have the delimiters list but don't have the number list, stop processing
		if (StringUtils.isEmpty(numbersList)) {
			return EMPTY_STRING_RETURN_VALUE;
		}
		
		//for ease of processing, transform all delimiters identified to comma, then split the the line into an array.
		for (String delimeter:delimeters) {
			numbersList = numbersList.replaceAll(StrMatcher.stringMatcher(delimeter), COMMA_DELIMITER);
		}		
		
		//change string array of input number to int array for further processing
		int[] inputNumbers = Arrays.asList(numbersList.toString().split(COMMA_DELIMITER)).stream()
				.map(String::trim)
				.filter(item -> !StringUtils.isEmpty(item))
				.mapToInt(Integer::parseInt).toArray();
		
		//get the list of negative numbers
		int[] negativeNumbers= Arrays.stream(inputNumbers).filter(item -> item<0).toArray();
		
		//if negative numbers found, throw exception
		if (negativeNumbers.length>0) {
			throw new Exception("negatives not allowed - " + Arrays.toString(negativeNumbers));
		}
		
		//finally, sum all numbers, ignore the items that are > MAX_NUMBER
		return IntStream.of(inputNumbers).filter(item -> item<=MAX_NUMBER).sum();	
		
	}

}
