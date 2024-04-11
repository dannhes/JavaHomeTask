package expression;

import java.math.BigInteger;
import java.util.*;

public class Const implements Expres {
    private final Number x;

    public Const(int x) {
        this.x = x;

    }

    public Const(BigInteger x) {
        this.x = x;
    }

    public int evaluate(int x) {
        return (int) this.x;
    }
    public int evaluate(List<Integer> var){
        return (int) this.x;
    }

    //private int opert = 15;
    public BigInteger evaluate(BigInteger x) {
        return (BigInteger) this.x;
    }

    public int evaluate(int x, int y, int z) {
        return (int) this.x;
    }



    public String toMiniString() {
        return this.x.toString();
    }

    public String toString() {
        return this.x.toString();
    }

    public int hashCode() {
        return x.hashCode();
    }

    public boolean equals(Object a) {
        // if (a instanceof Const c) {... c }
        if (a == null || a.getClass() != Const.class) {
            return false;
        }
        Const b = (Const) a;
        return x.equals(b.x);
    }

    public String check_operation() {
        return " ";
    }

    public int oper() {
        return 21;
    }

    @Override
    public int countPer() {
        return 1;
    }

}
