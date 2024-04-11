package Scanner;

import java.io.*;
import java.util.*;

public class WsppSortedFirst {
    public static void main(String[] args) {
        HashSet<String> word = new HashSet<>();
        if (args.length!=2) {
            return;
        }
        TreeMap<String, Intlist> map = new TreeMap<>();
        int number = 0;

        try  {
            MyScanner inp = new MyScanner(args[0],"utf-8");
            while (inp.hasNextLine()) {
                while (inp.hasNextthird()) {
                    String sl = inp.next().toLowerCase();

                    number+=1;
                    Intlist list;
                    if (!map.containsKey(sl)) {
                        list = new Intlist();
                        map.put(sl, list);
                    }
                    list = map.get(sl);
                    /*if (list.len()==1 || list.get(list.len()-2)!=1) {
                        list.addtoL(number);
                        fl++;
                        list.addtoL(fl);
                    }*/
                    if (!word.contains(sl)){
                        list.addtoL(number);
                    }
                    word.add(sl);
                    list.countplus();
                    /*if (inp.change()) {
                        inp.change();
                        break;
                    }*/
                }
                //inp.change();
                word.clear();
            }

            inp.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"))) {
            for (String key : map.keySet()) {
                writer.write(key + " ");
                int[] mas = new int[map.get(key).len()];
                String s="";
                for (int i = 0; i < map.get(key).len();i+=1) {
                    mas[i] = map.get(key).get(i);
                    if (i == map.get(key).len()-1) {
                        s = (Integer.toString(map.get(key).get(i)));
                    }
                    else {
                        s = (Integer.toString(map.get(key).get(i))+" ");
                    }
                    writer.write(s.substring(0,s.length()));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
