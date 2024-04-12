import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TileState {
    private final int[][] board;
    private int emptyR, emptyC;
    private TileState parent;
    private final int depth;
    private static final int[][] GOAL_STATE = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

    public TileState(int[][] initial) {
        board = copy(initial);
        setEmptyLocation();
        parent = null;
        this.depth = 0;
    }

    public TileState(TileState toCopy) {
        this.board = copy(toCopy.board);
        this.parent = toCopy.parent;
        this.emptyC = toCopy.emptyC;
        this.emptyR = toCopy.emptyR;
        this.depth = toCopy.depth + 1;
    }

    public ArrayList<TileState> getNextStates() {
        ArrayList<TileState> nextStates = new ArrayList<>();
        for (int row = emptyR - 1; row <= emptyR + 1; row += 2) {
            if (row >= 0 && row < board.length) {
                TileState nextState = copy();
                nextState.moveTile(row, emptyC);
                nextState.parent = this;
                nextStates.add(nextState);
            }
        }
        for (int col = emptyC - 1; col <= emptyC + 1; col += 2) {
            if (col >= 0 && col < board[emptyR].length) {
                TileState nextState = copy();
                nextState.moveTile(emptyR, col);
                nextState.parent = this;
                nextStates.add(nextState);
            }
        }
        return nextStates;
    }

    private void setEmptyLocation() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    this.emptyR = r;
                    this.emptyC = c;
                }
            }
        }
    }

    public boolean isGoal() {
        return isGoal(this.board);
    }

    public boolean isGoal(int[][] board) {
        return equalBoards(board, GOAL_STATE);
    }

    public void moveTile(int r, int c) {
        if (isAdjacent(r, c, emptyR, emptyC)) {
            board[emptyR][emptyC] = board[r][c];
            board[r][c] = 0;
            emptyR = r;
            emptyC = c;
        }
    }

    private boolean isAdjacent(int r, int c, int r2, int c2) {
        int rDiff = Math.abs(r - r2);
        int cDiff = Math.abs(c - c2);
        return rDiff + cDiff == 1;
    }

    @Override
    public boolean equals(Object other) {
        return equalBoards(this.board, ((TileState) other).board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(emptyR, emptyC);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int[] ints : board) {
            for (int i : ints) {
                out.append("[").append(i).append("] ");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public TileState copy() {
        return new TileState(this);
    }

    private int[][] copy(int[][] initial) {
        int[][] copy = new int[initial.length][initial[0].length];
        for (int r = 0; r < initial.length; r++) {
            System.arraycopy(initial[r], 0, copy[r], 0, initial[r].length);
        }
        return copy;
    }

    private boolean equalBoards(int[][] board, int[][] goal) {
        if (board.length != goal.length || board[0].length != goal[0].length) {
            return false;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != goal[r][c]) return false;
            }
        }
        return true;
    }

    private int heuristic() {
        int score = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                score += movesAway(board[row][col], row, col);
            }
        }
        return score;
    }

    private int movesAway(int tile, int tileR, int tileC) {
        int row = 0;
        int col = 0;
        for (int r = 0; r < GOAL_STATE.length; r++) {
            for (int c = 0; c < GOAL_STATE[0].length; c++) {
                if (GOAL_STATE[r][c] == tile) {
                    row = r;
                    col = c;
                }
            }
        }
        return Math.abs(tileR - row) + Math.abs(tileC - col);
    }

    public int getScore() {
        return depth + heuristic();
    }

    public TileState getParent() {
        return this.parent;
    }

    public int getDepth() {
        return this.depth;
    }
}
