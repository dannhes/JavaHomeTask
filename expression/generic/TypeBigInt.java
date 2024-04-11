package expression.generic;


import java.math.BigInteger;

public class TypeBigInt implements Types<BigInteger> {
    public BigInteger add(BigInteger a,BigInteger b){
        return a.add(b);
    }
    public BigInteger subtract(BigInteger a,BigInteger b){
        return a.subtract(b);
    }
    public BigInteger multiply(BigInteger a,BigInteger b){
        return a.multiply(b);
    }
    public BigInteger divide(BigInteger a,BigInteger b){
        if (b.equals(BigInteger.ZERO)){
            return null;
        }
        return a.divide(b);
    }
    public BigInteger negate(BigInteger a){
        return a.negate();
    }
    public BigInteger min(BigInteger a,BigInteger b){
        return a.min(b);
    }
    public BigInteger max(BigInteger a,BigInteger b){
        return a.max(b);
    }
    public BigInteger count(BigInteger a){
        return new BigInteger(String.valueOf(a.bitCount()));
    }


    public BigInteger cast(int a){
        return BigInteger.valueOf(a);
    }

    public BigInteger parse(String s){
        return new BigInteger(s);
    }
}
