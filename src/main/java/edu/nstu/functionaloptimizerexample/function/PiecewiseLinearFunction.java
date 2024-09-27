package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;

import java.util.List;

public class PiecewiseLinearFunction implements IParametricFunction {

    private final List<Double> breakpoints;

    public PiecewiseLinearFunction(List<Double> breakpoints) {
        this.breakpoints = breakpoints;
    }

    private static class InternalPiecewiseLinearFunction implements IDifferentiableFunction {
        private final IVector parameters;
        private final List<Double> breakpoints;

        public InternalPiecewiseLinearFunction(IVector parameters, List<Double> breakpoints) {
            this.parameters = parameters;
            this.breakpoints = breakpoints;
        }

        @Override
        public double value(IVector points) {
            double x = points.get(0);
            int segmentIndex = getSegmentIndex(x);

            double intercept = parameters.get(segmentIndex * 2);
            double slope = parameters.get(segmentIndex * 2 + 1);

            return intercept + slope * x;
        }

        @Override
        public IVector gradient(IVector points) {
            IVector result = new Vector(points.size());

            double x = points.get(0);
            int segmentIndex = getSegmentIndex(x);

            result.set(0, parameters.get(segmentIndex * 2 + 1));
            return result;
        }

        private int getSegmentIndex(double x) {
            for (int i = 0; i < breakpoints.size(); i++) {
                if (x < breakpoints.get(i)) {
                    return i;
                }
            }
            return breakpoints.size();
        }
    }

    @Override
    public IDifferentiableFunction bind(IVector parameters) {
        if (parameters.size() != (breakpoints.size() + 1) * 2) {
            throw new IllegalArgumentException("Некорректное количество параметров для кусочно-линейной функции.");
        }
        return new InternalPiecewiseLinearFunction(parameters, breakpoints);
    }
}
