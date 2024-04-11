package expression;

import java.math.BigInteger;

public class Multiply extends absttact
{
    public Multiply(Expres num1,Expres num2){
        super(num1,num2,"*");
    }
    protected int toDo(int a,int b){
        return a * b;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.multiply(b);
    }
    private int operat = 13;
    public int oper(){
        return 13;
    }

    @Override
    public int countPer() {
        return 0;
    }

}
