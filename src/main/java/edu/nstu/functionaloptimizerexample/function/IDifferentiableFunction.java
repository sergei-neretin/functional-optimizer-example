package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;

public interface IDifferentiableFunction extends IFunction {
    IVector gradient(IVector point);
}
