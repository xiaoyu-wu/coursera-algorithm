/******************************************************************************
 *  Name:    Xiaoyu Wu
 *  NetID:   xiaoyu
 *  Precept: P01
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 * 
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 ******************************************************************************/


public class Percolation {
    private edu.princeton.cs.algs4.WeightedQuickUnionUF uf;
//    private edu.princeton.cs.algs4.UF uf;
    private int side;
    private int[] openRecord;
    
    public Percolation(int n) {               
    // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal side width.");
        }
        int total = n * n + 2;
        side = n;
        openRecord = new int[total];
        uf = new edu.princeton.cs.algs4.WeightedQuickUnionUF(total);
//        uf = new edu.princeton.cs.algs4.UF(total);
    }
    
    private int rowCol2Index(int row, int col) {
    // Convert row and col to index
        int index = side * (row - 1) + col;
        return index;
    }
    
    private void cornerCheck(int i) {
    // Check that the row and column indices are integers between 1 and n
        if (i < 1 || i > side) {
            throw new java.lang.IllegalArgumentException("Illegal indices.");
        }
    }
    
    public void open(int row, int col) {   
    // open site (row, col) if it is not open already
        cornerCheck(row);
        cornerCheck(col);
        int index = rowCol2Index(row, col);
        if (!isOpen(row, col)) {
            openRecord[index] = 1;
            // Connect to 4 nearest neighbours
            if ((row - 1 >= 1) && (isOpen(row - 1, col))) {
                uf.union(index, rowCol2Index(row - 1, col));
            }
            if ((row + 1 <= side) && (isOpen(row + 1, col))) {
                uf.union(index, rowCol2Index(row + 1, col));
            }
            if ((col - 1 >= 1) && (isOpen(row, col - 1))) {
                uf.union(index, rowCol2Index(row, col - 1));
            }
            if ((col + 1 <= side) && (isOpen(row, col + 1))) {
                uf.union(index, rowCol2Index(row, col + 1));
            }
            // Connect to virtual top or bottom
            if (row == 1) {
                uf.union(index, 0); // top
            }
            if (row == side) {
                uf.union(index, side * side + 1);
            }
        }        
    }
    
    public boolean isOpen(int row, int col) {
    // is site (row, col) open?
        cornerCheck(row);
        cornerCheck(col);
        int index = rowCol2Index(row, col);
        return openRecord[index] == 1;
    }
    
    public boolean isFull(int row, int col) {
    // is site (row, col) full?
        cornerCheck(row);
        cornerCheck(col);
        int index = rowCol2Index(row, col);
        return uf.connected(0, index);
    }
        
    public     int numberOfOpenSites() {
    // number of open sites
        int sum = 0;
        for (int i : openRecord) {
            sum += i;
        }
        return sum;
    }
    
    public boolean percolates() {
    // does the system percolate?
        return uf.connected(0, side * side + 1);
    }
    
    public static void main(String[] args) {
    // test client (optional)
        System.out.println("Test started!");
        Percolation testClient = new Percolation(2);
        
        System.out.println("System percolates: " + testClient.percolates());
        
        System.out.println("Open site (1, 1)");
        testClient.open(1, 1);
        System.out.println("Site (1, 1) open: " + testClient.isOpen(1, 1));
        System.out.println("Site (1, 1) full: " + testClient.isFull(1, 1));
        System.out.println("# of open sites: " + testClient.numberOfOpenSites());
        System.out.println("System percolates: " + testClient.percolates());
        
        System.out.println("Site (2, 1) full: " + testClient.isFull(2, 1));
        System.out.println("Open site (2, 1)");
        testClient.open(2, 1);
        System.out.println("Site (2, 1) open: " + testClient.isOpen(2, 1));
        System.out.println("Site (2, 1) full: " + testClient.isFull(2, 1));
        System.out.println("# of open sites: " + testClient.numberOfOpenSites());
        System.out.println("System percolates: " + testClient.percolates());
    }
}