package expression.exceptions;

import expression.Expres;
import expression.absttact;

import java.math.BigInteger;

public class CheckedMin extends absttact {
    public CheckedMin(Expres num1, Expres num2){
        super(num1,num2,"min");

    }
    private int operat=10;
    protected int toDo(int a,int b){
        if (a>=b){
            return b;
        }
        return a;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.min(b);
    }
    public int oper(){
        return 1;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
