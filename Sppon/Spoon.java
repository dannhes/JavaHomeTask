package Sppon;

import java.util.*;
public class Spoon {
    public static void print(List<Integer> list, int index){
        System.out.print((char) (list.get(index) & 0xFF));
    }
    public static void addNew(List<Integer> list,int index){
        Scanner sc= new Scanner(System.in);
        int a  = sc.nextInt();
        list.set(a,index);
    }

    public static void subtractNumb(List<Integer> list, int index){
        list.set(index,list.get(index)-1);
    }


    public static void addNumb(List<Integer> list,int index){
        list.set(index,list.get(index)+1);
    }


    public static void main(String[] args){
        StringBuilder sb = new StringBuilder();
        String s = "11111111110010001011111110101111111111010111010101101101101100000110101100101001010010101111111001010001010111001010010110010100110111111111111111110010100100010101110010100000000000000000000010100000000000000000000000000010100101001010010001010";
        int k = 0;
        int begin = 0;
        List<Integer> ls = new ArrayList<>();
        for (int i = 0;i<s.length();i++){
            ls.add(0);
        }
        int index = 0;
        for (int i = 0;i<s.length();i++){
            sb.append((s.charAt(i)));
            switch (sb.toString()){
                case "1":
                    addNumb(ls,index);
                    sb.setLength(0);
                    break;
                case "000":
                    subtractNumb(ls,index);
                    sb.setLength(0);
                    break;
                case "010":
                    index+=1;
                    sb.setLength(0);
                    break;
                case "011":
                    index-=1;
                    sb.setLength(0);
                    break;
                case "0010110":
                    addNew(ls,index);
                    sb.setLength(0);
                    break;
                case "001010":
                    print(ls,index);
                    sb.setLength(0);
                    break;
                case "00100":
                    k = ls.get(index);
                    begin = i;
                    sb.setLength(0);
                    break;
                case "0011":
                    if (k>1){
                        i = begin;
                    }
                    k-=1;
                    sb.setLength(0);
                    break;
            }
        }
    }
}
