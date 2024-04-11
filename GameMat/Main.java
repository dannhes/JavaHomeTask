package GameMat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */



public class Main {
    public static Integer closestDegree2(int n){
        int degree=1;
        while (degree*2<=n){
            degree*=2;
        }
        return degree;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите мнк,флаг(для круга или квадрата) в столбик");
        System.out.print("m=");
        int m = in.nextInt();
        System.out.print("n=");
        int n = in.nextInt();
        System.out.print("k=");
        int k = in.nextInt();
        System.out.print("Flag=");
        int flag = in.nextInt();
        System.out.print("Введите количество игроков турнира:");
        int countPlayers = in.nextInt();
        int[] players = new int[countPlayers];
        ArrayList<Integer> playernumb = new ArrayList<>();
        ArrayList<Integer> playerplace = new ArrayList<>();
        for (int i =0;i<countPlayers;i++){
            players[i]=i;
        }
        int c;
        int place = 0;
        if (closestDegree2(players.length)!=players.length){
            place = players.length;
            int[] playersnwxtround = new int[closestDegree2(players.length)];
            playersnwxtround = Arrays.copyOf(players,playersnwxtround.length);
            for (int i = 0;i<players.length-playersnwxtround.length;i++){
                final Game game = new Game(false, new RandomPlayer(m,n), new RandomPlayer(m,n));
                int result;
                do {
                    result = game.play(new TicTacToeBoard(m,n,k,flag));
                    if (result!=0){
                        if(result==2){
                            System.out.println("Plase of "+ playersnwxtround[i]+" player is "+place);
                            playernumb.add(playersnwxtround[i]);
                            playerplace.add(place);
                            playersnwxtround[i] = players[playersnwxtround.length+i];
                        }
                        else {
                            System.out.println("Plase of "+ players[playersnwxtround.length+i]+" player is "+place);
                            playernumb.add(players[playersnwxtround.length+i]);
                            playerplace.add(place);
                        }
                        System.out.println("Winner is player number "+playersnwxtround[i]);

                    }
                } while (result != 1 && result!=2);
            }
            players = Arrays.copyOf(playersnwxtround,playersnwxtround.length);
        }
        while (players.length!=1){
            place = players.length;
            int[] playersnwxtround = new int[players.length/2];
            c = 0;
            for(int i = 0;i<players.length-1;i+=2){
                final Game game = new Game(false, new SequentialPlayer(m,n), new HumanPlayer(m,n));
                int result;
                do {
                    result = game.play(new TicTacToeBoard(m,n,k,flag));
                    if (result!=0){
                        System.out.println("Now playing players number: "+players[i]+" and "+players[i+1]);
                        if(result==1){
                            System.out.println("Winner is player number "+players[i]);
                            System.out.println("Plase of "+ players[i+1]+" player is "+place);
                            playernumb.add(players[i+1]);
                            playerplace.add(place);
                            playersnwxtround[c] = players[i];
                        }
                        else{
                            System.out.println("Winner is player number "+players[i+1]);
                            System.out.println("Plase of "+ players[i]+" player is "+place);
                            playernumb.add(players[i]);
                            playerplace.add(place);
                            playersnwxtround[c] = players[i+1];
                        }
                    }
                } while (result != 1 && result!=2);
                c++;
            }
            players = Arrays.copyOf(playersnwxtround,playersnwxtround.length);
        }
        System.out.println();
        System.out.println("Winner of tournament is "+players[0]);
        playernumb.add(players[0]);
        playerplace.add(1);
        System.out.println("Если хотите вывести результат в виде массива введите 1");
        int flag2 = in.nextInt();
        if(flag2==1){
            for (int i = 0;i<playerplace.size();i++){
                System.out.println("Player " + playernumb.get(i) + " place is " + playerplace.get(i));
            }
        }
    }
}
