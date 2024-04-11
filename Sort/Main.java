package Sort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        String s = args[0];
        String[] parseVars = new String[args.length-1];
        System.arraycopy(args, 1, parseVars, 0, args.length - 1);
        Sort p = new Sort();
        System.out.println(Arrays.toString(p.parse(s, parseVars)));
    }
}
