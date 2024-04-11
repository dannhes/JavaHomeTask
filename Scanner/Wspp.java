package Scanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Wspp {
    public static void main(String[] args) {
        if (args.length!=2) {
            System.out.println("Usage: wspp INPUT OUTPUT");
            return;
        }
        LinkedHashMap<String, Intlist> map = new LinkedHashMap<>();
        int number = 0;
        try  {
            MyScanner inp = new MyScanner(args[0],"utf-8");
            while (inp.hasNextLine()) {
                while (inp.hasNextthird()) {
                    String sl = inp.next().toLowerCase();
                    number += 1;
                    Intlist list;
                    if (!map.containsKey(sl)) {
                        list = new Intlist();
                        map.put(sl, list);
                    }
                    list = map.get(sl);
                    list.addtoL(number);
                    list.countplus();
                    if (inp.change()) {
                        inp.change();
                        break;
                    }
                    /*if (System.lineSeparator().contains(sl)) {
                        break;
                    }*/
                }
                /*if (inp.nextLine().isEmpty()) {
                    break;
                }*/

            }
            inp.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"))) {
            for (var val: map.entrySet()) {
                writer.write(val.getKey() + " ");
                String s;
                for (int i = 0; i < val.getValue().len(); i += 1) {
                    if (i == val.getValue().len() - 1) {
                        s = (Integer.toString(val.getValue().get(i)));
                    } else {
                        s = (val.getValue().get(i) + " ");
                    }
                    writer.write(s);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}
