import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    // find a solution to the initial board (using the A* algorithm)
    private final MinPQ<GameTreeNode> minPQ;
    // private GameTreeNode minGameTreeNode;
    private final Board initialBoard;
    private final Stack<Board> boardStack;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        this.initialBoard = initial;
        this.minPQ = new MinPQ<>();
        this.boardStack = new Stack<>();
        if (isSolvable()) {
            this.solve(new GameTreeNode(initial, 0, null));
        }
        GameTreeNode current = this.minPQ.min();
        while (current.prevNode != null) {
            this.boardStack.push(current.getBoard());
            current = current.prevNode;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        int[] oneDBoard = new int[this.initialBoard.dimension() * this.initialBoard.dimension()];
        int blankSquareRow = 0;
        for (int i = 0; i < this.initialBoard.dimension(); i++) {
            for (int j = 0; j < this.initialBoard.dimension(); j++) {
                if (this.initialBoard.getTilesData()[i][j] == 0) blankSquareRow = i;
                oneDBoard[(i * this.initialBoard.dimension()) + j] = this.initialBoard.getTilesData()[i][j];
            }
        }

        return  this.initialBoard.dimension() % 2 == 0 ?
                this.isSolvableEvenBoard(oneDBoard, blankSquareRow) :
                this.isSolvableOddBoard(oneDBoard);
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return this.isSolvable() ? this.minPQ.min().getSearchScore() : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return this.isSolvable() ? this.boardStack : null;
    }

    private boolean isSolvableOddBoard(int[] oneDBoard) {
        return this.countInversions(oneDBoard) % 2 == 0;
    }

    private boolean isSolvableEvenBoard(int[] oneDBoard, int blankSquareRow) {
        return this.countInversions(oneDBoard) + blankSquareRow % 2 == 0;
    }

    private int countInversions(int[] oneDBoard) {
        int counter = 0;
        for (int i = 0; i < oneDBoard.length; i++) {
            for (int j = i+1; j < oneDBoard.length; j++) {
                if (oneDBoard[i] > oneDBoard[j] && oneDBoard[j] != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }
    private void solve(GameTreeNode initial) {
        // recursion solution will cause stack over flow for big tests
        // so i replaced it with iterative solution
        // Iterator<Board> boardIterator = initial.getBoard().neighbors().iterator();
        // if (initial.getBoard().isGoal()) {
        //     return;
        // }
        // while (boardIterator.hasNext()) {
        //     Board tempBoard = boardIterator.next();
        //     if (!initial.getBoard().equals(tempBoard)) {
        //         System.out.println(tempBoard.toString());
        //         this.minPQ.insert(new GameTreeNode(tempBoard, initial.numberOfPreviousMoves + 1, initial));
        //     }
        // }
        // this.minGameTreeNode = this.minPQ.min();
        // this.solve(this.minPQ.delMin());
        this.minPQ.insert(initial);
        while (!this.minPQ.min().getBoard().isGoal()) {
            GameTreeNode minNode = this.minPQ.delMin();
            for (Board tempBoard : minNode.getBoard().neighbors()) {
                if (!minNode.getBoard().equals(tempBoard)) {
                    this.minPQ.insert(new GameTreeNode(tempBoard, minNode.numberOfPreviousMoves + 1,
                                                       minNode));
                }
            }
        }



    }

    private class GameTreeNode implements Comparable<GameTreeNode> {
        private final Board board;
        private final int numberOfPreviousMoves;
        private final int aStarScore;
        private final GameTreeNode prevNode;

        public GameTreeNode(Board board, int numberOfPreviousMoves, GameTreeNode prevNode) {
            this.prevNode = prevNode;
            this.board = board;
            this.numberOfPreviousMoves = numberOfPreviousMoves;
            this.aStarScore = this.numberOfPreviousMoves + this.getBoard().manhattan();
        }

        public Board getBoard() {
            return this.board;
        }

        public int getSearchScore() {
            return this.aStarScore;
        }

        public int getNumberOfPreviousMoves() {
            return this.numberOfPreviousMoves;
        }

        public int compareTo(GameTreeNode gameTreeNode) {
            return Integer.compare(this.aStarScore, gameTreeNode.aStarScore);
        }
    }
    public static void main(String[] args) {
        int[][] arr = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}; // 4 moves to solve it
        int[][] arr1 = {{4, 1, 3}, {0, 2, 6}, {7, 5, 8}}; // 5 moves to solve it
        Solver solver = new Solver(new Board(arr1));
        System.out.println(solver.moves());
    }
}
