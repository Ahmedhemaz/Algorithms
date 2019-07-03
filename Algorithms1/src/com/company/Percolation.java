package com.company;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] grid;
    private int widthOf2D;
    private int openSitesCounter;
    private int virtualTopPointXCoordinate;
    private int virtualBotPointXCoordinate;
    private int virtualTopPointYCoordinate;
    private int virtualBotPointYCoordinate;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        this.widthOf2D = n;
        grid = new int[n*n];
        fill2DWithMaxIntValue(this.grid);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n);
        this.virtualTopPointXCoordinate = this.virtualBotPointXCoordinate = this.widthOf2D/2 + 1;
        this.virtualTopPointYCoordinate = 1;
        this.virtualBotPointYCoordinate = this.widthOf2D;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (!isOpen(row, col)){
            this.openSitesCounter++;
            grid[xyTo1D(row, col)] =
                    xyTo1D(row,col);
        }
        // connect Site with surrounding open sites
        connectSiteWithSurrounding(row, col);
        connectAllTopWithVirtualTopPoint(row, col);
        connectAllBottomWithVirtualBottomPoint(row,col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (grid[xyTo1D(row,col)] != Integer.MAX_VALUE){
            return true;
        }
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (!weightedQuickUnionUF.connected(xyTo1D(row, col),
                xyTo1D(virtualTopPointYCoordinate, virtualTopPointXCoordinate))) {
            return false;
        }
        return true;
    }
    // returns the number of open sites
    public int numberOfOpenSites(){
        return this.openSitesCounter;
    }

    // does the system percolate?
    public boolean percolates(){
        if (weightedQuickUnionUF.connected(xyTo1D(virtualBotPointYCoordinate,virtualBotPointXCoordinate),
                xyTo1D(virtualTopPointYCoordinate,virtualTopPointXCoordinate))){
            return true;
        }
        return false;
    }

    // convert x and y to 1D index
    private int xyTo1D(int row, int col){
        int[] rowAndColArrayValues = convertRowAndColToArrayIndexes(row,col);
        return (this.widthOf2D * rowAndColArrayValues[0])+rowAndColArrayValues[1];
    }

    // fill array with max int value which indicates that index is closed
    private void fill2DWithMaxIntValue(int[] grid){
        for (int i = 0; i<grid.length;i++){
            grid[i] = Integer.MAX_VALUE;
        }
    }


    // convert row and col to array indexes
    private int[] convertRowAndColToArrayIndexes(int row, int col){
        return new int[]{row - 1, col - 1};
    }

    private void connectSiteWithSurrounding(int row, int col){
        connectSiteToUpSite(row,col);
        connectSiteToDownSite(row,col);
        connectSiteToLeftSite(row,col);
        connectSiteToRightSite(row,col);
    }

    private void connectSiteToUpSite(int row, int col){
        if (row-1 <= 0){
            return;
        }
        if (isOpen(row-1, col)&& !weightedQuickUnionUF.connected(xyTo1D(row, col), xyTo1D(row-1, col))){
            weightedQuickUnionUF.union(xyTo1D(row,col),xyTo1D(row-1,col));
        }
    }

    private void connectSiteToDownSite(int row, int col){
        if (row+1 > widthOf2D){
            return;
        }
        if (isOpen(row+1, col) && !weightedQuickUnionUF.connected(xyTo1D(row, col), xyTo1D(row + 1, col))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
    }

    private void connectSiteToLeftSite(int row, int col){
        if (col-1 <= 0){
            return;
        }
        if (isOpen(row, col-1) && !weightedQuickUnionUF.connected(xyTo1D(row, col), xyTo1D(row, col - 1))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
    }

    private void connectSiteToRightSite(int row, int col){
        if (col+1 >= this.widthOf2D){
            return;
        }
        if (isOpen(row, col+1)&& !weightedQuickUnionUF.connected(xyTo1D(row, col), xyTo1D(row, col + 1))) {
            weightedQuickUnionUF.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    private void connectAllTopWithVirtualTopPoint(int row, int col){
        if (row == 1){
            weightedQuickUnionUF.union(xyTo1D(row, col),xyTo1D(virtualTopPointYCoordinate,virtualTopPointXCoordinate));
        }
    }

    private void connectAllBottomWithVirtualBottomPoint(int row , int col){
        if (row == virtualBotPointYCoordinate){
            weightedQuickUnionUF.union(xyTo1D(row, col),xyTo1D(virtualBotPointYCoordinate,virtualBotPointXCoordinate));
        }
    }
}
