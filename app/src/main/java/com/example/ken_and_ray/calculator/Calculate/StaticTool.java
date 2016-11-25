/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 4/04/2016.
 * Class for store related data
 */

package com.example.ken_and_ray.calculator.Calculate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class StaticTool {

	// all operators in Calculator
    public static Map<String, Operator> operators = new TreeMap<String, Operator>(String.CASE_INSENSITIVE_ORDER);

    // all functions in Calculator
    public static Map<String, Function> functions = new TreeMap<String, Function>(String.CASE_INSENSITIVE_ORDER);

    // all variables in Calculator
    public static Map<String, BigDecimal> variables = new TreeMap<String, BigDecimal>(String.CASE_INSENSITIVE_ORDER);

	// add Exception error message
	public static String ExceptionContent = "";

	public StaticTool() {
		
	}
	
    private static final char decimalSeparator = '.';
    // the minus signature
    private static final char minusSign = '-';
    
    /**
	 * Is the string a number?
	 * @param value validate data
	 * @return true if the input string is a number.
	 */
	public boolean isNumber(String value) {
		if (value.charAt(0) == minusSign && value.length() == 1) return false;
		if (value.charAt(0) == '+' && value.length() == 1) return false;
		if (value.charAt(0) == 'e' ||  value.charAt(0) == 'E') return false;
		for (char ch : value.toCharArray()) {
			if (!Character.isDigit(ch) && ch != minusSign
					&& ch != decimalSeparator
                                       	&& ch != 'e' && ch != 'E' && ch != '+')
				return false;
		}
		return true;
	}
}
