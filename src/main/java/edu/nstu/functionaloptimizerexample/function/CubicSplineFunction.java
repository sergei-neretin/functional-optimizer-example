package edu.nstu.functionaloptimizerexample.function;

import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;

import java.util.List;

public class CubicSplineFunction implements IParametricFunction {

    private static class InternalCubicSplineFunction implements IDifferentiableFunction {
        private final IVector parameters;
        private final List<Double> breakpoints;

        public InternalCubicSplineFunction(IVector parameters, List<Double> breakpoints) {
            this.parameters = parameters;
            this.breakpoints = breakpoints;
        }

        @Override
        public double value(IVector points) {
            double x = points.get(0);
            int segmentIndex = getSegmentIndex(x);

            double a = parameters.get(segmentIndex * 4);
            double b = parameters.get(segmentIndex * 4 + 1);
            double c = parameters.get(segmentIndex * 4 + 2);
            double d = parameters.get(segmentIndex * 4 + 3);

            double xSegment = x - breakpoints.get(segmentIndex);
            return a + b * xSegment + c * xSegment * xSegment + d * xSegment * xSegment * xSegment;
        }

        @Override
        public IVector gradient(IVector points) {
            double x = points.get(0);
            int segmentIndex = getSegmentIndex(x);

            double b = parameters.get(segmentIndex * 4 + 1);
            double c = parameters.get(segmentIndex * 4 + 2);
            double d = parameters.get(segmentIndex * 4 + 3);

            double xSegment = x - breakpoints.get(segmentIndex);

            double gradientValue = b + 2 * c * xSegment + 3 * d * xSegment * xSegment;

            IVector result = new Vector(1);
            result.set(0, gradientValue);
            return result;
        }

        private int getSegmentIndex(double x) {
            for (int i = 0; i < breakpoints.size() - 1; i++) {
                if (x < breakpoints.get(i + 1)) {
                    return i;
                }
            }
            return breakpoints.size() - 1;
        }
    }

    private final List<Double> breakpoints;

    public CubicSplineFunction(List<Double> breakpoints) {
        this.breakpoints = breakpoints;
    }

    @Override
    public IDifferentiableFunction bind(IVector parameters) {
        if (parameters.size() != breakpoints.size() * 4) {
            throw new IllegalArgumentException("Некорректное количество параметров для кубического сплайна.");
        }
        return new InternalCubicSplineFunction(parameters, breakpoints);
    }
}
