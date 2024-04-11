package expression;

import java.math.BigInteger;

public class Divide extends absttact {
    public Divide(Expres num1,Expres num2){
        super(num1,num2,"/");

    }
    protected int toDo(int a,int b){
//        if (b==0){
//            throws error("Division by zero");
//        }
        return a/b;
    }

    protected BigInteger toDo(BigInteger a,BigInteger b){
        return a.divide(b);
    }

    private int operat = 14;
    public int oper(){
        return 14;
    }

    @Override
    public int countPer() {
        return 0;
    }

}
