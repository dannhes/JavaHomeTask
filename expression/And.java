package expression;

import java.math.BigInteger;

public class And extends absttact{
    public And(Expres num1,Expres num2){
        super(num1,num2,"&");
    }
    protected int toDo(int a,int b){
        return a & b;
    }
    protected BigInteger toDo(BigInteger a, BigInteger b){
        return a.and(b);
    }
    public int oper(){
        return 9;
    }

    @Override
    public int countPer() {
        return 0;
    }


}
