package expression;

import java.math.BigInteger;

public class UnaryL1 extends AbstractUnary{
    public UnaryL1(Expres num2){
        super(num2,"l1");
    }
    protected int toDo(int x){
        return Integer.numberOfLeadingZeros(~x);
    }
    protected BigInteger toDo(BigInteger x){
        int count = 0;
        int iterat = 1 << x.bitCount()+1;
        if (x.equals(new BigInteger(String.valueOf(-1)))){
            return BigInteger.valueOf(x.bitCount()+1);
        }
        while (x.or(BigInteger.valueOf(iterat)).equals(x)){
            iterat<<=1;
            count+=1;
        }
        return BigInteger.valueOf(count);
    }
    public int oper(){
        return 15;
    }

    @Override
    public int countPer() {
        return 0;
    }

}
