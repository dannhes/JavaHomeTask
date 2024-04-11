package expression.exceptions;

import expression.Expres;
import expression.absttact;

import java.math.BigInteger;

public class CheckedSubtract extends absttact {
    public CheckedSubtract(Expres num1, Expres num2){
        super(num1,num2,"-");

    }
    protected int toDo(int a,int b){
        if ((a >= 0 && b < 0 && a > Integer.MAX_VALUE + b) || (a < 0 && b > 0 && a < Integer.MIN_VALUE + b)){
            throw new CheckException("overflow");
        }
        return a - b;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
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
