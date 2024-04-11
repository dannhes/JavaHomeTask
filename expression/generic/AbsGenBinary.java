package expression.generic;
import java.util.Objects;
import java.util.List;

public abstract class AbsGenBinary<T> implements GenericExpr<T>{
    private final GenericExpr<T> num1;
    private final GenericExpr<T> num2;
    public final Types<T> type;

    private final String operation;

    public AbsGenBinary(GenericExpr<T> num1, GenericExpr<T> num2, Types<T> type,String operation) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.operation = operation;
    }

    abstract protected T toDo(T a, T b);



    public T evaluate(T x, T y, T z) {
        if(x==null || y == null || z == null){
            return null;
        }
        T a = num1.evaluate(x, y, z);
        T b = num2.evaluate(x, y, z);
        if (a==null || b == null){
            return null;
        }
        return toDo(a, b);
    }


    public int hashCode() {
        int a = num1.hashCode();
        int b = num2.hashCode();
        int c = operation.hashCode();
        int d = type.hashCode();
        return a * 17 + b * 19 + c * 10 + d * 7;
    }

    public String check_operation() {
        return operation;
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != this.getClass()) {
            return false;
        }
        AbsGenBinary<T> b = (AbsGenBinary<T>) a;
        return num1.equals(b.num1) && num2.equals(b.num2);
    }

    public String toString() {
        return "(" + num1 + " " + operation + " " + num2 + ")";
    }

    /*public String toMiniString() {
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
    }*/
}
