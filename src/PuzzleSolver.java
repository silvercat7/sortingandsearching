import java.util.*;

public class PuzzleSolver {
    public static final int MAX_DEPTH = 30;
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {0, 4, 1}, {2, 5, 3}};

    public static void main(String[] args) {
        aStar(THIRTY_ONE_MOVE_TEST);
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

    private static TileState depthFirst(TileState initial) {
        return depthFirst(initial, MAX_DEPTH);
    }

    private static TileState depthFirst(TileState initial, int minDepth) {
        if (initial.isGoal()) {
            return initial;
        } else if (initial.getDepth() < minDepth) {
            TileState solution = null;
            ArrayList<TileState> possibilities = new ArrayList<>(initial.getNextStates());
            for (TileState possibility : possibilities) {
                TileState result = depthFirst(possibility, minDepth);
                if (result != null && result.isGoal()) {
                    if (result.getDepth() <= minDepth) {
                        minDepth = result.getDepth();
                        solution = result;
                    }
                }
            }
            return solution;
        } else {
            return null;
        }
    }

    private static void aStar(int[][] initialBoard) {
        TileState initial = new TileState(initialBoard);
        TileState result = aStar(initial);
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

    private static TileState aStar(TileState initial) {
        return aStar(initial, MAX_DEPTH);
    }

    private static TileState aStar(TileState initial, int minDepth) {
        if (initial.isGoal()) {
            return initial;
        } else if (initial.getDepth() < minDepth) {
            TileState solution = null;
            ArrayList<TileState> possibilities = new ArrayList<>(initial.getNextStates());
            sort(possibilities);
            for (TileState possibility : possibilities) {
                TileState result = aStar(possibility, minDepth);
                if (result != null && result.isGoal()) {
                    if (result.getDepth() <= minDepth) {
                        minDepth = result.getDepth();
                        solution = result;
                    }
                }
            }
            return solution;
        } else {
            return null;
        }
    }

    private static void sort(ArrayList<TileState> possibilities) {
        for (int index = 1; index < possibilities.size(); index++) {
            TileState toInsert = possibilities.get(index);
            int score = possibilities.get(index).getScore();
            int place = index;
            possibilities.set(place, possibilities.get(place- 1));
            while (place > 0 && score < possibilities.get(place - 1).getScore()) {
                possibilities.set(place, possibilities.get(place- 1));
                place--;
            }
            possibilities.set(place, toInsert);
        }
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