import com.sun.xml.internal.ws.resources.UtilMessages;

import java.util.*;

public class PuzzleSolver {
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {0, 4, 1}, {2, 5, 3}};

    public static void main(String[] args) {
//        play();
//        breadthFirst(ONE_MOVE_TEST);
//        breadthFirst(TWO_MOVE_TEST);
//        breadthFirst(SEVEN_MOVE_TEST);
//        breadthFirst(FOURTEEN_MOVE_TEST);
        breadthFirst(THIRTY_ONE_MOVE_TEST);
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
        while (!solved(moves)) {
            for (int i = start; i < moves.size(); i++) {
                possibilities.addAll(moves.get(i).getNextStates());
            }
            for (int i = 0; i < possibilities.size(); i++) {
                if (moves.contains(possibilities.get(i))) {
                    possibilities.remove(i);
                    i--;
                }
            }
            start = moves.size();
            moves.addAll(possibilities);
            possibilities.clear();
        }
        return solution(moves);
    }

    private static boolean solved(ArrayList<TileState> moves) {
        for (TileState move : moves) {
            if (move.isGoal()) {
                return true;
            }
        }
        return false;
    }

    private static TileState solution(ArrayList<TileState> moves) {
        for (TileState move : moves) {
            if (move.isGoal()) {
                return move;
            }
        }
        return null;
    }

    private static void play() {
        Scanner in = new Scanner(System.in);
        int[][] initial = {{1, 4, 3}, {6, 0, 7}, {5, 8, 2}};
        TileState current = new TileState(initial);
        System.out.println(current);
        while (!current.isGoal()) {
            System.out.println(current);
            System.out.println("which would you like?");
            List<TileState> next = current.getNextStates();
            for (int i = 0; i < next.size(); i++) {
                System.out.println("type " + i + " for");
                System.out.println(next.get(i));
            }
            int choice = in.nextInt();
            if (0 <= choice && choice < next.size()) {
                current = next.get(choice);
            }
        }
    }
}