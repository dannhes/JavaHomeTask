package expression.generic;


public class TypeByte implements Types<Byte>{
    public Byte add(Byte a,Byte b){
        return ((byte) (a+b));
    }

    public Byte subtract(Byte a,Byte b){
        return (byte) (a-b);
    }

    public Byte multiply(Byte a,Byte b){
        return (byte) (a*b);
    }

    public Byte divide(Byte a,Byte b){
        if (b==0){
            return null;
        }
        return (byte) (a/b);
    }

    public Byte negate(Byte a){
        return (byte) (-a);
    }

    public Byte min(Byte a,Byte b){
        if (a>=b){
            return (byte) b;
        }
        else {
            return (byte) a;
        }
    }

    public Byte max(Byte a,Byte b){
        if (a>=b){
            return (byte) a;
        }
        else {
            return (byte) b;
        }
    }

    public Byte count(Byte a){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if ((a & (1 << i)) != 0) {
                count++;
            }
        }
        return (byte) count;
    }

    public Byte cast(int a){
        return (byte) a;
    }

    public Byte parse(String s){
        return Byte.parseByte(s);
    }

}
