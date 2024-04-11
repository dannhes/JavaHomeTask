package expression;

import java.util.*;
import expression.exceptions.ExpressionParser;

public class Main {
    public static void main(String[] args) throws Exception {
        ExpressionParser ss = new ExpressionParser();
        String str = "(a & b) | ~c";
        ss.parse(str,List.of("a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
        //System.out.println(ss.parse(str, List.of("a","b","c")));
        //System.out.println(ss.retLit());
        List<String> array = new ArrayList<>(ss.retLit());
        String expr = (ss.parse(str,array).toMiniString());
        System.out.println((array));
        int n = 10;
        StringBuilder answ = new StringBuilder();
        for (int i = 0;i<Math.pow(2,array.size());i++){
            //int mask = 1 << i;
            StringBuilder sb =new StringBuilder();
            String mask = Integer.toBinaryString(i);
            int size = array.size();
            int len = mask.length();
            while (len<size){
                sb.append("0");
                len+=1;
            }
            sb.append(mask);
            String parseStr = sb.toString();
            List<Integer> constans = new ArrayList<>();
            for (int j = 0;j<parseStr.length();j++){
                constans.add(Integer.parseInt(Character.toString(parseStr.charAt(j))));
            }
            //System.out.println(constans);
            //System.out.println(ss.parse(str,array).evaluate(constans));
            //System.err.println(constans);
            //System.err.println(ss.parse(str,array).evaluate(constans));
            if (ss.parse(str,array).evaluate(constans)==1){

                StringBuilder s= new StringBuilder();
                s.append("(");
                for(int k = 0;k<array.size();k++){
                    if (constans.get(k)==1){
                        s.append(array.get(k)).append("|");
                    }else {
                        s.append("!").append(array.get(k)).append("|");
                    }
                }
                String string = s.toString().substring(0,s.length()-1);
                StringBuilder sss = new StringBuilder();
                sss.append(string);
                sss.append(")").append("&");
                //System.out.println(sss);
                answ.append(sss);
            }
        }
        //System.out.println(answ);
        System.out.println(answ.toString().substring(0,answ.length()-1));

    }
}
