package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;


public class LineFunction implements IParametricFunction {

    private static class InternalLineFunction implements IDifferentiableFunction {
        private final IVector parameters;

        public InternalLineFunction(IVector parameters) {
            this.parameters = parameters;
        }

        @Override
        public double value(IVector points) {
            double sum = parameters.get(0);
            for(int i = 0; i < points.size(); i++) {
                sum += parameters.get(i + 1) * points.get(i);
            }
            return sum;
        }

        @Override
        public IVector gradient(IVector point) {
            IVector result = new Vector(point);
            result.addAll(parameters);
            result.remove(0);
            return result;
        }
    }

    @Override
    public IDifferentiableFunction bind(IVector parameters) {
        return new InternalLineFunction(parameters);
    }
}
