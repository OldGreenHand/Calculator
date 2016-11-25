/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 3/04/2016.
 * Class for construct each function, such as SIN, COS ...
 */
package com.example.ken_and_ray.calculator.Calculate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

public abstract class Function {

    // initial parameter of each funtion
    private String function;
    private int numParams;

    /**
     * Creates a new function with given name and parameter count.
     *
     * @param function --> name of the function.
     * @param numParams --> type indicate the number of parameters current function required
     *            -1 --> A set of parameters required, such as MAX, MIN, AVG;
     *             1 --> only require one parameter, such as SIN, NOT;
     *             2 --> two parameters, such as ROUND;
     *             3 --> three parameters, such as IF;
     */
    public Function(String function, int numParams) {
        this.function = function.toUpperCase(Locale.ROOT);
        this.numParams = numParams;
    }

    //
    public String getName() {
        return function;
    }

    public int getNumParams() {
        return numParams;
    }

    /**
     * Implementate function calculate (must be override during initial)
     * @param parameters --> parameters;
     * @return result after the calculation.
     */
    public abstract BigDecimal calculate (List<BigDecimal> parameters);
}
