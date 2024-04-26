package GameFixed;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the field (n and m) ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter count of players");
        int playersCount = scanner.nextInt();
        Game game = new Game(2,m,n);
        game.play(new TicTacToeBoard(2, 2, new TicTacToePosition(2,2), 1, 3));
    }
}
