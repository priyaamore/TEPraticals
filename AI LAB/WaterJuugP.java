import java.util.*;

public class DFS {

    static void dfs(int node, boolean[] visited,
                    ArrayList<ArrayList<Integer>> graph) {

        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbour : graph.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, graph);
            }
        }
    }

    public static void main(String[] args) {

        int vertices = 5;

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(0).add(2);
        graph.get(1).add(3);
        graph.get(1).add(4);

        boolean[] visited = new boolean[vertices];

        System.out.println("DFS Traversal:");
        dfs(0, visited, graph);
    }
}
