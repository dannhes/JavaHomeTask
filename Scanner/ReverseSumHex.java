package Scanner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class ReverseSumHex {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int[] mas = new int[10];
        int i = 0;
        int temp_el = 0;
        while (scanner.hasNextLine()) {
            MyScanner linescanner = new MyScanner(scanner.nextLine());
            int prefsum = 0;
            while (linescanner.hasNextfirst()) {
                if (i == mas.length) {
                    mas = Arrays.copyOf(mas , i * 2);
                }
                temp_el = Integer.parseUnsignedInt(linescanner.next(),16);
                prefsum += mas[i] + temp_el;
                System.out.print(Integer.toHexString(prefsum));
                System.out.print(" ");
                mas[i] += temp_el;
                i += 1;
            }
            i = 0;
            System.out.println();
            linescanner.close();
        }
        scanner.close();
    }
}