package expression.exceptions;

import expression.Expres;
import expression.absttact;
import java.math.BigInteger;

public class CheckedMultiply extends absttact {
    public CheckedMultiply(Expres num1, Expres num2){
        super(num1,num2,"*");
    }
    protected int toDo(int a,int b){
        if (a>0 && b>0 && a>Integer.MAX_VALUE/b){
            throw new CheckException("overflow");
        } else if(a<0 && b<0 && a<Integer.MAX_VALUE/b){
            throw new CheckException("overflow");
        } else if(a>0 && b<0 && b < Integer.MIN_VALUE / a){
            throw new CheckException("overflow");
        } else if(a < 0 && b > 0 && a < Integer.MIN_VALUE / b){
            throw new CheckException("overflow");
        }
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
