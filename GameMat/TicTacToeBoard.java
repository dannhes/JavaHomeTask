package GameMat;


import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.BlackKing, '♚',
            Cell.WhiteKing, '♔',
            Cell.E, '.',
            Cell.WhiteRook, '♖'
    );
    public boolean isInCircle(int i,int j,int m){
        return ((m-i)*(m-i)+(m-j)*(m-j))<=(m)*(m);
    }

    private final Cell[][] cells;
    private Cell turn;

    private final int m;
    private final int n;
    private int countEmpCells=0;
    private final int k;

    private final int flag;

    public Integer checkerwin(int curRow,int curCol,int k,int sdvx,int sdvy){
        int inRow = 0;
        int maxWin=0;
        for(int i = 1-k;i<k;i++){
            if (curCol+sdvy*i>=0 && curCol+sdvy*i<m && curRow+sdvx*i>=0 && curRow+sdvx*i<n){
                if (cells[curRow+sdvx*i][curCol+sdvy*i]==turn){
                    inRow++;
                }
                else {
                    maxWin = Math.max(maxWin,inRow);
                    inRow=0;
                }
            }
        }
        maxWin = Math.max(maxWin,inRow);
        return maxWin;
    }
    public Integer checkernexrmove(int curRow,int curCol,int sdvx,int sdvy){
        int inRow = 0;
        int inRow1=0;
        int maxWin=0;
        int maxWin1=0;
        for(int i = -4;i<=4;i++){
            if (curCol+sdvy*i>=0 && curCol+sdvy*i<m && curRow+sdvx*i>=0 && curRow+sdvx*i<n){
                if (cells[curRow+sdvx*i][curCol+sdvy*i]==turn){
                    if (i!=4 && i!=-4){
                        inRow1++;
                    }
                    inRow++;
                    if (i==0){
                        inRow--;
                        maxWin = Math.max(maxWin,inRow);
                        inRow=0;
                    }
                }
                else {
                    maxWin1=Math.max(maxWin1,inRow1);
                    maxWin=Math.max(maxWin,inRow);
                    inRow1=0;
                }
            }
        }
        maxWin = Math.max(maxWin,inRow);
        maxWin1=Math.max(maxWin1,inRow1);
        if (maxWin==4){
            maxWin1=0;
        }
        return maxWin1;
    }

    public TicTacToeBoard(int m,int n,int k,int flag) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.flag = flag;
        this.cells = new Cell[m][n];
        if (flag == 0){
            for (Cell[] row : cells) {
                Arrays.fill(row, Cell.E);
            }
            cells[3][4] = Cell.BlackKing;
            cells[5][6] = Cell.WhiteRook;
            cells[5][1] = Cell.WhiteKing;
            countEmpCells = m*n;
        }
        else {
            throw new  IllegalStateException("Illegal flag, flag only can be 1 or 0");
        }
        turn = Cell.WhiteRook;
    }

    public boolean canMove(int curCol,int curRow,int rowRook,int colRook,int rowWKing,int colWKing){
        if (curCol == colWKing){
            if (Math.abs(curRow-rowWKing)==1){
                if (Math.abs(curRow-rowRook)==1){
                    return false;
                }
            }
        }
        return true;
    }

    public TicTacToeBoard(Cell[][] cells, Cell turn,int m,int n,int k,int flag){
        this.cells = Arrays.copyOf(cells,cells.length);
        this.turn = turn;
        this.m = m;
        this.n = n;
        this.k = k;
        this.flag = flag;
    }
    @Override
    public Position getPosition() {
        return new TicTacToeBoard(cells,turn,m,n,k,flag);
    }

    @Override
    public Cell getCell() {
        return turn;
    }
    public boolean changeturn(int a,int b,int x){
        return (a<4 && b<4 && x<4);
    }
    @Override
    public Result makeMove(final Move move) {
        isValid(move);
        countEmpCells-=1;

        cells[move.getRow()][move.getColumn()] = move.getValue();
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                if (cells[i][j]==move.getValue() && (i!=move.getRow() || j!= move.getColumn())){
                    cells[i][j] = Cell.E;
                }
            }
        }
        int rowBlackKing=0;
        int colBlackKing=0;
        int rowWhiteKing=0;
        int colWhiteKing=0;
        int rowWhiteRook=0;
        int colWhiteRook=0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if(cells[r][c]==Cell.BlackKing){
                    rowBlackKing = r;
                    colBlackKing = c;
                }
                if (cells[r][c]==Cell.WhiteKing){
                    colWhiteKing = c;
                    rowWhiteKing = r;
                }
                if (cells[r][c]==Cell.WhiteRook){
                    rowWhiteRook = r;
                    colWhiteRook = c;
                }
            }
        }
        if (!canMove(colBlackKing,rowBlackKing,rowWhiteRook,colWhiteRook,rowWhiteKing,colWhiteKing)){
            if (turn == Cell.WhiteRook){
                return Result.WIN;
            } else {
                return Result.LOSE;
            }
        }
        if (turn==Cell.WhiteKing || turn ==Cell.WhiteRook){
            turn = Cell.BlackKing;
        }else if (turn==Cell.BlackKing){
            turn = Cell.WhiteRook;
        }
        //if cant move : lose
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i =0;i<n;i++) {
            sb.append(i).append(" ");
        }
        for (int r = 0; r < m; r++) {
            sb.append("\n");
            sb.append(r).append(" ");
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ");
            }
        }
        return sb.toString();
    }
}
