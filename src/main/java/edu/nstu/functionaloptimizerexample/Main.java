package edu.nstu.functionaloptimizerexample;

import edu.nstu.functionaloptimizerexample.function.LineFunction;
import edu.nstu.functionaloptimizerexample.functional.MyFunctional;
import edu.nstu.functionaloptimizerexample.math.IVector;
import edu.nstu.functionaloptimizerexample.math.Vector;
import edu.nstu.functionaloptimizerexample.optimizator.MinimizerMonteCarlo;
import edu.nstu.functionaloptimizerexample.math.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            MinimizerMonteCarlo optimizer = new MinimizerMonteCarlo();
            Vector initial = new Vector();
            initial.add(1.);
            initial.add(1.);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of points: ");
            int n = Integer.parseInt(scanner.nextLine());
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                System.out.println("Enter x coordinates: ");
                double x = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter y coordinates: ");
                double y = Double.parseDouble(scanner.nextLine());
                points.add(new Point(x, y));
            }
            scanner.close();

            MyFunctional functional = new MyFunctional(points);
            LineFunction fun = new LineFunction();

            IVector res = optimizer.minimize(functional, fun, initial);
            System.out.println("a = " + res.get(0) + ", b = " + res.get(1));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}