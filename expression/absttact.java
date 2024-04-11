package expression;

import java.math.BigInteger;
import java.util.*;

public abstract class absttact implements Expres {
    private final Expres num1;
    private final Expres num2;
    private final String operation;

    public absttact(Expres num1, Expres num2, String operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
    }

    abstract protected int toDo(int num1, int num2);

    //abstract protected int oper();

    abstract protected BigInteger toDo(BigInteger num1, BigInteger num2);

    public int evaluate(int x) {
        return toDo(num1.evaluate(x), num2.evaluate(x));
    }

    public BigInteger evaluate(BigInteger x) {
        return toDo(num1.evaluate(x), num2.evaluate(x));
    }


    public int evaluate(int x, int y, int z) {
        int a = num1.evaluate(x, y, z);
        int b = num2.evaluate(x, y, z);
        return toDo(a, b);
    }
    public int evaluate(List<Integer> vars){
        int a = num1.evaluate(vars);
        int b = num2.evaluate(vars);
        return toDo(a,b);
    }


    public int hashCode() {
        int a = num1.hashCode();
        int b = num2.hashCode();
        int c = operation.hashCode();
        return a * 17 + b * 19 + c * 10;
    }

    public String check_operation() {
        return operation;
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != this.getClass()) {
            return false;
        }
        absttact b = (absttact) a;
        return num1.equals(b.num1) && num2.equals(b.num2);
    }

    public String toString() {
        return "(" + num1 + " " + operation + " " + num2 + ")";
    }

    public int count(){
        int n = 0;
        n += countPer();
        return n;
    }

    public String toMiniString() {
        String nl = num1.toMiniString();
        String nr = num2.toMiniString();
        int numl = num1.oper();
        int numr = num2.oper();
        int numc = this.oper();
        String opeationsRight = num2.check_operation();
        String opeationsCur = this.check_operation();
        String operationLeft = num1.check_operation();
        if (opeationsCur.equals("*") && opeationsRight.equals("/")) {
            nr = "(" + nr + ")";
        }
        if (opeationsCur.equals("min") && opeationsRight.equals("max")){
            nr = "(" + nr + ")";
        }
        if (opeationsCur.equals("max") && opeationsRight.equals("min")){
            nr = "(" + nr + ")";
        }
        if (numc % 2 != 0) {
            if (numc > numl) {
                nl = "(" + nl + ")";
            }
            else {
                nl = nl;
            }
            if (numc > numr) {
                nr = "(" + nr + ")";
            } else {
                nr = nr;
            }
        } else {
            if (numc - 1 > numl) {
                nl = "(" + nl + ")";
            } else {
                nl = nl;
            }
            if (numc > numr-1) {
                nr = "(" + nr + ")";
            }
            else {
                nr = nr;
            }
        }
        return nl + " " + operation + " " + nr;
    }


}
