/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int[] openRecord;
    private int size;


    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive integer!");
        }
        size = n;
        openRecord = new int[n * n];
        for (int i = 0; i < n * n; i++) {
            openRecord[i] = 0;
        }
        grid = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 1; i <= n; i++) {
            grid.union(0, i);
            grid.union(n * n + 1, n * n - i);
        }
    }

    private void checkInputError(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("Invalid row or col number!");
        }
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        checkInputError(row, col);
        openRecord[row * size + col] = 1;
        if ((row > 1) && (openRecord[(row - 1) * size + col] == 1)) {
            grid.union(row * size + col, (row - 1) * size + col);
        }
        if ((row < size) && (openRecord[(row + 1) * size + col] == 1)) {
            grid.union(row * size + col, (row + 1) * size + col);
        }
        if ((col > 1) && (openRecord[row * size + col - 1] == 1)) {
            grid.union(row * size + col, row * size + col - 1);
        }
        if ((col < size) && (openRecord[row * size + col + 1] == 1)) {
            grid.union(row * size + col, row * size + col + 1);
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        checkInputError(row, col);
        return (openRecord[row * size + col] == 1);
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        checkInputError(row, col);
        return grid.connected(0, row * size + col);
    }

    public int numberOfOpenSites() {
        // number of open sites
        int sum = 0;
        for (int i : openRecord) {
            sum += i;
        }
        return sum;
    }

    public boolean percolates() {
        // does the system percolate?
        return grid.connected(0, size * size + 1);
    }

    public static void main(String[] args) {
        // test client (optional)
    }
}
