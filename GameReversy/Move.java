package GameReversy;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class Move {
    private final int row;
    private final int column;
    private final Cell value;

/*    private Scanner in = new Scanner(System.in);
    private final int m = in.nextInt();
    private final int n = in.nextInt();

    private final int k = in.nextInt();*/

    public Move(final int row, final int column, final Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
        //this.m = m;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "row=" + row + ", column=" + column + ", value=" + value;
    }
}
