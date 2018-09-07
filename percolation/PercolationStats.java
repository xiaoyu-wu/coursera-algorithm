/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private int numTrials;
    private double meanValue;
    private double stddevValue;

    public PercolationStats(int n, int trials) {
        // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Arguments must be positive!");
        }
        numTrials = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation testPercolation = new Percolation(n);
            while (!testPercolation.percolates()) {
                int randomRow = 1 + StdRandom.uniform(n);
                int randomCol = 1 + StdRandom.uniform(n);
                testPercolation.open(randomRow, randomCol);
            }
            results[i] = testPercolation.numberOfOpenSites() / ((double) n * n);
        }
        meanValue = StdStats.mean(results);
        stddevValue = StdStats.stddev(results);
    }

    public double mean() {
        // sample mean of percolation threshold
        return meanValue;
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return stddevValue;
    }

    public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        double temp = meanValue - 1.96 * stddevValue / Math.sqrt(numTrials);
        return temp;
    }

    public double confidenceHi() {
        // high endpoint of 95% confidence interval
        double temp = meanValue + 1.96 * stddevValue / Math.sqrt(numTrials);
        return temp;
    }

    public static void main(String[] args) {
        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats myPercolationStats = new PercolationStats(n, trials);
        System.out.println(myPercolationStats.mean());
        System.out.println(myPercolationStats.stddev());
        System.out.println(myPercolationStats.confidenceLo() + ", " +
                                   myPercolationStats.confidenceHi());
    }
}
