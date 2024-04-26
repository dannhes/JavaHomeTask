package GameFixed;

public class TicTacToeBoard implements Board {
    private final TicTacToePosition pos;
    private final int[][] columns, rows;
    private final int[] results;
    private int turn, count;
    private final int playersCount, n, m;

    public TicTacToeBoard(int n, int m, TicTacToePosition pos, int turn, int playersCount) {
        if (n < 0 || m < 0) {
            throw new IllegalArgumentException("Numbers cannot be negative");
        }
        columns = new int[n - 1][m];
        rows = new int[n][m - 1];
        results = new int[playersCount];
        count = 2 * n * m - n - m;
        this.playersCount = playersCount;
        this.turn = turn;
        this.pos = pos;
        this.m = m;
        this.n = n;
    }

    @Override
    public int getVal() {return turn;}

    @Override
    public Position getPosition() {
        return this.pos;
    }

    @Override
    public boolean drawCheck() {
        int mx = 0;
        int mxCount = 0;
        for (int i = 0; i < playersCount; i++) {
            if (results[i] > mx) {
                mx = results[i];
                mxCount = 1;
            } else if (results[i] == mx) {
                mxCount++;
            }
        }
        return mxCount > 1;
    }

    @Override
    public int getWinner() {
        int ind = 0;
        for (int i = 0; i < playersCount; i++) {
            if (results[ind] < results[i]) {
                ind = i;
            }
        }
        return ind;
    }

    @Override
    public Result makeMove(final Move move) {
        if (count == 0) {
            return Result.ENDED;
        }
        if (!isValid(move)) {
            System.out.println("Incorrect move!");
            return Result.INCORRECT;
        }


        int i = move.getRow();
        int j = move.getColumn();
        if (move.getIsColumn()) {
            columns[i][j] = 1;
            if (j > 0 && columns[i][j - 1] == 1 && rows[i][j - 1] == 1 && rows[i + 1][j - 1] == 1) {
                results[move.getValue()]++;
            }
            if (j < m - 1 && columns[i][j + 1] == 1 && rows[i][j] == 1 && rows[i + 1][j] == 1) {
                results[move.getValue()]++;
            }
        } else {
            rows[move.getRow()][move.getColumn()] = 1;
            if (i > 0 && rows[i - 1][j] == 1 && columns[i - 1][j] == 1 && columns[i - 1][j + 1] == 1) {
                results[move.getValue()]++;
            }
            if (i < n - 1 && rows[i + 1][j] == 1 && columns[i][j] == 1 && columns[i][j + 1] == 1) {
                results[move.getValue()]++;
            }
        }
        pos.set(columns, rows);
        count--;
        if (count == 0) {
            return Result.ENDED;
        }

        turn = turn == playersCount ? 1 : turn + 1;
        return Result.UNKNOWN;
    }

    public boolean isValid(final Move move) {
        return pos.isValid(move);
    }

    public void print() {
        pos.print();
    }
}
