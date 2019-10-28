import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double RATIO = 1.96;
    private final double[] allTrials;
    private double stddevResult = 0.0;
    private double meanResult = 0.0;

    // perform independent allTrials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 1) {
            throw new IllegalArgumentException("n must bigger than 0");
        }
        if (trials < 1) {
            throw new IllegalArgumentException("trials must bigger than 0");
        }
        allTrials = new double[trials];
        int row;
        int col;
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                row = getRandom(n);
                col = getRandom(n);
                percolation.open(row, col);
            }
            allTrials[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if (meanResult == 0.0) {
            meanResult = StdStats.mean(allTrials);
        }
        return meanResult;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddevResult == 0.0) {
            stddevResult = StdStats.stddev(allTrials);
        }
        return stddevResult;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (RATIO * stddev()) / Math.sqrt(allTrials.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (RATIO * stddev()) / Math.sqrt(allTrials.length);
    }

    private int getRandom(int n) {
        return StdRandom.uniform(n) + 1;
    }

    public static void main(String[] args) {
        // default value for n
        int n = 30;
        // default value for allTrials time
        int allTrials = 100;
        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        if (args.length == 2) {
            allTrials = Integer.parseInt(args[1]);
        }

        PercolationStats ps = new PercolationStats(n, allTrials);
        System.out.println("mean                     = " + ps.mean());
        System.out.println("stddev                   = " + ps.stddev());
        System.out.println("95% confidence interval  = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}

