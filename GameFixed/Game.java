package GameFixed;

import java.util.ArrayList;
import java.util.*;
public class Game {
    private final ArrayList<Player> players;

    public Game(int n,int rows,int cols) {
        Scanner sc = new Scanner(System.in);
        players = new ArrayList();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter 1 if you want to add HumanPlayer 2 - RandomPlayer 3 - SequentialPlayer");
            int a = sc.nextInt();
            switch (a){
                case 1: players.add(new HumanPlayer());
                case 2: players.add(new RandomPlayer(rows,cols));
                case 3: players.add(new SequentialPlayer(rows,cols));
            }
            //players.add(new HumanPlayer());
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
