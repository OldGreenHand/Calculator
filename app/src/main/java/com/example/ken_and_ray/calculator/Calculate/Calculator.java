/**
 * Author: Yongqiang Wang
 * Created by Yongqiang on 4/04/2016.
 * Class for initial all operators, functions and variables in Calculator
 */
package com.example.ken_and_ray.calculator.Calculate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import com.example.ken_and_ray.calculator.Calculate.*;

public class Calculator {

    public static final BigDecimal PI = new BigDecimal(
            "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");

    public static final BigDecimal E = new BigDecimal(
            "2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274");
    
    public Calculator() {
    }
    
    public void initial(){
    	constructOperator();
        constructFunction();
        constructVaribale();
    }

    private void constructVaribale() {
    	StaticTool.variables.put("PI", PI);
    	StaticTool.variables.put("E", E);
    }

    private void constructFunction() {
    	addFunction(new Function("SIN", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.sin(Math.toRadians(parameters.get(0)
						.doubleValue()));
				return new BigDecimal(d);
			}
		});
		addFunction(new Function("COS", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.cos(Math.toRadians(parameters.get(0)
						.doubleValue()));
				return new BigDecimal(d);
			}
		});
		addFunction(new Function("TAN", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.tan(Math.toRadians(parameters.get(0)
						.doubleValue()));
				return new BigDecimal(d);
			}
		});
    	addFunction(new Function("ABS", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				return parameters.get(0).abs();
			}
		});
		addFunction(new Function("LOG", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.log10(parameters.get(0).doubleValue());
				return new BigDecimal(d);
			}
		});
		addFunction(new Function("LN", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.log(parameters.get(0).doubleValue()) / Math.log(E.doubleValue());
				return new BigDecimal(d);
			}
		});
		addFunction(new Function("SQRT", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = Math.sqrt(parameters.get(0).doubleValue());
				return new BigDecimal(d);
			}
		});
		addFunction(new Function("FAC", 1) {
			@Override
			public BigDecimal calculate (List<BigDecimal> parameters) {
				double d = 1d;
				for(int i = 1; i <= parameters.get(0).doubleValue(); i++) {
					d = d * i;
				}
				return new BigDecimal(d);
			}
		});
    }

    private void constructOperator() {
        addOperator(new Operator("+", 20, true) {
            @Override
            public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
                return v1.add(v2);
            }
        });
        addOperator(new Operator("-", 20, true) {
            @Override
            public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
                return v1.subtract(v2, new MathContext(20));
            }
        });
        addOperator(new Operator("*", 30, true) {
            @Override
            public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
                return v1.multiply(v2);
            }
        });
        addOperator(new Operator("/", 30, true) {
            @Override
            public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
                return v1.divide(v2, new MathContext(30));
            }
        });
		addOperator(new Operator("/", 30, true) {
			@Override
			public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
				return v1.divide(v2, new MathContext(30));
			}
		});
        addOperator(new Operator("%", 30, true) {
			@Override
			public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
				return v1.remainder(v2, new MathContext(30));
			}
		});
		addOperator(new Operator("^", 40, false) {
			@Override
			public BigDecimal calculate (BigDecimal v1, BigDecimal v2) {
				/*- 
				 * Thanks to Gene Marin:
				 * http://stackoverflow.com/questions/3579779/how-to-do-a-fractional-power-on-bigdecimal-in-java
				 */
				int signOf2 = v2.signum();
				double dn1 = v1.doubleValue();
				v2 = v2.multiply(new BigDecimal(signOf2)); // n2 is now positive
				BigDecimal remainderOf2 = v2.remainder(BigDecimal.ONE);
				BigDecimal n2IntPart = v2.subtract(remainderOf2);
				BigDecimal intPow = v1.pow(n2IntPart.intValueExact(), new MathContext(40));
				BigDecimal doublePow = new BigDecimal(Math.pow(dn1,
						remainderOf2.doubleValue()));

				BigDecimal result = intPow.multiply(doublePow, new MathContext(40));
				if (signOf2 == -1) {
					result = BigDecimal.ONE.divide(result, new MathContext(40).getPrecision(),
							RoundingMode.HALF_UP);
				}
				return result;
			}
		});
    }

    // add given operator to Calculator
    public Operator addOperator(Operator operator) {
        return StaticTool.operators.put(operator.getOperator(), operator);
    }

    // add given function to Calculator
    public Function addFunction(Function function) {
        return StaticTool.functions.put(function.getName(), function);
    }

    // set given variable to Calculator
    public void setVariable(String variable, BigDecimal value) {
        StaticTool.variables.put(variable, value);
    }
}
