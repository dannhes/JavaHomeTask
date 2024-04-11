package expression.exceptions;

import expression.Expres;
import expression.absttact;

import java.math.BigInteger;

public class CheckedAdd extends absttact {
    public CheckedAdd(Expres num1, Expres num2){
        super(num1,num2,"+");

    }
    private int operat=10;
    protected int toDo(int a,int b){
        if ((a > 0 && b > 0 && a > Integer.MAX_VALUE - b)||(a < 0 && b < 0 && a < Integer.MIN_VALUE - b)){
            throw new CheckException("overflow");
        }

        return a+b;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.add(b);
    }
    public int oper(){
        return 11;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
