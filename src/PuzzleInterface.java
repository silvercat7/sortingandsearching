import java.util.List;
import java.util.Scanner;

public class PuzzleInterface {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] initial = { {1, 4, 3}, {6, 0, 7}, {5, 8, 2}};
        TileState current = new TileState(initial);
        while (!current.isGoal()) {
            System.out.println(current);
            System.out.println("\n");
            System.out.println("which would you like?");
            List<TileState> next = current.getNextStates();
            for (int i = 0; i < next.size(); i++) {
                System.out.println("type " + i + " for ");
                System.out.println(next.get(i));
            }
            int choice = in.nextInt();

            if (0 <= choice && choice < next.size()) {
                current = next.get(choice);
            }
        }
    }
}