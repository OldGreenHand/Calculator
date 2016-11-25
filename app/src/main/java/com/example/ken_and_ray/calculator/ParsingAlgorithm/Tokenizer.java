/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 8/04/2016.
 * Class for expression tokenizer that allows to iterate over a expression
 * token by token. Blank characters will be skipped.
 */
package com.example.ken_and_ray.calculator.ParsingAlgorithm;

import com.example.ken_and_ray.calculator.Calculate.StaticTool;

import java.util.Iterator;



public class Tokenizer implements Iterator<String> {

	// the decimal separator
	private static final char decimalSeparator = '.';
	// the minus signature
	private static final char minusSign = '-';
	
	// position in expression string.
	private int pos = 0;
	// input expression
	private String input;
	// previousToken
	private String previousToken;

	/**
	 * Construct a new tokenizer for an expression.
	 * @param input string expression
	 */
	public Tokenizer(String input) {
		StaticTool.ExceptionContent = " Error in expression tokenization!";
		this.input = input.trim();
	}

	@Override
	public boolean hasNext() {
		return (pos < input.length());
	}

	/**
	 * Peek next character, without advancing the iterator.
	 * @return The next character or character 0, if at end of string.
	 */
	private char peekNextChar() {
		if (pos < (input.length() - 1)) {
			return input.charAt(pos + 1);
		} else {
			return 0;
		}
	}

	@Override
	public String next() {
		StringBuilder token = new StringBuilder();
		if (pos >= input.length()) {
			return previousToken = null;
		}
		char ch = input.charAt(pos);
		while (Character.isWhitespace(ch) && pos < input.length()) {
			ch = input.charAt(++pos);
		}
		if (Character.isDigit(ch)) {
			while ((Character.isDigit(ch) || ch == decimalSeparator || ch == 'e' || ch == 'E'
					|| (ch == minusSign && token.length() > 0
							&& ('e' == token.charAt(token.length() - 1) || 'E' == token.charAt(token.length() - 1)))
					|| (ch == '+' && token.length() > 0
							&& ('e' == token.charAt(token.length() - 1) || 'E' == token.charAt(token.length() - 1))))
					&& (pos < input.length())) {
				token.append(input.charAt(pos++));
				ch = pos == input.length() ? 0 : input.charAt(pos);
			}
		} else if (ch == minusSign && Character.isDigit(peekNextChar()) && ("(".equals(previousToken)
				|| ",".equals(previousToken) || previousToken == null || StaticTool.operators.containsKey(previousToken))) {
			token.append(minusSign);
			pos++;
			token.append(next());
		} else if (Character.isLetter(ch) || (ch == '_')) {
			while ((Character.isLetter(ch) || Character.isDigit(ch) || (ch == '_')) && (pos < input.length())) {
				token.append(input.charAt(pos++));
				ch = pos == input.length() ? 0 : input.charAt(pos);
			}
		} else if (ch == '(' || ch == ')' || ch == ',') {
			token.append(ch);
			pos++;
		} else {
			while (!Character.isLetter(ch) && !Character.isDigit(ch) && ch != '_' && !Character.isWhitespace(ch)
					&& ch != '(' && ch != ')' && ch != ',' && (pos < input.length())) {
				token.append(input.charAt(pos));
				pos++;
				ch = pos == input.length() ? 0 : input.charAt(pos);
				if (ch == minusSign) {
					break;
				}
			}
			if (!StaticTool.operators.containsKey(token.toString())) {
				StaticTool.ExceptionContent = "Unknown operator '" + token + "' at position " + (pos - token.length() + 1);
			}
		}
		return previousToken = token.toString();
	}

	@Override
	public void remove() {
		StaticTool.ExceptionContent = "remove() not supported";
	}

	/**
	 * Get character position in the string.
	 * @return character position.
	 */
	public int getPos() {
		return pos;
	}
	
	public static class ExpressionException extends RuntimeException {
		private static final long serialVersionUID = 1118142866870779047L;

		public ExpressionException(String message) {
			super(message);
		}
	}

}