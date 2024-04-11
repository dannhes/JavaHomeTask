package Scanner;

import java.io.*;
import java.util.*;
public class WordStatInput {
    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        String input = args[0];
        String output = args[1];
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        try {
            //MyScanner r = new MyScanner(input,"UTF-8");
            String line = reader.readLine();
            try {
                while (line!=null) {
                    MyScanner r = new MyScanner(line);
                    while (r.hasNextsecond()){
                        String str = r.next();
                        String word = str.toLowerCase();
                        String middle = word.substring(0, word.length());
                        map.put(middle, map.getOrDefault(middle, 0) + 1);

                    }
                    line = reader.readLine();
                        /*char ch = reader.nextLine().charAt(i);
                        if (Character.isLetter(ch) || ch == '\'' || Character.getType(ch) == Character.DASH_PUNCTUATION) {
                            wordBuffer.append(ch);
                        } else {
                            if (wordBuffer.length() >= 5) {
                                String word = wordBuffer.toString().toLowerCase();
                                String middle = word.substring(2, word.length() - 2);
                                map.put(middle, map.getOrDefault(middle, 0) + 1);
                            }
                            wordBuffer.setLength(0);
                        }*/

                }
            } catch (IOException e ){
                System.err.println(e.getMessage());
            }


            try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));) {
                for (String key : map.keySet()) {
                    writer.write(key + " "+map.get(key));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            reader.close();
        }
    }
}