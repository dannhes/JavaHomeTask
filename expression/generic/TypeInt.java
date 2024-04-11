package expression.generic;

public class TypeInt implements Types<Integer>{

    @Override
    public Integer add(Integer a, Integer b){
        if ((a > 0 && b > 0 && a > Integer.MAX_VALUE - b)||(a < 0 && b < 0 && a < Integer.MIN_VALUE - b)){
            return null;
        }
        return a+b;
    }

    public Integer subtract(Integer a,Integer b){
        if ((a >= 0 && b < 0 && a > Integer.MAX_VALUE + b) || (a < 0 && b > 0 && a < Integer.MIN_VALUE + b)){
            return null;
        }
        return a - b;
    }

    public Integer multiply(Integer a,Integer b){
        if (a>0 && b>0 && a>Integer.MAX_VALUE/b){
            return null;
        } else if(a<0 && b<0 && a<Integer.MAX_VALUE/b){
            return null;
        } else if(a>0 && b<0 && b < Integer.MIN_VALUE / a){

            return null;
        } else if(a < 0 && b > 0 && a < Integer.MIN_VALUE / b){
            return null;
        }
        return a * b;
    }


    public Integer divide(Integer a,Integer b){
        if (b==0){
            return null;
        }
        if (a == Integer.MIN_VALUE && b == -1){
            return null;
        }
        return a/b;
    }

    public Integer negate(Integer a){
        if (a==Integer.MIN_VALUE){
            return null;
        }
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

    public Integer cast(int a) {
        return  a;
    }

    public Integer parse(String s){
        return Integer.parseInt(s);
    }
}
