import java.util.*;

// Class to represent a weighted edge/node for the PriorityQueue
class Node implements Comparable<Node> {
    public int vertex;
    public int weight; // distance from the source

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    // Compare based on the weight (distance)
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// **Renamed the main class to match the desired filename: Dijkstra**
public class Dijkstra { 
    private int V; // Number of vertices
    // Adjacency list: List of (neighbor_vertex, edge_weight) pairs
    private List<List<Node>> adj;

    public Dijkstra(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Function to add a directed edge to the graph
    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Node(v, weight));
    }

    // Core Dijkstra's algorithm function
    public void shortestPath(int source) {
        // 1. Initialize
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        // PriorityQueue stores Node objects, sorted by weight (distance)
        PriorityQueue<Node> pq = new PriorityQueue<>();

        distance[source] = 0;
        pq.add(new Node(source, 0));

        // 2. Process
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int dist = current.weight;

            // Skip if we found a shorter path already
            if (dist > distance[u]) {
                continue;
            }

            // Iterate over neighbors (v)
            for (Node neighbor : adj.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                // Relaxation Step
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pq.add(new Node(v, distance[v]));
                }
            }
        }

        // 3. Output Results
        System.out.println("\n--- Shortest Path Results from Source " + source + " ---");
        for (int i = 0; i < V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": Not Reachable (INF)");
            } else {
                System.out.println("Vertex " + i + ": " + distance[i]);
            }
        }
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- User Input for Graph Definition ---
        System.out.print("Enter the number of vertices (V): ");
        int V = scanner.nextInt();
        
        if (V <= 0) {
            System.out.println("Number of vertices must be positive.");
            return;
        }

        // Changed constructor call to use the new class name
        Dijkstra graph = new Dijkstra(V);

        System.out.print("Enter the number of edges (E): ");
        int E = scanner.nextInt();
        
        System.out.println("Enter the edges (source destination weight) one by one:");
        System.out.println("(Use 0-based indexing for vertices, e.g., 0 1 10)");

        for (int i = 0; i < E; i++) {
            try {
                System.out.print("Edge " + (i + 1) + ": ");
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int weight = scanner.nextInt();
                
                if (u < 0 || u >= V || v < 0 || v >= V || weight < 0) {
                    System.out.println("Invalid input (vertices must be between 0 and V-1, weight must be non-negative). Skipping edge.");
                    i--;
                    continue;
                }
                
                graph.addEdge(u, v, weight);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter integers only. Exiting.");
                scanner.nextLine();
                return;
            }
        }

        // --- User Input for Source Vertex ---
        int sourceVertex = -1;
        while (sourceVertex < 0 || sourceVertex >= V) {
            System.out.print("\nEnter the starting source vertex (0 to " + (V - 1) + "): ");
            if (scanner.hasNextInt()) {
                sourceVertex = scanner.nextInt();
                if (sourceVertex < 0 || sourceVertex >= V) {
                    System.out.println("Error: Source vertex must be between 0 and " + (V - 1) + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.nextLine();
            }
        }

        // --- Run Dijkstra's Algorithm ---
        graph.shortestPath(sourceVertex);
        
        scanner.close();
    }
}
