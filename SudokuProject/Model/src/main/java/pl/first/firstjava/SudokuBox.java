package pl.first.firstjava;

import java.util.List;

public class SudokuBox extends SudokuFieldGroup {

    public SudokuBox(List<SudokuField> box) {
        super(box);
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
            System.out.print(board[i].getFieldValue() + " ");
            if (i % 3 == 2) {
                System.out.println("");
            }
        }
        System.out.println("");
    }*/
}
