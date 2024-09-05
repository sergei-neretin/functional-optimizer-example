package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;

public interface IParametricFunction {
    IFunction bind(IVector parameters);
}
