package edu.nstu.functionaloptimizerexample.optimizator;

import org.junit.jupiter.api.BeforeEach;

public class MinimizerMonteCarloTests {

    private IOptimizator underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MinimizerMonteCarlo();
    }

}
