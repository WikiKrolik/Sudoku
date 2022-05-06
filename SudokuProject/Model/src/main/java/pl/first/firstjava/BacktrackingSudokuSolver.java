package pl.first.firstjava;

import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public boolean solve(SudokuBoard board) {
        Random random = new Random();
        int solveSeed = random.nextInt(9);

        // iterate over each row
        for (int row = 0; row < 9; row++) {
            // iterate over each number in that row
            for (int col = 0; col < 9; col++) {
                // if a number is equal to 0, try to solve it
                if (board.get(row, col) == 0) {
                    // iterate over each possible value in that spot
                    for (int number = 1; number <= 9; number++) {
                        int tryNumber = (number + solveSeed) % 9 + 1;
                        // if the number is safe try to solve the board recursively
                        if (isNumberSafe(board, tryNumber, row, col)) {
                            board.set(row, col, tryNumber);
                            // if the board is solved recursively, return true
                            // else, the number is incorrect and try for another value. Midaaaaas
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(row, col, 0);
                            }
                        }
                    }
                    // after iterating over all values the board wasn't solved, it is unsolvable
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isNumberSafe(SudokuBoard board, int num, int row, int col) {
        // Check if this number is already in the row
        for (int i = 0; i < 9; i++) {
            if (board.get(row, i) == num) {
                return false;
            }
        }

        // Check if this number is already in the column
        for (int i = 0; i < 9; i++) {
            if (board.get(i, col) == num) {
                return false;
            }
        }

        // Check if this number is already in the 3x3 block
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i + startRow, j + startCol) == num) {
                    return false;
                }
            }
        }

        return true;
    }


}


