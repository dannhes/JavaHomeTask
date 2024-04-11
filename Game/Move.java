package Game;

public final class Move {
    private final int row;
    private final int column;
    private final boolean isColumn;
    private final int value;

    public Move(final int row, final int column, final int value, final boolean isColumn) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.isColumn = isColumn;
    }

    public boolean getIsColumn() {
        return isColumn;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "isColumn=" + isColumn + ", row=" + row + ", column=" + column + ", value=" + value;
    }
}
