package Game;

public class TicTacToePosition implements Position {
    private int[][] rows, columns;
    private final int n, m;

    public TicTacToePosition(int n, int m) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Numbers cannot be negative");
        }
        this.rows = new int[n][m - 1];
        this.columns = new int[n - 1][m];
        this.n = n;
        this.m = m;
    }

    public void set(int[][] columns, int [][] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public boolean isValid(final Move move) {
        if (move.getIsColumn()) {
            return (0 <= move.getRow() && move.getRow() < n - 1
                    && 0 <= move.getColumn() && move.getColumn() < m
                    && columns[move.getRow()][move.getColumn()] == 0);
        } else {
            return (0 <= move.getRow() && move.getRow() < n
                    && 0 <= move.getColumn() && move.getColumn() < m - 1
                    && rows[move.getRow()][move.getColumn()] == 0);
        }
    }

    public void print() {
        System.out.println("The rows are: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - 1; j++) {
                System.out.print(rows[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("The columns are: ");
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(columns[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
