package GameMat;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class SequentialPlayer implements Player {


    public final int m;

    public final int n;



    public SequentialPlayer(int m,int n) {
        //this.random = random;
        this.m = m;
        this.n =n;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        int rowBlackKing=0;
        int colBlackKing=0;
        int rowWhiteKing=0;
        int colWhiteKing=0;
        int rowWhiteRook=0;
        int colWhiteRook=0;
        System.out.println("Position");
        System.out.println(position);
        System.out.println(cell + "'s move");
        System.out.println("Enter row and column");
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if(position.getCell(r,c)==Cell.BlackKing){
                    rowBlackKing = r;
                    colBlackKing = c;
                }
                if (position.getCell(r,c)==Cell.WhiteKing){
                    colWhiteKing = c;
                    rowWhiteKing = r;
                }
                if (position.getCell(r,c)==Cell.WhiteRook){
                    rowWhiteRook = r;
                    colWhiteRook = c;
                }

                /*final Move move = new Move(r, c, Cell.BlackKing);
                if (position.isValid(move)) {
                    return move;
                }*/
            }
        }
        if (Math.abs(colWhiteRook-colBlackKing)<=1){
            //System.out.println("Col is" + colWhiteRook);
            if (colWhiteRook>5){
                //System.out.println("I should go here");
                colWhiteRook=0;
            }
            else if (colWhiteRook<2){
                //System.out.println("But i go here ");
                colWhiteRook = 7;
            } else {
                colWhiteRook=7;
            }
            System.out.println("move to "+rowWhiteRook+" "+ colWhiteRook+ " Rook");
            return new Move(rowWhiteRook, colWhiteRook, Cell.WhiteRook);
        } else if (Math.abs(rowWhiteRook-rowBlackKing)!=1){
            if (rowBlackKing==0 ){
                rowWhiteRook = 1;
            }else if (rowBlackKing==7){
                rowWhiteRook = 6;
            } else {
                if (rowBlackKing>=4){
                    rowWhiteRook = rowBlackKing-1;
                }else {
                    rowWhiteRook = rowBlackKing+1;
                }
            }
            System.out.println("move to "+rowWhiteRook+" "+ colWhiteRook+ " Rook");
            return new Move(rowWhiteRook, colWhiteRook, Cell.WhiteRook);
        } else{
            if (rowWhiteRook>=4){
                if (rowWhiteKing>rowWhiteRook){
                    rowWhiteKing-=1;
                    System.out.println("move to ");
                    return new Move(rowWhiteKing, colWhiteKing, Cell.WhiteKing);
                } else {
                    if (rowWhiteKing-rowBlackKing!=0){
                        if (colWhiteKing-colBlackKing!=0){
                            if (colWhiteKing>colBlackKing){
                                colWhiteKing-=1;
                            } else {
                                colWhiteKing+=1;
                            }
                        }
                        if (rowWhiteKing>rowBlackKing){
                            rowWhiteKing-=1;
                        } else {
                            rowWhiteKing+=1;
                        }
                    }
                    System.out.println("move to ");
                    return new Move(rowWhiteKing, colWhiteKing, Cell.WhiteKing);
                }
            }else {
                if (rowWhiteKing<rowWhiteRook){
                    rowWhiteKing+=1;
                    System.out.println("move to ");
                    return new Move(rowWhiteKing, colWhiteKing, Cell.WhiteKing);
                }else {
                    if (rowWhiteKing-rowBlackKing!=0){
                        if (colWhiteKing-colBlackKing!=0){
                            if (colWhiteKing>colBlackKing){
                                colWhiteKing-=1;
                            } else {
                                colWhiteKing+=1;
                            }
                        }
                        if (rowWhiteKing>rowBlackKing){
                            rowWhiteKing-=1;
                        } else {
                            rowWhiteKing+=1;
                        }
                    }
                    System.out.println("move to ");
                    return new Move(rowWhiteKing, colWhiteKing, Cell.WhiteKing);
                }
            }
        }
    }
}
