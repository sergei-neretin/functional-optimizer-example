package edu.nstu.functionaloptimizerexample.optimizator;

import edu.nstu.functionaloptimizerexample.function.IFunction;
import edu.nstu.functionaloptimizerexample.function.IParametricFunction;
import edu.nstu.functionaloptimizerexample.functional.IFunctional;
import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;

import java.util.Random;

public class MinimizerMonteCarlo implements IOptimizator {
    private static final int MAX_ITER = 100000;
    @Override
    public IVector minimize(
            IFunctional objective,
            IParametricFunction function,
            IVector initialParameters,
            IVector minimumParameters,
            IVector maximumParameters) {
        IVector param = new Vector();
        IVector minParam = new Vector();
        param.addAll(initialParameters);
        minParam.addAll(initialParameters);
        IFunction fun = function.bind(param);
        double currentmin = objective.value(fun);
        Random rand = new Random(0);
        for (int i = 0; i < MAX_ITER; i++) {
            param.replaceAll(ignored -> rand.nextDouble());
            double f = objective.value(function.bind(param));
            if (f < currentmin) {
                currentmin = f;
                for (int j = 0; j < param.size(); j++)
                    minParam.set(j, param.get(j));
            }
        }
        return minParam;
    }
}
