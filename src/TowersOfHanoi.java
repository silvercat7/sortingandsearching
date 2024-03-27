public class TowersOfHanoi {
    public static void main(String[] args) {
        moveDisksAdjacent(2);
    }

    public static void moveDisks(int n) {
        moveDisks(n, 1, 3);
    }

    public static void moveDisks(int n, int initial, int target) {
        if (n == 1) {
            System.out.println(initial + " --> " + target);
        } else {
            int open = 6 - initial - target;
            moveDisks(n - 1, initial, open);
            System.out.println(initial + " --> " + target);
            moveDisks(n - 1, open, target);
        }
    }

    public static void moveDisksAdjacent(int n) {
        moveDisksAdjacent(n, 1, 3);
    }

    public static void moveDisksAdjacent(int n, int initial, int target) {
        int open = 6 - initial - target;
        if (n == 1) {
            System.out.println(initial + " --> " + open);
            System.out.println(open + " --> " + target);
        } else {
            moveDisksAdjacent(n - 1, initial, target);
            System.out.println(initial + " --> " + open);
            moveDisksAdjacent(n - 1, target, initial);
            System.out.println(open + " --> " + target);
            moveDisksAdjacent(n - 1, initial, target);
        }
    }
}