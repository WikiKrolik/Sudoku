package pl.first.firstjava;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public abstract class SudokuFieldGroup {

    public static final int SIZE = 9;
    private final List<SudokuField> sudokuFields;

    public SudokuFieldGroup(List<SudokuField> row) {
        sudokuFields = Arrays.asList(new SudokuField[SIZE]);
        for (int i = 0; i < SIZE; i++) {
            sudokuFields.set(i,row.get(i));
        }
    }

    public SudokuField getSudokuField(int i) {
        return sudokuFields.get(i);
    }

    public boolean verify() {
        for (SudokuField x: sudokuFields) {
            for (SudokuField y: sudokuFields) {
                if (x.getFieldValue() == y.getFieldValue() && x != y || (x.getFieldValue() == 0
                        || y.getFieldValue() == 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SudokuFieldGroup)) {
            return false;
        }

        SudokuFieldGroup that = (SudokuFieldGroup) o;

        return new EqualsBuilder()
                .append(sudokuFields, that.sudokuFields)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuFields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return  new ToStringBuilder(this)
                .append("sudokuFields", sudokuFields)
                .toString();
    }
}
