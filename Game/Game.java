package Game;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;

    public Game(int n) {
        players = new ArrayList();
        for (int i = 0; i < n; i++) {
            players.add(new HumanPlayer());
        }
    }

    public void play(Board board) {
        int i = 0;
        int mod = players.size();
        while (true) {
            final int result1 = move(board, players.get(i), i + 1);
            if (result1 == 1) {
                break;
            }
            i = (i + 1) % mod;
        }
        if (board.drawCheck()) {
            System.out.println("The result is DRAW!");
        } else {
            System.out.println("The WINNER is player number: " + board.getWinner());
        }
    }

    private int move(final Board board, Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getVal());
        final Result result = board.makeMove(move);
        board.print();
        System.out.println("Player " + no + " make move: " + move);
        if (result == Result.ENDED) {
            return 1;
        }
        return 0;
    }
}
