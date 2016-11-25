package com.example.ken_and_ray.calculator;

import com.example.ken_and_ray.calculator.Calculator.CalculatorTest;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by MacBook on 17/05/2016.
 */
public class MainActivityTest {

    @Test
    public void testGetResult() throws Exception {
        MainActivity mainActivity = new MainActivity();

        // test case
        String result = mainActivity.getResult("2+3");
        assertEquals("5", result);

        result = mainActivity.getResult("2+3*((5-2)%3)");
        assertEquals("2", result);

        result = mainActivity.getResult("LN(E) + 2 - 2^3 + COS(0) + FAC(3)");
        assertEquals("2", result);

        // start calculator function test
        new CalculatorTest().testInitial();
    }
}