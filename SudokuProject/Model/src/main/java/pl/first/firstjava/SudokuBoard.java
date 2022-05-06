package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuBoard {
    private static final int SIZE = 9;

    private final List<SudokuField> board;
    private final SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        this.board = Arrays.asList(new SudokuField[SIZE * SIZE]);
        for (int i = 0; i < SIZE * SIZE; i++) {
            this.board.set(i, new SudokuField());
        }
        this.sudokuSolver = sudokuSolver;
    }

    /// returns int value of a field specified using index
    public int get(int row, int col) {
        return board.get(row * 9 + col).getFieldValue();
    }

    public void set(int row, int col, int val) {
        board.get(row * 9 + col).setFieldValue(val);
    }

    public void solveGame() {
        if (this.checkBoard()) {
            return;
        }
        this.sudokuSolver.solve(this);
    }

    public void print() {
        for (int i = 0; i < SIZE * SIZE; i++) {
            System.out.print(board.get(i).getFieldValue() + " ");
            if ((i + 1) % 9 == 0) {
                System.out.println("");
            }
        }
    }

    private boolean checkBoard() {
        // verify rows and columns
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify() || !getColumn(i).verify()) {
               return false;
            }
        }

        // verify box
        for (int i = 0; i < 9; i++) {
            if (!getBox(i % 3, i / 3).verify()) {
                return false;
            }
        }
        return true;
    }

    public SudokuRow getRow(int y) {
        // no need to change array to Java collection class, it is not exposed to the user
        List<SudokuField> sudokuRow = Arrays.asList(new SudokuField[SIZE]);

        for (int i = 0; i < sudokuRow.size(); i++) {
            sudokuRow.set(i, new SudokuField()); // bez tego nie dziala :)
            sudokuRow.get(i).setFieldValue(get(y, i));

        }
        return new SudokuRow(sudokuRow);
    }

    public SudokuColumn getColumn(int x) {
        List<SudokuField> sudokuColumn = Arrays.asList(new SudokuField[SIZE]);

        for (int i = 0; i < sudokuColumn.size(); i++) {
            sudokuColumn.set(i, new SudokuField()); // bez tego nie dziala :)
            sudokuColumn.get(i).setFieldValue(get(i, x));

        }
        return new SudokuColumn(sudokuColumn);
    }

    /**
     * +---+---+---+<br>.
     * | 0 0 | 0 1 | 0 2 |<br>
     * +---+---+---+<br>
     * | 1 0 | 1 1 | 1 2 |<br>
     * +---+---+---+<br>
     * | 2 0 | 2 1 | 2 2 |<br>
     * +---+---+---+<br>
     * @param row Row of the box
     * @param col Column of the box
     * @return SudokuBox object
     */

    public SudokuBox getBox(int row, int col) {
        List<SudokuField> sudokuBox = Arrays.asList(new SudokuField[SIZE]);
        int startRow = row * 3;
        int startCol = col * 3;
        int index = 0;

        for (int i = 0; i < 9; i++) {
            sudokuBox.set(i, new SudokuField());
        }

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                sudokuBox.get(index).setFieldValue(get(i, j));
                index++;
            }
        }
        return new SudokuBox(sudokuBox);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuBoard)) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(that))
                .append(board, that.board)
                .append(sudokuSolver, that.sudokuSolver)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).append(sudokuSolver).toHashCode();
    }

    @Override
    public String toString() {
        return  new ToStringBuilder(this)
                .append("board", board)
                .append("sudokuSolver", sudokuSolver)
                .toString();
    }
}
