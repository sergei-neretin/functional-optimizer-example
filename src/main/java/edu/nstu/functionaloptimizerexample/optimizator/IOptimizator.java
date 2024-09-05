package edu.nstu.functionaloptimizerexample.optimizator;

import edu.nstu.functionaloptimizerexample.function.IParametricFunction;
import edu.nstu.functionaloptimizerexample.functional.IFunctional;
import edu.nstu.functionaloptimizerexample.math.IVector;

public interface IOptimizator {
    IVector minimize(IFunctional objective,
                     IParametricFunction function,
                     IVector initialParameters,
                     IVector minimumParameters,
                     IVector maximumParameters);

    default IVector minimize(IFunctional objective,
                             IParametricFunction function,
                             IVector initialParameters) {
        return minimize(objective, function, initialParameters, null, null);
    }
}
