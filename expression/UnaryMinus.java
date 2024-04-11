package expression;

import java.math.BigInteger;

public class UnaryMinus extends AbstractUnary{
    public UnaryMinus(Expres num1){
        super(num1,"-");

    }
    public String type(){
        return "";
    }
    private int operat=10;
    protected int toDo(int a){
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
