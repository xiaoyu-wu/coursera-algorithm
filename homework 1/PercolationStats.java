public class PercolationStats {
    private int side;
    private int t;
    private double[] xs;
    
    public PercolationStats(int n, int trials) {
    // perform trials independent experiments on an n-by-n grid
        side = n;
        t = trials;
        xs = new double[t];
        int i = 1;
        while (i <= t) {
            Percolation p = new Percolation(side);
            while (!p.percolates()) {
                int randRow = edu.princeton.cs.algs4.StdRandom.uniform(1, side + 1);
                int randCol = edu.princeton.cs.algs4.StdRandom.uniform(1, side + 1);
                p.open(randRow, randCol);
            }
            xs[i - 1] = p.numberOfOpenSites() / ((double) side * side);
//            System.out.println("Trial # " + i + ": " + xs[i - 1]);
            i++;
        }
    }
    
    public double mean() {
    // sample mean of percolation threshold
        return edu.princeton.cs.algs4.StdStats.mean(xs);
    }
    
    public double stddev() {
    // sample standard deviation of percolation threshold
        return edu.princeton.cs.algs4.StdStats.stddev(xs);
    }

    public double confidenceLo() {
    // low  endpoint of 95% confidence interval
        double cLow = mean() - 1.96 * stddev() / java.lang.Math.sqrt(t);
        return cLow;
    }
    
    public double confidenceHi() {
    // high endpoint of 95% confidence interval
        double cHigh = mean() + 1.96 * stddev() / java.lang.Math.sqrt(t);
        return cHigh;    
    }
    public static void main(String[] args) {
    // test client (described below)
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        edu.princeton.cs.algs4.StdOut.printf("%-23s = %f%n", "mean", ps.mean());
        edu.princeton.cs.algs4.StdOut.printf("%-23s = %f%n", "stddev", ps.stddev());
        edu.princeton.cs.algs4.StdOut.printf("%-23s = [%f,  %f]%n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
    }
}