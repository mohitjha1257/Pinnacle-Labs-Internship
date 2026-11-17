import java.util.Scanner;

public class ticTacToe {
    // A 3x3 2D array to represent the board
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("Welcome to Classic Tic-Tac-Toe!");
        printBoard();

        // Main game loop
        while (!gameEnded) {
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3] separated by space): ");
            int row = -1;
            int col = -1;

            // Handle user input
            try {
                row = scanner.nextInt() - 1; // Subtract 1 for 0-indexed array
                col = scanner.nextInt() - 1;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine(); // Clear the bad input
                continue;
            }

            // Check if move is within bounds and space is empty
            if (row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == '-') {
                board[row][col] = currentPlayer;
                printBoard();

                // Check for win or draw conditions
                if (checkForWin()) {
                    System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull()) {
                    System.out.println("The game is a draw!");
                    gameEnded = true;
                } else {
                    // Switch players if the game is still going
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("This move is not valid. The cell might be taken or out of bounds. Try again.");
            }
        }
        scanner.close();
    }

    // Fills the board with empty dashes at the start
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Prints the current state of the board in a grid format
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Checks if there are no empty spaces left
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Checks rows, columns, and diagonals for three matching characters
    private static boolean checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) return true; // Rows
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) return true; // Columns
        }
        // Check diagonals
        if (checkRowCol(board[0][0], board[1][1], board[2][2])) return true;
        if (checkRowCol(board[0][2], board[1][1], board[2][0])) return true;

        return false;
    }

    // Helper method to verify three characters are the same and not empty dashes
    private static boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }
}