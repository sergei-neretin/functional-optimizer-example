package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;

public class PolynomialFunction implements IParametricFunction {

    private static class InternalPolynomialFunction implements IFunction {
        private final IVector parameters;

        public InternalPolynomialFunction(IVector parameters) {
            this.parameters = parameters;
        }

        @Override
        public double value(IVector point) {
            double sum = 0;
            for(int i = 0; i < parameters.size(); i++) {
                sum += parameters.get(i) * Math.pow(point.get(0), i);
            }
            return sum;
        }
    }

    @Override
    public IFunction bind(IVector parameters) {
        return new InternalPolynomialFunction(parameters);
    }
}
