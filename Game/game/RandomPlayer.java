package Game;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements Player {


    private final int m;

    private final int n;


    public RandomPlayer(int m,int n) {
        this.m = m;
        this.n =n;
    }



    @Override
    public Move move(final Position position, final Cell cell) {
        Random random = new Random();
        while (true) {
            int r = random.nextInt(m);
            int c = random.nextInt(n);
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
