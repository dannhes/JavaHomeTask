package Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) throws IOException {
        //MyScanner s = new MyScanner(System.in);
        //Scanner ss = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] array = new int[10][];
        int[] mas = new int[10];
        int count = 0;
        String s = reader.readLine();
        while (s!=null) {
            int[] intmas = new int[10];
            int length = 0;
            MyScanner ss = new MyScanner(s);
            while (ss.hasNextInt()) {
                if (length == intmas.length) {
                    intmas = Arrays.copyOf(intmas, 2 * length);
                }
                intmas[length] = ss.nextIntNumber();
                length++;
            }
            if (count==array.length){
                array = Arrays.copyOf(array,2*count);
                mas = Arrays.copyOf(mas,2*count);
            }
            array[count] = intmas;
            mas[count]=length;
            count++;
            s = reader.readLine();
        }
        //s.close();
        for (int i = count - 1; i >= 0 ; i--) {
            for (int j = mas[i]-1; j >= 0; j--) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}