package edu.nstu.functionaloptimizerexample.functional;

import edu.nstu.functionaloptimizerexample.function.IFunction;
import edu.nstu.functionaloptimizerexample.math.IMatrix;
import edu.nstu.functionaloptimizerexample.math.IVector;

public interface ILeastSquaresFunctional extends IFunctional {
    IVector residual(IFunction function);
    IMatrix jacobian(IFunction function);
}
