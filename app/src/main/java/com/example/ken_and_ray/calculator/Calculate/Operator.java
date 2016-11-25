/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 3/04/2016.
 * Class for construct each operator, such as +, -, %, * ...
 */
package com.example.ken_and_ray.calculator.Calculate;

import java.math.BigDecimal;

public abstract class Operator {

    // initial parameter of each operator
    private String operator;
    private int hierarchy;
    private boolean leftAssoc;

    /**
     * Operator constructor
     * @param operator --> name of operator;
     * @param hierarchy --> hierarchy of operator;
     * @param leftAssoc --> true for operatoris left associative
     */
    public Operator(String operator, int hierarchy, boolean leftAssoc) {
        this.operator = operator;
        this.hierarchy = hierarchy;
        this.leftAssoc = leftAssoc;
    }

    // construct getter
    public String getOperator() {
        return operator;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public boolean isLeftAssoc() {
        return leftAssoc;
    }

    /**
     * Implementate operator calculate (must be override during initial)
     * @param o1 --> operand 1;
     * @param o2 --> operand 2;
     * @return result after the operation.
     */
    public abstract BigDecimal calculate(BigDecimal o1, BigDecimal o2);
}
