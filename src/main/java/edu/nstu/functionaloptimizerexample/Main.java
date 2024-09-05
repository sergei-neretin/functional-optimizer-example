package edu.nstu.functionaloptimizerexample;

import edu.nstu.functionaloptimizerexample.function.LineFunction;
import edu.nstu.functionaloptimizerexample.functional.MyFunctional;
import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;
import edu.nstu.functionaloptimizerexample.optimizator.MinimizerMonteCarlo;
import edu.nstu.functionaloptimizerexample.math.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MinimizerMonteCarlo optimizer = new MinimizerMonteCarlo();
        Vector initial = new Vector();
        initial.add(1.);
        initial.add(1.);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] str = input.split(" ");
            double x = Double.parseDouble(str[0]);
            double y = Double.parseDouble(str[1]);
            points.add(new Point(x, y));
        }
        MyFunctional functional = new MyFunctional(points);
        LineFunction fun = new LineFunction();

        IVector res = optimizer.minimize(functional, fun, initial);
        System.out.println("a = " + res.get(0) + ", b = " + res.get(1));
    }
}