package expression.exceptions;

import expression.Expres;
import expression.absttact;

import java.math.BigInteger;

public class CheckedDivide extends absttact {
    public CheckedDivide(Expres num1, Expres num2){
        super(num1,num2,"/");

    }
    protected int toDo(int a,int b){
        if (b==0){
            throw new CheckException("division by zero");
        }
        if (a == Integer.MIN_VALUE && b == -1){
            throw new CheckException("overflow");
        }

        return a/b;
    }

    protected BigInteger toDo(BigInteger a, BigInteger b){
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
