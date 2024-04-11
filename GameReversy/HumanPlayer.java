package GameReversy;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    public final int m;
    public final int n;
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public HumanPlayer(int m,int n) {
        this.m = m;
        this.n=n;
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            String a = in.next();
            String b = in.next();
            if (isNumeric(a) && isNumeric(b)){
                int c = Integer.parseInt(a);
                int d = Integer.parseInt(b);
                final Move move = new Move(c,d, cell);
                if (position.isValid(move)) {
                    return move;
                }else {
                    out.println("Move " + move + " is invalid");
                }
            }
            else {
                out.println("Move row=" + a + " column=" + b + " value="+ cell+ " is invalid");
            }
        }
    }
}
