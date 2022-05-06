package pl.first.firstjava;

import java.util.List;

public class SudokuColumn extends SudokuFieldGroup {

    public SudokuColumn(List<SudokuField> board) {
        super(board);
    }

    @Override
    public boolean verify() {
        return super.verify();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /*    @Override
    public void print() {
        for (int i = 0; i < 9; i++) {
            System.out.println(board[i].getFieldValue());
        }
    }*/
}
