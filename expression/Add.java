package expression;

import java.math.BigInteger;

public class Add  extends  absttact{
    public Add(Expres num1,Expres num2){
        super(num1,num2,"+");

    }
    private int operat=10;
    protected int toDo(int a,int b){
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
