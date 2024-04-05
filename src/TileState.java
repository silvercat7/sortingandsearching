import java.util.ArrayList;
import java.util.List;

public class TileState {
    private int[][] board;
    private TileState parent;
    private int emptyR, emptyC;

    public TileState(int[][] initial) {
        board = copy(initial);
        setEmptyLocation();
        parent = null;
    }

    public TileState(TileState toCopy) {
        this.board = copy(toCopy.board);
        this.parent = toCopy.parent;
        this.emptyC = toCopy.emptyC;
        this.emptyR = toCopy.emptyR;
    }

    public int[][] copy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int row = 0; row < arr.length; row++) {
            System.arraycopy(arr[row], 0, copy[row], 0, arr[0].length);
        }
        return copy;
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

    public void setParent(TileState p) {
        this.parent = p;
    }

    public boolean isGoal() {
        return isGoal(this.board);
    }


    public boolean isGoal(int[][] board) {
        int correctVal = 1;
        for (int[] ints : board) {
            for (int i : ints) {
                if (correctVal == (board.length * board[0].length)) {
                    correctVal = 0;
                }
                if (i != correctVal) {
                    return false;
                }
                correctVal++;
            }
        }
        return true;
    }

    public List<TileState> getNextStates() {
        ArrayList<TileState> nextStates = new ArrayList<>();
        for (int row = emptyR - 1; row <= emptyR + 1; row++) {
            for (int col = emptyC - 1; col <= emptyC + 1; col++) {
                if (row < board.length && col < board[0].length) {
                    TileState nextState = copy();
                    nextState.moveTile(row, col);
                    nextState.parent = this;
                    nextStates.add(nextState);
                }
            }
        }
        return nextStates;
    }

    public void moveTile(int r, int c) {
        if (!isAdjacent(r, c, emptyR, emptyC)) {
            return;
        } else {
            board[emptyR][emptyC] = board[r][c];
            emptyR = r;
            emptyC = c;
        }
    }

    private boolean isAdjacent(int r, int c, int r2, int c2) {
        int rDiff = Math.abs(r - r2);
        int cDiff = Math.abs(c - c2);
        return rDiff + cDiff == 1;
    }

    public boolean equals(TileState other) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != other.board[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public TileState copy() {
        return new TileState(this);
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                out.append("[").append(anInt).append("] ");
            }
            out.append("\n");
        }
        return out.toString();
    }

    private boolean isInBounds(int r, int c) {
        if (r < 0 || c < 0) {
            return false;
        }
        return r < board.length && c < board[0].length;
    }
}