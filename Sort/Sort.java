package Sort;

import java.util.Arrays;

public class Sort implements toSort {
    public String[] parse(String mode,String[] mas){
        return switch (mode) {
            case "sort--ignore-leading-blanks" -> parseIgnoreBlanks(mas);
            case "sort--dictionary-order" -> parseOrder(mas);
            case "sort--ignore-case" -> parseIgnoreCase(mas);
            case "sort--nonprinting" -> parseNonPrint(mas);
            case "sort--numeric-sort" -> parseNumeric(mas);
            case "sort--reverse" -> parseReverse(mas);
            default -> throw new IllegalArgumentException("No current option");
        };
    }
    private String[] parseIgnoreBlanks(String[] mas){
        for (int i = 0;i<mas.length;i++){
            int j = 0;
            while (!Character.isWhitespace(mas[i].charAt(j))){
                j++;
            }
            mas[i] = mas[i].substring(j);
        }
        return parseOrder(mas);
    }

    private String[] parseIgnoreCase(String[] mas){
        for (int i = 0;i<mas.length;i++){
            mas[i] = mas[i].toLowerCase();
        }
        System.out.println(Arrays.toString(mas));
        return parseOrder(mas);
    }

    private String[] parseNonPrint(String[] mas){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<mas.length;i++){
            String s = mas[i];
            for (int j = 0;j<s.length();j++){
                if(!Character.isISOControl(s.charAt(j))){
                    sb.append(s.charAt(i));
                }
            }
            mas[i] = sb.toString();
            sb.setLength(0);
        }
        return parseOrder(mas);
    }

    private String[] parseOrder(String[] mas){

        for(int i = 0;i<mas.length-1;i++){
            for (int j = 0;j<mas.length-1-i;j++){
                if (fistBigger(mas[j],mas[j+1])){
                    String s = mas[j];
                    mas[j] = mas[j+1];
                    mas[j+1] = s;
                }
            }
        }

        return mas;
    }

    private String[] parseNumeric(String[] mas){
        int[] a = new int[mas.length];
        for (int i =0;i<mas.length;i++){
            a[i] = Integer.parseInt(mas[i]);
        }
        for (int i = 0;i<a.length-1;i++){
            for (int j =0; j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        for(int i = 0;i<a.length;i++){
            mas[i] = String.valueOf(a[i]);
        }
        return mas;
    }

    private String[] parseReverse(String[] mas){
        String[] answ = new String[mas.length];
        parseOrder(mas);
        for (int i=mas.length-1;i>=0;i--){
            answ[mas.length-i-1] = mas[i];
        }
        return answ;

    }

    private boolean fistBigger(String first,String second){
        for (int i = 0;i<Math.min(first.length(),second.length());i++){
            int asciFirts = first.charAt(i);
            int asciSec = second.charAt(i);
            if (asciFirts>asciSec){
                return true;
            } else if (asciFirts<asciSec) {
                return false;
            }
        }
        return first.length()>=second.length();
    }


}
