import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] grid;
    private final int widthOf2D;
    private int openSitesCounter;
    private int virtualTopPointXCoordinate;
    private int virtualBotPointXCoordinate;
    private final int virtualTopPointYCoordinate;
    private final int virtualBotPointYCoordinate;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0) throw  new IllegalArgumentException();
        this.widthOf2D = n;
        grid = new int[n*n];
        fill2DWithMaxIntValue(this.grid);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n);
        this.virtualTopPointXCoordinate = grid.length/2;
        this.virtualTopPointYCoordinate = 1;
        this.virtualBotPointYCoordinate = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0) throw new IllegalArgumentException();
        if (!isOpen(row, col))
        {
            this.openSitesCounter++;
            grid[xyTo1D(row, col)] =
                    xyTo1D(row, col);
        }
        // connect Site with surrounding open sites
        connectSiteWithSurrounding(row, col);
        connectAllTopWithVirtualTopPoint(row, col);
        connectAllBottomWithVirtualBottomPoint(row, col);
        if (row == this.widthOf2D && isFull(row, col)) {
            this.virtualBotPointXCoordinate = col;
        }
        if (row == 1 && isFull(row, col)) {
            this.virtualTopPointXCoordinate = col;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0) throw new IllegalArgumentException();
        if (grid[xyTo1D(row, col)] != Integer.MAX_VALUE)
        {
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0) throw new IllegalArgumentException();
        if (!this.connected(xyTo1D(row, col),
                            xyTo1D(virtualTopPointYCoordinate, virtualTopPointXCoordinate))) {
            return false;
        }
        return true;
    }
    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return this.openSitesCounter;
    }

    // does the system percolate?
    public boolean percolates()
    {
        if (this.connected(xyTo1D(virtualBotPointYCoordinate, virtualBotPointXCoordinate),
                           xyTo1D(virtualTopPointYCoordinate, virtualTopPointXCoordinate)))
        {
            return true;
        }
        return false;
    }

    // convert x and y to 1D index
    private int xyTo1D(int row, int col) {
        int[] rowAndColArrayValues = convertRowAndColToArrayIndexes(row, col);
        return (this.widthOf2D * rowAndColArrayValues[0])+rowAndColArrayValues[1];
    }

    // fill array with max int value which indicates that index is closed
    private void fill2DWithMaxIntValue(int[] gridArray) {
        for (int i = 0; i < gridArray.length; i++)
        {
            gridArray[i] = Integer.MAX_VALUE;
        }
    }


    // convert row and col to array indexes
    private int[] convertRowAndColToArrayIndexes(int row, int col)
    {
        return new int[]{row - 1, col - 1};
    }

    private void connectSiteWithSurrounding(int row, int col)
    {
        connectSiteToUpSite(row, col);
        connectSiteToDownSite(row, col);
        connectSiteToLeftSite(row, col);
        connectSiteToRightSite(row, col);
    }

    private void connectSiteToUpSite(int row, int col)
    {
        if (row-1 <= 0)
        {
            return;
        }
        if (isOpen(row-1, col) && !this.connected(xyTo1D(row, col), xyTo1D(row-1, col)))
        {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row-1, col));
        }
    }

    private void connectSiteToDownSite(int row, int col)
    {
        if (row+1 > widthOf2D)
        {
            return;
        }
        if (isOpen(row+1, col) && !this.connected(xyTo1D(row, col), xyTo1D(row + 1, col))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
    }

    private void connectSiteToLeftSite(int row, int col)
    {
        if (col-1 <= 0)
        {
            return;
        }
        if (isOpen(row, col-1) && !this.connected(xyTo1D(row, col), xyTo1D(row, col - 1))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
    }

    private void connectSiteToRightSite(int row, int col)
    {
        if (col+1 >= this.widthOf2D)
        {
            return;
        }
        if (isOpen(row, col+1) && !this.connected(xyTo1D(row, col), xyTo1D(row, col + 1))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    private void connectAllTopWithVirtualTopPoint(int row, int col)
    {
        if (row == 1)
        {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(virtualTopPointYCoordinate, virtualTopPointXCoordinate));
        }
    }

    private void connectAllBottomWithVirtualBottomPoint(int row, int col)
    {
        if (row == virtualBotPointYCoordinate && isFull(row, col))
        {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(virtualBotPointYCoordinate, virtualBotPointXCoordinate));
        }
    }

    private boolean connected(int p, int q) {
        return weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(q);
    }

}