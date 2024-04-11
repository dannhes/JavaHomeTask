package GameReversy;

import java.util.Arrays;
import java.util.Map;
import GameReversy.Cell;

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
    int countMoves = 0;

    int countFirst= 0;
    int countSecond = 0;


    private final int n;
    private int countEmpCells=0;
    private final int k;

    private final int flag;
    int mas[][] = new int[100][100];

    public void addToCircle(int i,int j,int m,int a,int b){
        if (isInCircle(i+a,j+b,m)){
            cells[i][j]= Cell.E;
            countEmpCells+=1;
        }
        else {
            cells[i][j]= Cell.P;
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

    public Integer chWin(int curRow,int curCol,int sdvx,int sdvy){
        int valid = 0;
        for(int i = 0;i<m;i++){
            if (curCol+sdvy*i>=0 && curCol+sdvy*i<m && curRow+sdvx*i>=0 && curRow+sdvx*i<n && i!=0){
                if (cells[curRow+sdvx*i][curCol+sdvy*i]==Cell.E){
                    break;
                }
                if (cells[curRow+sdvx*i][curCol+sdvy*i]==turn){
                       int coord1 = curRow+sdvx*i;
                       int coord2 = curCol+sdvx*i;
                       valid = 1;
                       for (int j = 0;j<i;j++){
                           cells[curRow+sdvx*j][curCol+sdvy*j]=turn;
                       }
                       break;
                }
            }
        }
        return valid;
    }

    public Integer cWin(int curRow,int curCol,int sdvx,int sdvy){
        int valid = 0;
        int countChagne=0;

        for(int i = 0;i<m;i++){
            if (curCol+sdvy*i>=0 && curCol+sdvy*i<m && curRow+sdvx*i>=0 && curRow+sdvx*i<n && i!=0){

                if (cells[curRow+sdvx*i][curCol+sdvy*i]==Cell.E){
                    //valid = 0;
                    return 0;
                }
                if (i!=1){
                    if (cells[curRow+sdvx*i][curCol+sdvy*i]==turn){

                        return 1;
                    }
                } else if(i==1){
                    if (cells[curRow+sdvx*i][curCol+sdvy*i]==turn){
                        return 0;
                    }
                }

            }
        }
        return valid;
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
            cells[2][2] = Cell.X;
            cells[3][3] = Cell.X;
            cells[3][2] = Cell.O;
            cells[2][3] = Cell.O;
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

    public TicTacToeBoard(Cell[][] cells, Cell turn, int m, int n, int k, int flag){
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

    public boolean Valid(int currRow,int currColumn){
        int maxincol1 = cWin(currRow,currColumn,1,0);
        int maxincol2 = cWin(currRow,currColumn,-1,0);
        int maxinrow1 = cWin(currRow,currColumn,0,1);
        int maxinrow2 = cWin(currRow,currColumn,0,-1);
        int maxIndiag1 = cWin(currRow,currColumn,1,1);
        int maxIndiag2 = cWin(currRow,currColumn,1,-1);
        int maxIndiag3 = cWin(currRow,currColumn,-1,1);
        int maxIndiag4 = cWin(currRow,currColumn,-1,-1);
        return !(maxincol1+maxincol2+maxinrow2+maxinrow1+maxIndiag1+maxIndiag2+maxIndiag3+maxIndiag4==0);
    }


    public Result makeMove(final Move move) {
        isValid(move);
        /*if (!isValid(move)) {
            return Result.LOSE;
        }*/
        countEmpCells-=1;
        cells[move.getRow()][move.getColumn()] = move.getValue();
        int currRow = move.getRow();
        int currColumn = move.getColumn();
        int maxincol1 = chWin(currRow,currColumn,1,0);
        int maxincol2 = chWin(currRow,currColumn,-1,0);
        int maxinrow1 = chWin(currRow,currColumn,0,1);
        int maxinrow2 = chWin(currRow,currColumn,0,-1);
        int maxIndiag1 = chWin(currRow,currColumn,1,1);
        int maxIndiag2 = chWin(currRow,currColumn,1,-1);
        int maxIndiag3 = chWin(currRow,currColumn,-1,1);
        int maxIndiag4 = chWin(currRow,currColumn,-1,-1);

        if(countEmpCells==0){
            int count = 0;
            for(int i = 0;i<m;i++){
                for (int j = 0;j<n;j++){
                    if (cells[i][j]==Cell.X){
                        count+=1;
                    }
                }
            }
            if (turn== Cell.X){
                if (count>m*n/2){
                    return Result.WIN;
                }
            } else if (turn== Cell.O) {
                if (count>m*n/2){
                    return Result.WIN;
                }
            }
            return Result.DRAW;
        }

        if(turn== Cell.O) {
            turn = Cell.X;
        } else {
            turn = Cell.O;
        }
        int countCells = 0;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if (cells[i][j]==Cell.E){
                    if (Valid(i,j)){
                        System.out.println("valid cell " + i + " " + j);
                        countCells+=1;
                    }
                }
            }
        }
        if (countCells==0){
            if (turn==Cell.X){
                turn = Cell.O;
            }else {
                turn=Cell.X;
            }
            for(int i = 0;i<m;i++){
                for(int j = 0;j<n;j++){
                    if (cells[i][j]==Cell.E){
                        if (Valid(i,j)){
                            //System.out.println("valid cell " + i + " " + j);
                            countCells+=1;
                        }
                    }
                }
            }
            if (countCells==0){
                int count = 0;
                for(int i = 0;i<m;i++){
                    for (int j = 0;j<n;j++){
                        if (cells[i][j]==Cell.X){
                            count+=1;
                        }
                    }
                }
                if (turn== Cell.X){
                    if (count>m*n/2){
                        return Result.WIN;
                    }
                } else if (turn== Cell.O) {
                    if (count>m*n/2){
                        return Result.WIN;
                    }
                }
                return Result.DRAW;
            }
            return Result.UNKNOWN;
        }
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        int currRow = move.getRow();
        int currColumn = move.getColumn();
        int maxincol1 = cWin(currRow,currColumn,1,0);
        int maxincol2 = cWin(currRow,currColumn,-1,0);
        int maxinrow1 = cWin(currRow,currColumn,0,1);
        int maxinrow2 = cWin(currRow,currColumn,0,-1);
        int maxIndiag1 = cWin(currRow,currColumn,1,1);
        int maxIndiag2 = cWin(currRow,currColumn,1,-1);
        int maxIndiag3 = cWin(currRow,currColumn,-1,1);
        int maxIndiag4 = cWin(currRow,currColumn,-1,-1);
        System.out.println("All"+ maxincol1+ maxincol2 + maxinrow2+ maxinrow1+ maxIndiag1+maxIndiag2+maxIndiag3+maxIndiag4);
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E && (maxincol1+maxincol2+maxIndiag3+maxIndiag4+maxIndiag1+maxIndiag2+maxinrow1+maxinrow2)!=0
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
