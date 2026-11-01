
public class EightQueens {

    static int N = 8;
    static int[][] board = new int[N][N];

    // Check whether queen can be placed safely
    static boolean isSafe(int row, int col) {

        // Check left side of the row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Solve using Backtracking
    static boolean solve(int col) {

        // All queens are placed
        if (col >= N)
            return true;

        // Try every row in current column
        for (int row = 0; row < N; row++) {

            if (isSafe(row, col)) {

                board[row][col] = 1;

                // Recursively place next queen
                if (solve(col + 1))
                    return true;

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    // Display chessboard
    static void printBoard() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        if (solve(0)) {
            System.out.println("Solution Found:");
            printBoard();
        } else {
            System.out.println("No Solution Exists");
        }
    }
}
