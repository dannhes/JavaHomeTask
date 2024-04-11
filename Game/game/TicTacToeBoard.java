package Game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class TicTacToeBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.P, ' '

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

    public void addToCircle(int i,int j,int m,int a,int b){
        if (isInCircle(i+a,j+b,m)){
            cells[i][j]=Cell.E;
            countEmpCells+=1;
        }
        else {
            cells[i][j]=Cell.P;
        }
    }
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
            countEmpCells = m*n;
        }
        else if (flag==1 && m==n){
            if (m%2!=0){
                for(int i = 0;i<m;i++){
                    for (int j = 0;j<n;j++){
                        addToCircle(i,j,m/2,0,0);
                    }
                }
            } else {
                for (int i = 0;i<m;i++){
                    for(int j = 0;j<n;j++){
                        if(i<m/2 && j<m/2){
                            addToCircle(i,j,m/2-1,0,0);
                        } else if (i>=m/2 && j<m/2){
                            addToCircle(i,j,m/2-1,-1,0);
                        } else if (i<m/2 && j>=m/2) {
                            addToCircle(i,j,m/2-1,0,-1);
                        } else if (i>=m/2 && j>=m/2) {
                            addToCircle(i,j,m/2-1,-1,-1);
                        }
                    }
                }
            }
        } else {
            throw new  IllegalStateException("Illegal flag, flag only can be 1 or 0");
        }
        turn = Cell.X;
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
        if (!isValid(move)) {
            return Result.LOSE;
        }
        countEmpCells-=1;
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int currRow = move.getRow();
        int currColumn = move.getColumn();
        int maxinrow=checkerwin(currRow,currColumn,k,1,0);
        int maxincol=checkerwin(currRow,currColumn,k,0,1);
        if (maxinrow >= k || maxincol >= k) {
            return Result.WIN;
        }
        int MaxInDiag1 = checkerwin(currRow,currColumn,k,1,1);
        int MaxInDiag2 = checkerwin(currRow,currColumn,k,-1,1);
        int maxIndiag = Math.max(MaxInDiag1,MaxInDiag2);
        int maxInCol4 = checkernexrmove(currRow,currColumn,1,0);
        int maxInrow4 = checkernexrmove(currRow,currColumn,0,1);
        if (maxIndiag>=k){
            return Result.WIN;
        }
        if(countEmpCells==0){
            return Result.DRAW;
        }
        int inDiag1 = checkernexrmove(currRow,currColumn,1,1);
        int inDiag2 = checkernexrmove(currRow,currColumn,-1,1);
        int MaxIndiag = Math.max(inDiag1,inDiag2);
        if (turn == Cell.X && (changeturn(maxInCol4,maxInrow4,MaxIndiag))){
            turn = Cell.O;
        } else if (turn == Cell.X) {
            return Result.Anothher;
        } else if(turn==Cell.O && (changeturn(maxInCol4,maxInrow4,MaxIndiag))) {
            turn = Cell.X;

        } else {
            turn = Cell.O;
            return Result.Anothher;
        }
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
