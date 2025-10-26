import java.util.Arrays;
import java.util.Scanner;

public class OptimalStorage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of programs: ");
        int n = scanner.nextInt();

        int[] lengths = new int[n];
        System.out.println("Enter the lengths of the " + n + " programs:");
        for (int i = 0; i < n; i++) {
            lengths[i] = scanner.nextInt();
        }

        Arrays.sort(lengths);

        System.out.println("\nOptimal storage order (sorted by length):");
        for (int length : lengths) {
            System.out.print(length + " ");
        }
        System.out.println();

        
        double totalRetrievalTime = 0;
        int cumulativeTime = 0;
        for (int i = 0; i < n; i++) {
            cumulativeTime += lengths[i];
            totalRetrievalTime += cumulativeTime;
        }

        double mrt = totalRetrievalTime / n;

        System.out.println("\nTotal Retrieval Time: " + (int)totalRetrievalTime);
        System.out.printf("Mean Retrieval Time (MRT): %.2f\n", mrt);

        scanner.close();
    }
}
