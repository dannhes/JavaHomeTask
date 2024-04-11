package expression.generic;


public class TypeIntWithExc implements Types<Integer> {
    public Integer add(Integer a, Integer b){
        return a+b;
    }

    public Integer subtract(Integer a,Integer b){
        return a - b;
    }

    public Integer multiply(Integer a,Integer b){
        return a * b;
    }


    public Integer divide(Integer a,Integer b){
        if (b==0){
            return null;
        }
        return a/b;
    }

    public Integer negate(Integer a){
        return a*(-1);
    }

    public Integer count(Integer a){
        return Integer.bitCount(a);
    }

    public Integer min(Integer a, Integer b){
        if (a>=b){
            return b;
        }
        return a;
    }

    public Integer max(Integer a,Integer b){
        if (a>=b){
            return a;
        }
        return b;
    }

    public Integer cast(int a){
        return a;
    }

    public Integer parse(String s){
        return Integer.parseInt(s);
    }
}
