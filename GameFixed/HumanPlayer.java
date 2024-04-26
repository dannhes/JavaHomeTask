package GameFixed;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer() {
        this.out = System.out;
        this.in = new Scanner(System.in);
    }

    @Override
    public Move move(final Position position, final int cell) {
        while (true) {
            out.println("Position");
            position.print();
            out.println(cell + "'s move");
            out.println("Enter isColumn(0 - rows, 1 - columns), row and column");
            int r, c;
            boolean isColumn;
            while (true) {
                try {
                    isColumn = Integer.parseInt(in.next()) == 1;
                    r = Integer.parseInt(in.next());
                    c = Integer.parseInt(in.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Input String cannot be parsed to Integer. Try again.");
                }
            }
            final Move move = new Move(r, c, cell, isColumn);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Move " + move + " is invalid");
        }
    }
}
