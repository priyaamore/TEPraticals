
import java.util.*;

public class TSPBeamSearch {

    static int n = 4;
    static int k = 2;

    static int[][] cost = {
        {0, 10, 15, 20},
        {10, 0, 35, 25},
        {15, 35, 0, 30},
        {20, 25, 30, 0}
    };

    // Calculate total tour cost
    static int calculateCost(List<Integer> path) {

        int total = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            total += cost[path.get(i)][path.get(i + 1)];
        }

        total += cost[path.get(path.size() - 1)][path.get(0)];

        return total;
    }

    // Generate neighboring paths
    static List<List<Integer>> getNeighbors(List<Integer> path) {

        List<List<Integer>> neighbors = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                List<Integer> newPath = new ArrayList<>(path);

                Collections.swap(newPath, i, j);

                neighbors.add(newPath);
            }
        }

        return neighbors;
    }

    // Local Beam Search
    static void beamSearch() {

        List<List<Integer>> beam = new ArrayList<>();

        beam.add(Arrays.asList(0, 1, 2, 3));

        int bestCost = Integer.MAX_VALUE;
        List<Integer> bestPath = null;

        for (int iteration = 0; iteration < 100; iteration++) {

            List<List<Integer>> candidates = new ArrayList<>();

            for (List<Integer> path : beam) {
                candidates.addAll(getNeighbors(path));
            }

            candidates.sort(
                Comparator.comparingInt(TSPBeamSearch::calculateCost)
            );

            beam.clear();

            for (List<Integer> path : candidates) {

                if (!beam.contains(path)) {
                    beam.add(path);
                }

                if (beam.size() == k) {
                    break;
                }
            }

            if (beam.isEmpty()) {
                break;
            }

            for (List<Integer> path : beam) {

                int currentCost = calculateCost(path);

                if (currentCost < bestCost) {
                    bestCost = currentCost;
                    bestPath = new ArrayList<>(path);
                }
            }
        }

        System.out.println("Best Tour: " + bestPath);
        System.out.println("Minimum Cost: " + bestCost);
    }

    public static void main(String[] args) {

        System.out.println("Traveling Salesman Problem");
        beamSearch();

    }
}
