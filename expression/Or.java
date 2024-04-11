package expression;

import java.math.BigInteger;

public class Or extends absttact{
    public Or(Expres num1,Expres num2){
        super(num1,num2,"|");
    }
    protected int toDo(int a,int b){
        return a | b;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.or(b);
    }
    public int oper(){
        return 5;
    }

    @Override
    public int countPer() {
        return 0;
    }
}
