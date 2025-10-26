// Breadth First Search (BFS) in Java
import java.util.*;

public class BFS {
    private int vertices;                 // number of vertices
    private LinkedList<Integer> adj[];    // adjacency list

    // Constructor
    BFS(int v) {
        vertices = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<>();
    }

    // Add edge to graph
    void addEdge(int v, int w) {
        adj[v].add(w); // directed edge (for undirected: add both)
    }

    // BFS Traversal
    void bfs(int start) {
        boolean visited[] = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbour : adj[node]) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS graph = new BFS(5);

        // Constructing graph
        // 0 -> 1, 2
        // 1 -> 3
        // 2 -> 4
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.bfs(0); // start from node 0
    }
}
