package edu.nstu.functionaloptimizerexample.optimizator;

import edu.nstu.functionaloptimizerexample.function.IFunction;
import edu.nstu.functionaloptimizerexample.function.IParametricFunction;
import edu.nstu.functionaloptimizerexample.functional.IFunctional;
import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class MinimizerMonteCarlo implements IOptimizator {
    private final static Logger logger = LoggerFactory.getLogger(MinimizerMonteCarlo.class);

    private static final int MAX_ITER = 100000;

    @Override
    public IVector minimize(IFunctional objective,
                            IParametricFunction function,
                            IVector initialParameters,
                            IVector minimumParameters,
                            IVector maximumParameters) {
        IVector param = new Vector();
        IVector minParam = new Vector();

        for (Double initialParameter : initialParameters) {
            param.add(initialParameter);
            minParam.add(initialParameter);
        }

        IFunction fun = function.bind(param);
        double currentMin = objective.value(fun);
        Random rand = new Random(0);

        int i;
        for (i = 0; i < MAX_ITER; i++) {
            for (int j = 0; j < param.size(); j++) {
                double randomValue = rand.nextDouble();
                if (minimumParameters != null && maximumParameters != null) {
                    randomValue = minimumParameters.get(j) + randomValue * (maximumParameters.get(j) - minimumParameters.get(j));
                }
                param.set(j, randomValue);
            }

            double f = objective.value(function.bind(param));
            if (f < currentMin) {
                currentMin = f;
                for (int j = 0; j < param.size(); j++) {
                    minParam.set(j, param.get(j));
                }
            }
        }
        logger.info("iter number = {}, minimum parameter = {}", i, minParam);
        return minParam;
    }

    @Override
    public IVector minimize(IFunctional objective,
                            IParametricFunction function,
                            IVector initialParameters) {
        return minimize(objective, function, initialParameters, null, null);
    }
}
