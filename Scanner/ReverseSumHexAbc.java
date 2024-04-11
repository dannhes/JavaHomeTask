package Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class ReverseSumHexAbc {
    public static void main(String[] args) throws IOException {
        //MyScanner linescanner = new MyScanner(System.in);
        int[] mas = new int[10];
        int i = 0;
        int temp_el;
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        String s = scanner.readLine();
        while (s!=null) {
            MyScanner linescanner = new MyScanner(s);
            int prefsum = 0;
            while (linescanner.hasNextHexOrInt()) {
                if (i == mas.length) {
                    mas = Arrays.copyOf(mas , i * 2);
                }
                temp_el = linescanner.nextHexOrInt();
                prefsum += mas[i] + temp_el;
                String word = Integer.toString(prefsum);
                StringBuilder abc = new StringBuilder();
                for (int j = 0; j < word.length(); j++) {
                    char curr = word.charAt(j);
                    if (curr == '-') {
                        abc.append('-');
                    } else if (curr == '0') {
                        abc.append('a');
                    } else if (curr == '1') {
                        abc.append('b');
                    } else if (curr == '2') {
                        abc.append('c');
                    } else if (curr == '3') {
                        abc.append('d');
                    } else if (curr == '4') {
                        abc.append('e');
                    } else if (curr == '5') {
                        abc.append('f');
                    } else if (curr == '6') {
                        abc.append('g');
                    } else if (curr == '7') {
                        abc.append('h');
                    } else if (curr == '8') {
                        abc.append('i');
                    } else if (curr == '9') {
                        abc.append('j');
                    }
                }
                System.out.print(abc.toString());
                System.out.print(" ");
                mas[i] += temp_el;
                i += 1;
            }
            i = 0;
            System.out.println();
            s = scanner.readLine();
            linescanner.close();
        }
        //scanner.close();
    }
}