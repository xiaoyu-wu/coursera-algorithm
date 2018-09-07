/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[] openRecord;
    private int size;
    private int head;
    private int tail;


    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive integer!");
        }
        size = n;
        head = n * n;
        tail = n * n + 1;
        openRecord = new boolean[n * n];
        for (int i = 0; i < n * n; i++) {
            openRecord[i] = false;
        }
        grid = new WeightedQuickUnionUF(n * n + 2);
    }

    private void checkInputError(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException("Invalid row or col number!");
        }
    }

    public void open(int row, int col) {
        // open site (row, col) if it is not open already
        checkInputError(row, col);
        openRecord[(row - 1) * size + col - 1] = true;
        if ((row > 1) && openRecord[(row - 2) * size + col - 1]) {
            grid.union((row - 1) * size + col - 1, (row - 2) * size + col - 1);
        }
        if ((row < size) && openRecord[row * size + col - 1]) {
            grid.union((row - 1) * size + col - 1, row * size + col - 1);
        }
        if ((col > 1) && openRecord[(row - 1) * size + col - 2]) {
            grid.union((row - 1) * size + col - 1, (row - 1) * size + col - 2);
        }
        if ((col < size) && openRecord[(row - 1) * size + col]) {
            grid.union((row - 1) * size + col - 1, (row - 1) * size + col);
        }
        if (row == 1) {
            grid.union((row - 1) * size + col - 1, head);
        }
        if ((row == size)) {
            grid.union((row - 1) * size + col - 1, tail);
        }
    }

    public boolean isOpen(int row, int col) {
        // is site (row, col) open?
        checkInputError(row, col);
        return openRecord[(row - 1) * size + col - 1];
    }

    public boolean isFull(int row, int col) {
        // is site (row, col) full?
        checkInputError(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return grid.connected((row - 1) * size + col - 1, head);
    }

    public int numberOfOpenSites() {
        // number of open sites
        int sum = 0;
        for (boolean i : openRecord) {
            if (i) {
                sum += 1;
            }
        }
        return sum;
    }

    public boolean percolates() {
        // does the system percolate?
        return grid.connected(head, tail);
    }

    public static void main(String[] args) {
        // test client (optional)
    }
}
