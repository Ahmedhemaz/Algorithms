import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double stddev, mean, confidenceHigh, confidenceLo;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int gridN, int trails) {
        if (trails <= 0 || gridN <= 0) throw new IllegalArgumentException("T shall be > 0");
        double[] threshold = new double[trails];

        for (int i = 0; i < trails; i++) {
            Percolation p = new Percolation(gridN);
            int sidesOpened = 0;
            do {
                int row = StdRandom.uniform(1, gridN + 1);
                int col = StdRandom.uniform(1, gridN + 1);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    sidesOpened++;
                }
            } while (!p.percolates());

            threshold[i] = (double) sidesOpened / (gridN * gridN);
        }

        this.stddev = StdStats.stddev(threshold);
        this.mean = StdStats.mean(threshold);
        double s = 1.96 * this.stddev/ Math.sqrt(trails);
        this.confidenceLo = this.mean - s;
        this.confidenceHigh = this.mean + s;
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }


    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHigh;
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1])
        );
        System.out.println("Mean                    = " + percolationStats.mean());
        System.out.println("StdDev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = " + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi());
    }
}
