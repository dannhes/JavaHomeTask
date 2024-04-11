package expression.generic;


public class TypeDouble implements  Types<Double>{
    public Double add(Double a, Double b){
        return a+b;
    }

    public Double subtract(Double a,Double b){
        return a-b;
    }

    public Double multiply(Double a,Double b){
        return a * b;
    }

    public Double divide(Double a,Double b){
        return a/b;
    }

    public Double negate(Double a){
        return -a;
    }

    public Double count(Double a) {
        return (double) Long.bitCount(Double.doubleToLongBits(a));
    }

    public Double min(Double a, Double b){
        return Double.min(a,b);
    }

    public Double max(Double a,Double b){
        return Double.max(a,b);
    }

    public Double cast(int a) {
        return (double) a;
    }

    public Double parse(String s){
        return Double.parseDouble(s);
    }


}
