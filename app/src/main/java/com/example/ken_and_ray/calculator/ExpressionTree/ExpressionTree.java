/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 4/04/2016.
 * Class for construct each operator, such as +, -, %, * ...
 */
package com.example.ken_and_ray.calculator.ExpressionTree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import com.example.ken_and_ray.calculator.Calculate.StaticTool;


public class ExpressionTree {
	private StaticTool staticTool;
	
	public ExpressionTree() {
		staticTool = new StaticTool();
	}

	public TreeNode buildExpressionTree(Queue<String> data) {
		StaticTool.ExceptionContent = "Error in building tree!";
		TreeNode node = null;
		boolean leaf = false;
		String value = data.poll();
		
//		System.out.println("current value: " + value);
		if (staticTool.isNumber(value) ) {
			node = new TreeNode(true, new BigDecimal(value));
		} else if( StaticTool.variables.containsKey(value) ) {
//			System.out.println("-----number value------");
			// Tree end at leaf nodes
			node = new TreeNode(true, StaticTool.variables.get(value));
		} else if (StaticTool.operators.containsKey(value)) {
//			System.out.println("-----operator value------");
			// Add operation node and recurse child nodes
			node = new TreeNode(leaf, value);
			node.left = buildExpressionTree(data);
			node.right = buildExpressionTree(data);
		} else {
			// Add function node and recurse child nodes
//			System.out.println("-----function value------");
			node = new TreeNode(leaf, value);
			node.middle = buildExpressionTree(data);
		}
		return node;
	}

	private static class TreeNode {
		private final boolean leaf;
		private String name;
		private BigDecimal value;
		private TreeNode left, right, middle;

		private TreeNode(boolean leaf, String name) {
			this.leaf = leaf;
			this.name = name;
			this.left = null;
			this.right = null;
		}
		
		private TreeNode(boolean leaf, BigDecimal value) {
			this.leaf = leaf;
			this.value = value;
			this.left = null;
			this.right = null;
		}

		public String toString() {
			return leaf ? String.valueOf((BigDecimal) value) : name;
		}
	}
	
	/*
	   * Display a tree in preorder
	  */
	  public static void showPrefix(TreeNode node) {
	    if (node != null) {
	      if (!node.leaf) {
	    	  System.out.print("node: " + node + " ");
	    	  if ( StaticTool.operators.containsKey(node.name)) {
	    		  showPrefix(node.left);
	    	      showPrefix(node.right);
	    	  } else {
	    		  showPrefix(node.middle);
	    	  }
	      }else {
	    	  System.out.print("left: " + node + " ");
	      }
	      
	       // or node = node.right and change if loop to while loop;
	    }
	  }
	  
	public static BigDecimal evaluateExpression(TreeNode node) {

		BigDecimal result = new BigDecimal(0);

		// Base leaf case
		if (node.leaf)
			return node.value;

		// This node is an expression, save the current node value
		BigDecimal left = null, right = null, middle = null;
		String name = node.name;

		System.out.println("\nCurrent node name: " + name);
		
		// Figure out value of left tree and right tree
		if (StaticTool.operators.containsKey(name)) {
//			System.out.println("---start get left result---");
			left = evaluateExpression(node.left);
//			System.out.println("---finish get left result---: " + left);
			
//			System.out.println("---start get right result---");
			right = evaluateExpression(node.right);
//			System.out.println("---finish get right result---: " + right);
		} else if (StaticTool.functions.containsKey(name)) {
//			System.out.println("---start get middle result---");
			middle = evaluateExpression(node.middle);
//			System.out.println("---finish get middle result---");
		} else {
			StaticTool.ExceptionContent = "Cannot find operator " + name + " in building tree!";
			new Exception();
		}

		
		
		// Perform the operation on left and right values
		if (StaticTool.operators.containsKey(name)) {
			result = StaticTool.operators.get(name).calculate(left, right);
		} else if (StaticTool.functions.containsKey(name)) {
			List<BigDecimal> parameters = new ArrayList<BigDecimal>();
			parameters.add(middle);
			result = StaticTool.functions.get(name).calculate(parameters);
		} else {
			StaticTool.ExceptionContent = "Cannot find operator " + name + " in building tree!";
			new Exception();
		}
		return result;
	}


}
