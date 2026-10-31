
import java.util.*;

public class BFS8Puzzle {

    static int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    static String key(int[][] state) {
        StringBuilder s = new StringBuilder();

        for (int[] row : state) {
            for (int value : row) {
                s.append(value);
            }
        }

        return s.toString();
    }

    static int[][] convert(String s) {
        int[][] board = new int[3][3];

        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = s.charAt(i) - '0';
        }

        return board;
    }

    static List<String> getNeighbors(String state) {

        List<String> neighbors = new ArrayList<>();

        int pos = state.indexOf('0');
        int r = pos / 3;
        int c = pos % 3;

        int[][] moves = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1}
        };

        for (int[] move : moves) {

            int nr = r + move[0];
            int nc = c + move[1];

            if (nr >= 0 && nr < 3 &&
                nc >= 0 && nc < 3) {

                int newPos = nr * 3 + nc;

                char[] arr = state.toCharArray();

                arr[pos] = arr[newPos];
                arr[newPos] = '0';

                neighbors.add(new String(arr));
            }
        }

        return neighbors;
    }

    static void bfs(int[][] start) {

        String startState = key(start);
        String goalState = key(goal);

        Queue<String> queue = new LinkedList<>();

        Map<String, String> parent = new HashMap<>();

        queue.add(startState);
        parent.put(startState, null);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(goalState)) {

                System.out.println("Solution Found!");

                List<String> path = new ArrayList<>();

                while (current != null) {
                    path.add(current);
                    current = parent.get(current);
                }

                Collections.reverse(path);

                System.out.println("Number of moves: " +
                                   (path.size() - 1));

                int step = 0;

                for (String state : path) {

                    System.out.println("Step " + step++);

                    int[][] board = convert(state);

                    for (int[] row : board) {
                        System.out.println(Arrays.toString(row));
                    }

                    System.out.println();
                }

                return;
            }

            for (String next : getNeighbors(current)) {

                if (!parent.containsKey(next)) {

                    parent.put(next, current);
                    queue.add(next);
                }
            }
        }

        System.out.println("No solution found!");
    }

    public static void main(String[] args) {

        int[][] start = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8}
        };

        bfs(start);
    }
}
