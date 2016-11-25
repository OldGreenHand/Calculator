/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 8/04/2016.
 * Class for expression tokenizer that allows to iterate over a expression
 * token by token. Blank characters will be skipped.
 */
package com.example.ken_and_ray.calculator.ParsingAlgorithm;

import com.example.ken_and_ray.calculator.Calculate.StaticTool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Convertor {
	private StaticTool staticTool;
	private Stack<String> operand;
	private Stack<String> operator;
	
	public Convertor() {
		
	}
	
	public Queue<String> getPrefix( Tokenizer tokenizer) {
		StaticTool.ExceptionContent = " Error in convert into prefix!";
		// Initial parameter
		staticTool = new StaticTool();
		Queue<String> outputQueue = new ArrayDeque<String>();
		
		// Create operand and operator stacks as empty stacks.
		operand = new Stack<String>();
		operator = new Stack<String>();
		
		String lastFunction = null;
		String previousToken = null;
		// While input expression still remains, read and process the next token.
		while(tokenizer.hasNext()) {
			String token = tokenizer.next();
			System.out.println("\nCurrent Token is : [" + token + "]");
			
			if (staticTool.isNumber(token)) {
				operand.push(token);
//				System.out.println("added number token to operand: " + toString(operand));
			} else if (StaticTool.variables.containsKey(token)) {
				operand.push(token);
//				System.out.println("added variables token to operand: " + toString(operand));
			} 
			
			/* If it is a left parentheses or operator of higher precedence than the last, 
			 * or the stack is empty
			*/
			else if ( token.equals("(") || operator.isEmpty() || 
					getHierarchy(token) > getHierarchy(operator.peek())) {
				operator.push(token);
//				System.out.println("added token to operator stack: " + toString(operator));
			}
			
			/*
			 * Continue to pop operator and operand stacks, building
			 * prefix expressions until left parentheses is found.
			*/
			else if ( token.equals(")")) {
				if (!operator.peek().equals("(")) {
					 while (!operator.peek().equals("(")) {
						 if (StaticTool.operators.containsKey(operator.peek())) {		
							 addNewOperatorOperand();
						 } else {
							 addNewFunctionOperand();
						 }
					 }
				}
				operator.pop();
//				System.out.println("After process token: \nOperand: " + toString(operand)
//				+ "\nOperator: " + toString(operator));
			}
			
			 /* Continue to pop operator and operand stack, building prefix
			  * expressions until the stack is empty or until an operator at
			  * the top of the operator stack has a lower hierarchy than that
			  * of the token.
			 */
			else if ( getHierarchy(token) <= getHierarchy(operator.peek()) ) {
				while ( !operator.isEmpty() && getHierarchy(token) <= getHierarchy(operator.peek()) ) {
					if (StaticTool.operators.containsKey(operator.peek())) {
						addNewOperatorOperand();
					} else {
						addNewFunctionOperand();
					}
				}
				operator.push(token);
//				System.out.println("added token to operator stack: " + toString(operator));
			}
		}

		System.out.println("last while loop!");
		while ( !operator.isEmpty() ) {
			if (StaticTool.operators.containsKey(operator.peek())) {
				addNewOperatorOperand();
			} else {
				addNewFunctionOperand();
			}
		}
		
		// construct output queue
		for(String value : operand.pop().split(" ")) outputQueue.add(value);
		return outputQueue;
	}
	
	/* Each operator prefix expression is push back onto the operand
	 * stack as either a left or right operand for the next operator.
	*/
	private void addNewOperatorOperand() {
		String op = "";
		try {
			op = operator.pop();
			String rightOperand = operand.pop();
			String leftOperand = operand.pop();
			String newOperand =  op + " " + leftOperand + " " + rightOperand;
			operand.push(newOperand);
		} catch(Exception e) {
			StaticTool.ExceptionContent = "Operator " + op + " require more parameters!";
		}

	}
	
	/* Each function prefix expression is push back onto the operand
	 * stack as either a left or right operand for the next operator.
	*/
	private void addNewFunctionOperand() {
		String fc = "";
		try {
			fc = operator.pop();
			String rightOperand = operand.pop();
			String newOperand = fc + " " + rightOperand;
			operand.push(newOperand);
		} catch (Exception e) {
			StaticTool.ExceptionContent = "Operator " + fc + " require one parameter!";
		}
	}
	
	private int getHierarchy (String token) {
		if (StaticTool.operators.containsKey(token)) {
			return StaticTool.operators.get(token).getHierarchy();
		} else if (StaticTool.functions.containsKey(token)) {
			return 100;
		}
		return 0;
	}
	
	private String toString(Stack<String> stack) {
		String result = "";
		for (String a : stack) {
			result += a + ", ";
		}
		return result;
	}
}
