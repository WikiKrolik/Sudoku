package pl.first.firstjava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BoardTest {

    @Test
    public void testSudokuRepetitiveness() {
        SudokuSolver sudokuSolver1 = new BacktrackingSudokuSolver();
        SudokuSolver sudokuSolver2 = new BacktrackingSudokuSolver();

        SudokuBoard sudokuBoard1 = new SudokuBoard(sudokuSolver1);
        SudokuBoard sudokuBoard2 = new SudokuBoard(sudokuSolver2);

        sudokuBoard2.solveGame();
        sudokuBoard2.solveGame();

        Assertions.assertFalse(areBoardsTheSame(sudokuBoard1, sudokuBoard2));
    }

    @Test
    public void testIsSudokuCorrectWithBacktrackingGeneratedBoard() {
        boolean isCorrect = true;

        // create and generate correct board using backtracking solver
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        sudokuBoard.solveGame();

        // create empty control board and try to fill it using generated board numbers
        // if it can be done, this generated board was correct
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!BacktrackingSudokuSolver.isNumberSafe(testBoard, sudokuBoard.get(i, j), i, j)) {
                    isCorrect = false;
                    break outerLoop;
                } else {
                    testBoard.set(i, j, sudokuBoard.get(i, j));
                }
            }
        }

        Assertions.assertTrue(isCorrect);
    }

    @Test
    public void testIsSudokuCorrectWithIncorrectBoard() {
        boolean isCorrect = true;

        // create incorrect board
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        int[][] sudokuArray = {
                {1, 1, 1, 8, 1, 3, 5, 6, 7},
                {5, 1, 6, 4, 7, 2, 9, 3, 8},
                {7, 8, 3, 6, 5, 9, 2, 4, 1},
                {6, 7, 2, 1, 3, 4, 8, 5, 9},
                {3, 9, 5, 2, 8, 6, 1, 7, 4},
                {8, 4, 1, 7, 9, 5, 6, 2, 3},
                {1, 5, 8, 3, 6, 7, 4, 9, 2},
                {9, 3, 4, 5, 2, 8, 7, 1, 6},
                {2, 6, 7, 9, 4, 1, 3, 8, 5}
        };
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, sudokuArray[i][j]);
            }
        }

        // create empty control board and try to fill it using generated board numbers
        // if it can be done, this generated board was correct
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!BacktrackingSudokuSolver.isNumberSafe(testBoard, sudokuBoard.get(i, j), i, j)) {
                    isCorrect = false;
                    break outerLoop;
                } else {
                    testBoard.set(i, j, sudokuBoard.get(i, j));
                }
            }
        }

        Assertions.assertFalse(isCorrect);
    }

    @Test
    public void testIsSudokuCorrectWithCorrectBoard() {
        boolean isCorrect = true;

        // create correct board
        SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        int[][] sudokuArray = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, sudokuArray[i][j]);
            }
        }

        // create empty control board and try to fill it using generated board numbers
        // if it can be done, this generated board was correct
        SudokuSolver testSolver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(testSolver);

        outerLoop:
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!BacktrackingSudokuSolver.isNumberSafe(testBoard, sudokuBoard.get(i, j), i, j)) {
                    isCorrect = false;
                    break outerLoop;
                } else {
                    testBoard.set(i, j, sudokuBoard.get(i, j));
                }
            }
        }

        Assertions.assertTrue(isCorrect);
    }

    @Test
    public void testPrintMethodSudokuBoard() {
        // configure the test to check correctness of printing
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String expectedResult =
                "8 0 0 0 0 0 0 0 0 \n" +
                "0 0 3 6 0 0 0 0 0 \n" +
                "0 7 0 0 9 0 2 0 0 \n" +
                "0 5 0 0 0 7 0 0 0 \n" +
                "0 0 0 0 4 5 7 0 0 \n" +
                "0 0 0 1 0 0 0 3 0 \n" +
                "0 0 1 0 0 0 0 6 8 \n" +
                "0 0 8 5 0 0 0 1 0 \n" +
                "0 9 0 0 0 0 4 0 0 \n";
        int[][] array = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
        };
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        board.print();

        // remove the return character to fix annoying test bug
        Assertions.assertEquals(expectedResult.replaceAll("\r", "")
                , outputStreamCaptor.toString().replaceAll("\r", ""));
    }

    @Test
    public void testGetCorrectRowAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertTrue(board.getRow(0).verify());
    }

    @Test
    public void testGet() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertEquals(5, board.get(0, 0));
        Assertions.assertEquals(1, board.get(2, 0));
        Assertions.assertEquals(7, board.get(1, 1));
    }

    @Test
    public void testSolveGameForSolved() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        board.solveGame();

        // check for differences
        int diff = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
               if (board.get(i, j) != array[i][j]) {
                   diff++;
               }
            }
        }

        Assertions.assertEquals(0, diff);
    }

    @Test
    public void testSolveGameForFullBoardWithIncorrectBox() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {9, 7, 2, 1, 6, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        board.solveGame();

        // check for differences
        int diff = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i, j) != array[i][j]) {
                    diff++;
                }
            }
        }

        Assertions.assertEquals(0, diff);
    }
    @Test
    public void testSudokuFieldGroupGetter() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {9, 7, 2, 1, 6, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertEquals(5, board.getRow(0).getSudokuField(0).getFieldValue());
    }


    @Test
    public void testGetIncorrectRowAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {3, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertFalse(board.getRow(0).verify());
    }

    @Test
    public void testGetCorrectColumnAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertTrue(board.getColumn(0).verify());
    }

    @Test
    public void testGetIncorrectColumnAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {5, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertFalse(board.getColumn(0).verify());
    }

    @Test
    public void testGetCorrectBoxAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        Assertions.assertTrue(board.getBox(1,0).verify());
    }

    @Test
    public void testGetIncorrectBoxAndVerify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int[][] array = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        // set values from array to board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i, j, array[i][j]);
            }
        }

        board.print();

        // flip box 0 value to incorrect one
        board.set(0, 0, 3);

        Assertions.assertFalse(board.getBox(0,0).verify());
    }

    // private method used for testing
    private boolean areBoardsTheSame(SudokuBoard s1, SudokuBoard s2) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (s1.get(i, j) != s2.get(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }


    @Test
    public void ToStringTestSudokuBoard() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        s1.solveGame();
        try {
            String s = s1.toString();
            Assertions.assertNotSame(null, s);
        }
        catch (NullPointerException a) {
            System.out.println("Null values");
        }
    }

    @Test
    public void EqualsTestSudokuBoard() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    s1.set(i, j, 1);

            }
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++)
                    s2.set(i, j, 1);
            }
        }
        catch (NullPointerException a) {
            System.out.println("Null values");
        }

        boolean retval1 = s1.equals(s2);
        boolean retval2 = s2.equals(s1);

        Assertions.assertEquals(false, retval1);
        Assertions.assertEquals(false, retval2);
        Assertions.assertTrue( s1.hashCode() != s2.hashCode());
    }

    @Test
    public void HashCodeTestSudokuBoard() {
        SudokuBoard s1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard s2 = new SudokuBoard(new BacktrackingSudokuSolver());
        s1 = s2;
        Assertions.assertTrue(s1.equals(s2) && s2.equals(s1));
    }

}
