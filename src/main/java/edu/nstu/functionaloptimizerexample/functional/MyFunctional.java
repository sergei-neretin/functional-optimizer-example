package edu.nstu.functionaloptimizerexample.functional;

import edu.nstu.functionaloptimizerexample.function.IFunction;
import edu.nstu.functionaloptimizerexample.math.Point;
import edu.nstu.functionaloptimizerexample.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class MyFunctional implements IFunctional {
    public MyFunctional(List<Point> points) { this.points = points; }
    private List<Point> points;
    @Override
    public double value(IFunction function) {
        double sum = 0;
        for (Point point : points) {
            Vector param = new Vector();
            param.add(point.getX());
            double s = function.value(param) - point.getY();
            sum += s * s;
        }
        return sum;
    }
}
