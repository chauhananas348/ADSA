import java.util.Scanner;

public class PrimsAlgorithm {

    // Find the vertex with the minimum key value, from the set of vertices not yet included in MST
    int minKey(int key[], Boolean mstSet[], int V) {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Print the constructed MST stored in parent[]
    void printMST(int parent[], int graph[][], int V) {
        System.out.println("Edge \tWeight");
        int totalCost = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalCost += graph[i][parent[i]];
        }
        System.out.println("Total Minimum Cost: " + totalCost);
    }

    // Function to construct and print MST for a graph represented using adjacency matrix representation
    void primMST(int graph[][], int V) {
        int parent[] = new int[V]; // Array to store constructed MST
        int key[] = new int[V];    // Key values used to pick minimum weight edge
        Boolean mstSet[] = new Boolean[V]; // To represent set of vertices included in MST

        // Initialize all keys as INFINITE and mstSet[] as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have V vertices
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V); // Pick the minimum key vertex from the set of vertices not yet included in MST
            mstSet[u] = true; // Add the picked vertex to the MST Set

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        printMST(parent, graph, V);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrimsAlgorithm t = new PrimsAlgorithm();

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        int graph[][] = new int[V][V];
        System.out.println("Enter the adjacency matrix for the graph (0 for no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        t.primMST(graph, V);
        scanner.close();
    }
}
