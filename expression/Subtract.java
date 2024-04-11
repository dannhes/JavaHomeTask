package expression;

import java.math.BigInteger;

public class Subtract extends absttact {
    public Subtract(Expres num1,Expres num2){
        super(num1,num2,"-");

    }
    protected int toDo(int a,int b){
        return a - b;
    }
    protected BigInteger toDo(BigInteger a,BigInteger b){
        return a.subtract(b);
    }
    private int operat = 12;
    public int oper(){
        return 12;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
