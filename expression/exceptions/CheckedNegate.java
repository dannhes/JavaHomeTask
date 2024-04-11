package expression.exceptions;

import expression.AbstractUnary;
import expression.Expres;
import expression.absttact;
import java.math.BigInteger;

public class CheckedNegate extends AbstractUnary {
    public CheckedNegate(Expres num1){
        super(num1,"-");
    }
    public String type(){
        return "";
    }
    private int operat=10;
    protected int toDo(int a){
        if (a==Integer.MIN_VALUE){
            throw new CheckException("overflow");
        }
        return a*(-1);
    }
    protected BigInteger toDo(BigInteger a){
        return a.multiply(BigInteger.valueOf(-1));
    }
    public int oper(){
        return 15;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
