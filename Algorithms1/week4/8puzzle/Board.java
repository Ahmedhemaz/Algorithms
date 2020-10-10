import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board {

    private final int[][] tiles;
    private final int[][] goal;
    private final HashMap<Integer, List<Integer>> goalPositions;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        this.goal = new int[tiles.length][tiles.length];
        this.goalPositions = new HashMap<>();
        this.fillGoal();
        this.fillTiles(tiles);
    }

    // string representation of this board
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.tiles.length));
        stringBuilder.append("\n");
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                stringBuilder.append(this.tiles[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    // board dimension n
    public int dimension() {
        return  this.tiles.length;
    }

    // // number of tiles out of place
    public int hamming() {
        int hammingCounter = 0;
        for (int i = 0; i < this.goal.length; i++) {
            for (int j = 0; j < this.goal.length; j++) {
                if (this.goal[i][j] != this.tiles[i][j]) {
                    hammingCounter++;
                }
            }
        }
        // -1 because 0 is the empty slot which we should not count it as hamming
        return hammingCounter - 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanCounter = 0;
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tiles[i][j] != 0) {
                    manhattanCounter += Math.abs(this.goalPositions.get(this.tiles[i][j]).get(0) - i);
                    manhattanCounter += Math.abs(this.goalPositions.get(this.tiles[i][j]).get(1) - j);
                }
            }
        }
        return manhattanCounter;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return  this.hamming() == 0;
    }
    //
    // does this board equal y?
    @Override
    public boolean equals(Object y) {
        if (y == null) throw new IllegalArgumentException();
        if (y == this) return true;
        if (y.getClass() != this.getClass()) return false;
        Board board = (Board) y;
        return  Arrays.deepEquals(this.tiles, board.getTiles());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Queue<Board> boardQueue = new Queue<>();
        List<Integer> blankPosition = this.findBlankPosition();
        int i = blankPosition.get(0);
        int j = blankPosition.get(1);
        if (i > 0) {
            boardQueue.enqueue(new Board(swap(this.copy(this.tiles), i, j, i-1, j)));
        }
        if (i < dimension() - 1) {
            boardQueue.enqueue(new Board(swap(this.copy(this.tiles), i, j, i+1, j)));
        }
        if (j > 0) {
            boardQueue.enqueue(new Board(swap(this.copy(this.tiles), i, j, i, j-1)));
        }
        if (j < dimension() - 1) {
            boardQueue.enqueue(new Board(swap(this.copy(this.tiles), i, j, i, j+1)));
        }
        return boardQueue;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tilesCopy = this.copy(this.tiles);
        for (int i = 0; i < tilesCopy.length; i++) {
            for (int j = 0; j < tilesCopy.length; j++) {
                if (tilesCopy[i][j] != 0 && j < tilesCopy.length-1 && i < tilesCopy.length -1) {
                    swap(tilesCopy, i, j, i+1, j+1);
                    return new Board(tilesCopy);
                }
            }
        }
        return this;
    }

    public int[][] getTiles() {
        return this.copy(this.tiles);
    }

    private void fillGoal() {
        int counter = 0;
        for (int i = 0; i < this.goal.length; i++) {
            for (int j = 0; j < this.goal.length; j++) {
                this.goal[i][j] = ++counter;
                this.goalPositions.put(this.goal[i][j], new ArrayList<>(Arrays.asList(i, j)));
            }
        }
        this.goal[this.goal.length -1][this.goal.length -1] = 0;
    }

    private void fillTiles(int[][] incomingBoardTiles) {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                this.tiles[i][j] = incomingBoardTiles[i][j];
            }
        }
    }

    private int[][] copy(int[][] arr) {
        int [][] myCopy = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            myCopy[i] = arr[i].clone();
        }
        return myCopy;
    }

    private int[][] swap(int[][] arr, int fromI, int fromJ, int toI, int toJ) {
        int temp = arr[fromI][fromJ];
        arr[fromI][fromJ] = arr[toI][toJ];
        arr[toI][toJ] = temp;
        return arr;
    }

    private List<Integer> findBlankPosition() {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles.length; j++) {
                if (this.tiles[i][j] == 0) {
                    return new ArrayList<>(Arrays.asList(i, j));
                }

            }
        }
        return null;
    }

    public static void main(String[] args) {
        // int[][] arr = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        // int[][] arr1 = {{1, 0}, {2, 3}};
        // Board board = new Board(arr);
        // System.out.println(board.toString());
        // board.twin();
        // Iterator<Board> boardIterator = board.neighbors().iterator();
        // System.out.println(boardIterator.next().toString());
        // System.out.println(boardIterator.next().toString());
        // System.out.println(boardIterator.next().toString());
        // System.out.println(boardIterator.next().toString());
        // System.out.println("hamming:  " + board.hamming());
        // System.out.println("manhattan:  " + board.manhattan());
    }
}
