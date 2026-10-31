
import java.util.*;

class Node {
    int[][] state;
    int g, h;
    Node parent;

    Node(int[][] state, int g, int h, Node parent) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    int f() {
        return g + h;
    }
}

public class EightPuzzle {

    static int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    // Calculate Manhattan Distance
    static int heuristic(int[][] state) {
        int distance = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                int value = state[i][j];

                if (value != 0) {
                    int x = (value - 1) / 3;
                    int y = (value - 1) % 3;

                    distance += Math.abs(i - x) +
                                Math.abs(j - y);
                }
            }
        }

        return distance;
    }

    // Convert board into String
    static String key(int[][] state) {
        StringBuilder s = new StringBuilder();

        for (int[] row : state) {
            for (int value : row) {
                s.append(value);
            }
        }

        return s.toString();
    }

    // Check goal state
    static boolean isGoal(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    // Generate new states
    static List<int[][]> getNeighbors(int[][] state) {

        List<int[][]> neighbors = new ArrayList<>();

        int r = 0, c = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    r = i;
                    c = j;
                }
            }
        }

        int[][] moves = {
            {-1, 0}, {1, 0},
            {0, -1}, {0, 1}
        };

        for (int[] move : moves) {

            int nr = r + move[0];
            int nc = c + move[1];

            if (nr >= 0 && nr < 3 &&
                nc >= 0 && nc < 3) {

                int[][] newState = new int[3][3];

                for (int i = 0; i < 3; i++) {
                    newState[i] = state[i].clone();
                }

                newState[r][c] = newState[nr][nc];
                newState[nr][nc] = 0;

                neighbors.add(newState);
            }
        }

        return neighbors;
    }

    // A* Search Algorithm
    static void solve(int[][] start) {

        PriorityQueue<Node> pq =
            new PriorityQueue<>(
                Comparator.comparingInt(Node::f)
            );

        Map<String, Integer> visited = new HashMap<>();

        Node root = new Node(
            start, 0, heuristic(start), null
        );

        pq.add(root);
        visited.put(key(start), 0);

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            if (current.g != visited.get(key(current.state))) {
                continue;
            }

            if (isGoal(current.state)) {

                System.out.println("Solution Found!");
                System.out.println("Number of moves: " + current.g);

                Stack<Node> path = new Stack<>();

                while (current != null) {
                    path.push(current);
                    current = current.parent;
                }

                int step = 0;

                while (!path.isEmpty()) {
                    Node n = path.pop();

                    System.out.println("Step " + step++);

                    for (int[] row : n.state) {
                        System.out.println(Arrays.toString(row));
                    }

                    System.out.println();
                }

                return;
            }

            for (int[][] next : getNeighbors(current.state)) {

                int newG = current.g + 1;
                String k = key(next);

                if (!visited.containsKey(k) ||
                    newG < visited.get(k)) {

                    visited.put(k, newG);

                    Node child = new Node(
                        next, newG, heuristic(next), current
                    );

                    pq.add(child);
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

        solve(start);
    }
}
