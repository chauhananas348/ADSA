import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FloydWarshall {

    // Define a large number to represent infinity (assuming no path)
    private static final int INF = 99999; 

    /**
     * Reads the graph data (adjacency matrix) from a specified file.
     * * @param fileName The name of the file to read.
     * @return A square 2D array representing the weighted adjacency matrix.
     * @throws FileNotFoundException If the file cannot be found.
     * @throws InputMismatchException If the file content is not properly formatted.
     */
    public static int[][] readGraphFromFile(String fileName) throws FileNotFoundException, InputMismatchException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        
        // 1. Read the number of vertices (N)
        if (!fileScanner.hasNextInt()) {
            fileScanner.close();
            throw new InputMismatchException("File must start with the number of vertices (N).");
        }
        int V = fileScanner.nextInt();

        if (V <= 0) {
            fileScanner.close();
            throw new InputMismatchException("Number of vertices (N) must be positive.");
        }

        // 2. Initialize the distance matrix
        int[][] dist = new int[V][V];

        // 3. Read the matrix data
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (!fileScanner.hasNextInt()) {
                    fileScanner.close();
                    throw new InputMismatchException("Matrix does not contain " + (V * V) + " integers as expected.");
                }
                int weight = fileScanner.nextInt();

                // If the input matrix uses a sentinel value (like -1) for no connection,
                // replace it with INF, but ensure the algorithm works with 0 for self-loops.
                if (i == j) {
                    dist[i][j] = 0; // Distance to self is always 0
                } else if (weight == 0) {
                    // Assuming '0' in the file means no direct edge, use INF.
                    // If your file format explicitly uses a large number for INF, adjust here.
                    dist[i][j] = INF; 
                } else {
                    dist[i][j] = weight;
                }
            }
        }
        
        fileScanner.close();
        return dist;
    }

    /**
     * The core Floyd-Warshall algorithm.
     * * @param dist The initial adjacency matrix.
     * @param V The number of vertices.
     */
    public static void floydWarshall(int[][] dist, int V) {
        // k is the intermediate vertex
        for (int k = 0; k < V; k++) {
            // i is the start vertex
            for (int i = 0; i < V; i++) {
                // j is the end vertex
                for (int j = 0; j < V; j++) {
                    
                    // Avoid overflow when checking the sum with INF
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        // Relaxation step: dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }
    }

    /**
     * Prints the final shortest path matrix.
     * * @param dist The shortest path matrix.
     * @param V The number of vertices.
     */
    public static void printSolution(int[][] dist, int V) {
        System.out.println("\n--- All-Pairs Shortest Path Matrix ---");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.printf("%7s", "INF");
                } else {
                    System.out.printf("%7d", dist[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get filename from user
        System.out.print("Enter the name of the input file (e.g., graph.txt): ");
        String fileName = scanner.nextLine();

        try {
            // 2. Read graph from file
            int[][] dist = readGraphFromFile(fileName);
            int V = dist.length;

            System.out.println("\nSuccessfully read graph with " + V + " vertices.");
            System.out.println("Initial Adjacency Matrix:");
            printSolution(dist, V);

            // 3. Execute Floyd-Warshall
            floydWarshall(dist, V);

            // 4. Print results
            printSolution(dist, V);

        } catch (FileNotFoundException e) {
            System.err.println("\nERROR: File not found. Please ensure '" + fileName + "' is in the correct directory.");
        } catch (InputMismatchException e) {
            System.err.println("\nERROR: Invalid file format. " + e.getMessage());
            System.err.println("File must contain: V (number of vertices) followed by the V x V matrix of weights.");
        } catch (Exception e) {
            System.err.println("\nAn unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
