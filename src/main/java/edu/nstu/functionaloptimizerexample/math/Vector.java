package edu.nstu.functionaloptimizerexample.math;

import java.util.ArrayList;
import java.util.List;

public class Vector extends ArrayList<Double> implements IVector {
    public Vector(List<Double> list) {
        super(list);
    }

    public Vector (int size) {
        super(size);
    }

    public Vector() {
        super();
    }
}
