package Laytner;

public class Main extends Laytner{
    public static void main(String[] args) throws Exception{
        //BufferedWriter writeNumber = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("numbers"),"UTF-8"));
        String arg = args[0];
        Laytner word = new Laytner();
        word.parse(arg);
    }
}
