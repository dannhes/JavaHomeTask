package Laytner;
import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Laytner {

    public void parse(String input) throws Exception{
        List<Triple<String, String, Integer>> words = new ArrayList<>();
        //BufferedWriter writeNumber = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("numbers"),"UTF-8"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        String line = reader.readLine();
        List<String> inFile = new ArrayList<>();
        int numb;
        int countWords = 0;
        String first;
        String secons;
        while (line!=null){
            if (getWords(line).size()==3){
                first = getWords(line).get(0);
                secons = getWords(line).get(1);
                numb = Integer.parseInt(getWords(line).get(2));
            }else {
                System.out.println(getWords(line));
                numb=0;
                first = getWords(line).get(0);
                secons = getWords(line).get(1);
            }
            words.add(new Triple<>(first, secons, numb));
            countWords++;
            line = reader.readLine();
        }
        boolean fl = true;
        Scanner s = new Scanner(System.in);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(input), "UTF-8"));
        while (fl){
            for(int j =0;j<=9;j++){
                for(int i = 0;i<countWords;i++){
                    if (words.get(i).getThird()==j){
                        System.out.println("How is translate word: " + words.get(i).getFirst());
                        String translate= s.next();
                        if (translate.equals("exit")){
                            for(int k = 0;k<words.size();k++){
                                writer.write(words.get(k).getFirst() + " "+ words.get(k).getSecond() + " " + String.valueOf(words.get(k).getThird()));
                                writer.newLine();
                            }
                            writer.close();
                            System.exit(0);
                        }
                        if(translate.equals(words.get(i).getSecond())){
                            if (words.get(i).getThird()!=9){
                                words.get(i).replaceTheard(words.get(i).getThird()+1);
                            }
                        }else {
                            words.get(i).replaceTheard(0);
                        }
                    }
                }
            }
        }


    }
    private List<String> getWords(String s){
        StringBuilder sb = new StringBuilder();
        List<String> mas = new ArrayList<>();

        for(int i = 0;i<s.length();i++){
            if (Character.isWhitespace(s.charAt(i))){
                mas.add(sb.toString());
                sb.setLength(0);
            }else {
                sb.append(s.charAt(i));
            }
        }
        mas.add(sb.toString());
        return mas;
    }


}
