package edu.nstu.functionaloptimizerexample.functional;

import edu.nstu.functionaloptimizerexample.function.IFunction;
import edu.nstu.functionaloptimizerexample.math.IVector;

public interface IDifferentiableFunctional extends IFunctional {
    IVector gradient(IFunction function);
}
