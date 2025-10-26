import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class NQueens {

    private int N;
    private int solutionCount = 0;
    private List<List<String>> allSolutions = new ArrayList<>();

    /**
     * Solves the N-Queens problem and prints the results.
     * @param n The size of the board (N x N).
     */
    public void solve(int n) {
        this.N = n;
        solutionCount = 0;
        allSolutions.clear();
        
        // 'board' array where board[i] stores the column index of the queen in row i.
        // For an N=4 board: board[0]=1, board[1]=3, board[2]=0, board[3]=2 represents 
        // a queen at (0,1), (1,3), (2,0), and (3,2).
        int[] board = new int[N];
        
        // Start the backtracking process from the first row (row 0)
        backtrack(board, 0);
        
        // Print all solutions found
        printAllSolutions();
    }

    /**
     * The core recursive backtracking function.
     * @param board The current state of the board.
     * @param row The current row being processed.
     */
    private void backtrack(int[] board, int row) {
        // Base Case: If all queens are successfully placed (i.e., we've filled all N rows)
        if (row == N) {
            solutionCount++;
            allSolutions.add(formatBoard(board));
            return;
        }

        // Try placing a queen in every column of the current row
        for (int col = 0; col < N; col++) {
            
            // Check if placing a queen at (row, col) is safe
            if (isSafe(board, row, col)) {
                
                // Place the queen (Make a choice)
                board[row] = col; 
                
                // Recurse to the next row (Explore)
                backtrack(board, row + 1);
                
                // Backtrack: No need to explicitly "unplace" the queen since 
                // the next iteration of the loop or the return from recursion 
                // will overwrite board[row] or discard the current board state.
            }
        }
    }

    /**
     * Checks if a queen can be safely placed at (row, col) with respect to 
     * queens in previous rows.
     * @param board The current board state.
     * @param row The current row to check.
     * @param col The current column to check.
     * @return true if safe, false otherwise.
     */
    private boolean isSafe(int[] board, int row, int col) {
        // Check previous rows (i)
        for (int i = 0; i < row; i++) {
            
            // 1. Column Conflict: Check if there's a queen in the same column
            // board[i] is the column of the queen in row i
            if (board[i] == col) {
                return false;
            }
            
            // 2. Diagonal Conflict: Check if a queen is on the same diagonal
            // |i - row| == |board[i] - col|
            // (row difference equals column difference)
            if (Math.abs(i - row) == Math.abs(board[i] - col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts the 1D board array into a List of Strings for visual output.
     * 'Q' represents a queen, '.' represents an empty square.
     * @param board The solution array.
     * @return A List of Strings representing the formatted board.
     */
    private List<String> formatBoard(int[] board) {
        List<String> solution = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < N; col++) {
                if (board[row] == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            solution.add(sb.toString());
        }
        return solution;
    }

    /**
     * Prints all stored solutions and the total count.
     */
    private void printAllSolutions() {
        System.out.println("\n--- Found " + solutionCount + " Solutions for N = " + N + " ---");
        
        if (solutionCount == 0) return;
        
        for (int i = 0; i < allSolutions.size(); i++) {
            System.out.println("Solution " + (i + 1) + ":");
            for (String row : allSolutions.get(i)) {
                System.out.println(row);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = -1;

        // User Input Loop
        while (N < 1) {
            System.out.print("Enter the size of the board (N, where N >= 1): ");
            if (scanner.hasNextInt()) {
                N = scanner.nextInt();
                if (N < 1) {
                    System.out.println("N must be a positive integer.");
                } else if (N == 2 || N == 3) {
                    System.out.println("Note: No solution exists for N=2 or N=3.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume invalid input
            }
        }
        
        NQueens solver = new NQueens();
        solver.solve(N);
        
        scanner.close();
    }
}
