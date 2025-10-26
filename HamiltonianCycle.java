import java.util.Arrays;
import java.util.Scanner;

public class HamiltonianCycle {

    private int V; // Number of vertices
    private int[][] graph; // Adjacency matrix representation of the graph
    private int[] path; // Array to store the path (sequence of vertices)

    /**
     * Initializes the graph and starts the search for a Hamiltonian Cycle.
     * @param graph The adjacency matrix of the graph.
     */
    public boolean findHamiltonianCycle(int[][] graph) {
        this.graph = graph;
        this.V = graph.length;
        this.path = new int[V];

        // Initialize path array. Use -1 to indicate an unvisited vertex.
        Arrays.fill(path, -1);

        // Start the cycle search from vertex 0. The starting vertex doesn't
        // affect the cycle existence due to the cyclic nature of the problem.
        path[0] = 0;

        // Start backtracking from the second vertex (position 1 in the path array)
        if (solveHCUtil(1)) {
            System.out.println("A Hamiltonian Cycle exists:");
            printSolution();
            return true;
        }

        System.out.println("No Hamiltonian Cycle exists in the given graph.");
        return false;
    }

    /**
     * The core recursive backtracking utility function.
     * @param k The current position in the path array (vertex index to place).
     * @return true if a cycle is found, false otherwise.
     */
    private boolean solveHCUtil(int k) {
        // Base Case: If all vertices are included in the path
        if (k == V) {
            // Check if the last vertex connects back to the starting vertex (vertex 0)
            if (graph[path[k - 1]][path[0]] == 1) {
                return true; // Cycle found!
            } else {
                return false;
            }
        }

        // Try every vertex as the k-th vertex in the path
        for (int v = 1; v < V; v++) {
            
            // Check if vertex 'v' can be added to the path at position 'k'
            if (isSafe(v, k)) {
                
                // 1. Make a choice (Place vertex 'v' at path position 'k')
                path[k] = v;

                // 2. Explore (Recurse to the next position)
                if (solveHCUtil(k + 1)) {
                    return true;
                }

                // 3. Backtrack (Unplace the vertex by resetting the path position)
                path[k] = -1;
            }
        }

        // If no vertex can be placed at position 'k', return false
        return false;
    }

    /**
     * Checks if vertex 'v' can be placed at path position 'k'.
     * @param v The vertex to check.
     * @param k The position in the path.
     * @return true if safe, false otherwise.
     */
    private boolean isSafe(int v, int k) {
        // 1. Check Adjacency: Is 'v' connected to the previously added vertex (path[k-1])?
        if (graph[path[k - 1]][v] == 0) {
            return false;
        }

        // 2. Check Visited: Has 'v' already been included in the current path?
        for (int i = 0; i < k; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    /**
     * Prints the found Hamiltonian Cycle.
     */
    private void printSolution() {
        // The path is V vertices long (0 to V-1).
        // The cycle is completed by connecting the last vertex back to the first.
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " --> ");
        }
        System.out.println(path[0]); // Complete the cycle
    }

    // -----------------------------------------------------------------
    // Main method for user input and execution
    // -----------------------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices (V): ");
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input.");
            scanner.close();
            return;
        }
        int V = scanner.nextInt();

        if (V <= 0) {
            System.out.println("Number of vertices must be positive.");
            scanner.close();
            return;
        }

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix (V x V). Use '1' for an edge, '0' for no edge.");
        try {
            for (int i = 0; i < V; i++) {
                System.out.println("Enter row " + i + " (space-separated 0s and 1s):");
                for (int j = 0; j < V; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid input format. Please ensure you enter " + V * V + " integers (0 or 1).");
            scanner.close();
            return;
        } finally {
            scanner.close();
        }

        HamiltonianCycle solver = new HamiltonianCycle();
        solver.findHamiltonianCycle(graph);
    }
}
