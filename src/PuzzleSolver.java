import java.util.*;

public class PuzzleSolver {
    public static final int MAX_DEPTH = 3;
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {0, 4, 1}, {2, 5, 3}};

    public static void main(String[] args) {
//        breadthFirst(ONE_MOVE_TEST);
//        breadthFirst(TWO_MOVE_TEST);
//        breadthFirst(SEVEN_MOVE_TEST);
//        breadthFirst(FOURTEEN_MOVE_TEST);
//        breadthFirst(THIRTY_ONE_MOVE_TEST);
        depthFirst(ONE_MOVE_TEST);
    }

    private static void breadthFirst(int[][] initialBoard) {
        TileState initial = new TileState(initialBoard);
        TileState result = breadthFirst(initial);
        if (result == null) {
            System.out.println("no results found");
            System.out.println(initial);
            return;
        }
        System.out.print("solved in " + result.getDepth());
        if (result.getDepth() == 1) {
            System.out.println(" move:");
        } else {
            System.out.println(" moves:");
        }
        ArrayList<TileState> solutionPath = new ArrayList<>();
        TileState current = result;
        while (!current.equals(initial)) {
            solutionPath.add(current);
            current = current.getParent();
        }
        System.out.println(initial);
        for (int i = (solutionPath.size() - 1); i >= 0; i--) {
            System.out.println(solutionPath.get(i));
        }
    }

    private static TileState breadthFirst(TileState initial) {
        ArrayList<TileState> moves = new ArrayList<>();
        ArrayList<TileState> possibilities = new ArrayList<>();
        moves.add(initial);
        int start = 0;
        while (unsolved(moves, start)) {
            for (int i = start; i < moves.size(); i++) {
                possibilities.addAll(moves.get(i).getNextStates());
            }
            for (int i = possibilities.size() - 1; i >= 0; i--) {
                if (moves.contains(possibilities.get(i))) {
                    possibilities.remove(i);
                }
            }
            start = moves.size();
            moves.addAll(possibilities);
            possibilities.clear();
        }
        return solution(moves, start);
    }

    private static void depthFirst(int[][] initialBoard) {
        TileState initial = new TileState(initialBoard);
        TileState result = depthFirst(initial);
        if (result == null) {
            System.out.println("no results found");
            System.out.println(initial);
            return;
        }
        System.out.print("solved in " + result.getDepth());
        if (result.getDepth() == 1) {
            System.out.println(" move:");
        } else {
            System.out.println(" moves:");
        }
        ArrayList<TileState> solutionPath = new ArrayList<>();
        TileState current = result;
        while (!current.equals(initial)) {
            solutionPath.add(current);
            current = current.getParent();
        }
        System.out.println(initial);
        for (int i = (solutionPath.size() - 1); i >= 0; i--) {
            System.out.println(solutionPath.get(i));
        }
    }

    private static TileState depthFirst(TileState initial) { //returns a result but not the best result
        //also returns 3 moves for the one move, but prints only one move
        if (initial.getDepth() < MAX_DEPTH) {
            ArrayList<TileState> possibilities = new ArrayList<>(initial.getNextStates());
            TileState result = initial;
            for (TileState possibility : possibilities) {
//                System.out.println(possibility + "depth: " + possibility.getDepth());
                result = depthFirst(possibility);
                if (result.isGoal()) {

                    return result;
                }
            }
            return result;
        }
        return initial;
    }

    private static boolean unsolved(ArrayList<TileState> moves, int start) {
        for (int i = start; i < moves.size(); i++) {
            if (moves.get(i).isGoal()) {
                return false;
            }
        }
        return true;
    }

    private static TileState solution(ArrayList<TileState> moves, int start) {
        for (int i = start; i < moves.size(); i++) {
            if (moves.get(i).isGoal()) {
                return moves.get(i);
            }
        }
        return null;
    }
}