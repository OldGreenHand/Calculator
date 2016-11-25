/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 14/04/2016.
 * Class for expression tokenizer that allows to iterate over a expression
 * token by token. Blank characters will be skipped.
 */
package com.example.ken_and_ray.calculator.Calculator;

import com.example.ken_and_ray.calculator.Calculate.Calculator;
import com.example.ken_and_ray.calculator.Calculate.StaticTool;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void calculatorIsCorrect() throws Exception {
        Calculator calculator = new Calculator();
        calculator.initial();
        BigDecimal a1 = new BigDecimal(4);
        BigDecimal a2 = new BigDecimal(-2);
        List<BigDecimal> parameters = new ArrayList<>();
        parameters.add(new BigDecimal(0));

        // test all operators
        assertEquals(new BigDecimal(2), StaticTool.operators.get("+").calculate(a1, a2));
        assertEquals(new BigDecimal(6), StaticTool.operators.get("-").calculate(a1, a2));
        assertEquals(new BigDecimal(-8), StaticTool.operators.get("*").calculate(a1, a2));
        assertEquals(new BigDecimal(-2), StaticTool.operators.get("/").calculate(a1, a2));
        assertEquals(new BigDecimal(-2), StaticTool.operators.get("/").calculate(a1, a2));
        assertEquals(new BigDecimal(0), StaticTool.operators.get("%").calculate(a1, a2));
        assertEquals(new BigDecimal(16), StaticTool.operators.get("^").calculate(a2, a1));

        // test all functions
        assertEquals(new BigDecimal(0), StaticTool.functions.get("SIN").calculate(parameters));
        assertEquals(new BigDecimal(1), StaticTool.functions.get("COS").calculate(parameters));
        assertEquals(new BigDecimal(0), StaticTool.functions.get("TAN").calculate(parameters));
        parameters.remove(0);
        parameters.add(new BigDecimal(-3));
        assertEquals(new BigDecimal(3), StaticTool.functions.get("ABS").calculate(parameters));
        parameters.remove(0);
        parameters.add(new BigDecimal(100));
        assertEquals(new BigDecimal(2), StaticTool.functions.get("LOG").calculate(parameters));
        assertEquals(new BigDecimal(10), StaticTool.functions.get("SQRT").calculate(parameters));
        parameters.remove(0);
        parameters.add(Calculator.E);
        assertEquals(new BigDecimal(1), StaticTool.functions.get("LN").calculate(parameters));
        parameters.remove(0);
        parameters.add(new BigDecimal(5));
        assertEquals(new BigDecimal(120), StaticTool.functions.get("FAC").calculate(parameters));

        // test all variables
        assertEquals(Calculator.PI, StaticTool.variables.get("PI"));
        assertEquals(Calculator.E, StaticTool.variables.get("E"));

    }
}