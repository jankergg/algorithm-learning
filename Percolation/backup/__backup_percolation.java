import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 0 represents Open
 * 1 represents block
 */
public class Percolation {
    private final WeightedQuickUnionUF wquUF;
    private final int[][] grid;
    private final int n;
    private final int virtualTop;
    private int openedSites = 0;
    private boolean isPercolates;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        validate(n);
        this.n = n;
        // the counts of site
        int sites = n * n;
        // virtualTop and virtuanBottom must NOT includeed in sites
        virtualTop = sites;
        // create a weighted tree using WeightedQuickUnionwquUF
        wquUF = new WeightedQuickUnionUF(sites + 1);
        // create n-by-n sites.
        // 0 is blocked, 1 is opened
        grid = new int[n][n];
    }

    // opens the site (row, col) if it is not open already
    // grid[row][col] = grid[row*n + col]
    public void open(int inputRow, int inputCol) {
        if (!isOpen(inputRow, inputCol)) {
            int row = inputRow - 1;
            int col = inputCol - 1;

            int index = row * n + col;
            // if site is in first row, link it with virtualTop
            if (row == 0) {
                wquUF.union(index, virtualTop);
            }
            // if top is open
            if (row > 0 && grid[row - 1][col]>0) {
               wquUF.union(index, index - n);
            }
            // if left is open
            if (col > 0 && grid[row][col - 1]>0) {
               wquUF.union(index, index - 1);
            }
            // if right is open
            if (col < n - 1 && grid[row][col + 1]>0) {
               wquUF.union(index, index + 1);
            }
            // if bottom is open
            if (row < n - 1 && grid[row + 1][col]>0) {
               wquUF.union(index, index + n);
            }
            // if full site is in last row , link it with virtualBottom
            // if (row == n - 1) {
                // lastBottomOne = index;
            // }
            // Each time we open a site, check
            // if n=1 and left lastBottomOne unset
            grid[row][col] = index;
            openedSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int inputRow, int inputCol) {
        validate(inputRow, inputCol);
        return grid[inputRow - 1][inputCol - 1]>0;
    }

    // is the site (row, col) full?
    public boolean isFull(int inputRow, int inputCol) {
        validate(inputRow, inputCol);
        return wquUF.connected(virtualTop, (inputRow - 1) * n + (inputCol - 1));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openedSites;
    }

    // does the system percolate?
    public boolean percolates() {
      if(isPercolates){
        return isPercolates;
      }
      int flag=0;
      for(int i=0;i<n;i++){
        if(isOpen(n, i+1)){
          if(flag==1){
            continue;
          }
          flag = 1;

          if(wquUF.connected(virtualTop, n*(n-1)+i)){
            return true;
          }
        } else {
          flag = 0;
        }

      }
      return false;
    }


    /**
     * validate if the given row is available.
     *
     * @param row the integer representing one site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    private void validate(int row) {
        if (row < 1) {
            throw new IllegalArgumentException("the row and column must bigger than 0");
        }
    }

    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException("the row and column indices must be integers between 1 and " + n);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        // test
        int n = 10;
        Percolation p = new Percolation(n);
        while (p.openedSites < 50) {
          int row = StdRandom.uniform(n) + 1;
          int col = StdRandom.uniform(n) + 1;
          p.open(row, col);
        }
        // p.open(1, 3);
        // p.open(2, 3);
        // p.open(3, 3);
        // p.open(3, 1);
        // p.open(2, 1);
        // p.open(1, 1);
        // p.open(9, 1);
        // p.open(10, 1);
        // print a graph about grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(p.isOpen(i + 1, j + 1) ? p.grid[i][j] : "国");
            }
            System.out.print("\n");
        }
        System.out.println("----------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(p.isFull(i + 1, j + 1) ? "口" : "国");
            }
            System.out.print("\n");
        }
        System.out.print(p.isFull(9, 1)+"\n");
        System.out.print(p.isFull(10, 1)+"\n");
        System.out.print(p.percolates()+"\n");

    }
}

