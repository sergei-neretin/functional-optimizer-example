package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;


public class LineFunction implements IParametricFunction {

    // Внутренний класс
    private class InternalLineFunction implements IFunction {
        private double a;
        private double b;

        @Override
        public double value(IVector point) {
            return a * point.get(0) + b;
        }
    }

    @Override
    public IFunction bind(IVector parameters) {
        InternalLineFunction internalFunction = new InternalLineFunction();
        internalFunction.a = parameters.get(0);
        internalFunction.b = parameters.get(1);
        return internalFunction;
    }
}
