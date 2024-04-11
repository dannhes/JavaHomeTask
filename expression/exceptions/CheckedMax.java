package expression.exceptions;

import expression.Expres;
import expression.absttact;

import java.math.BigInteger;

public class CheckedMax extends absttact {
    public CheckedMax(Expres num1, Expres num2){
        super(num1,num2,"max");

    }
    private int operat=10;
    protected int toDo(int a,int b){

        if (a<=b){
            return b;
        }
        return a;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.max(b);
    }
    public int oper(){
        return 1;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
