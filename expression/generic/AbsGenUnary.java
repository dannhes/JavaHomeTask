package expression.generic;

import expression.AbstractUnary;

public abstract class AbsGenUnary<T> implements GenericExpr<T>{
    private final GenericExpr<T> num1;
    public final Types<T> type;

    private final String operation;
    public AbsGenUnary(GenericExpr<T> num1, Types<T> type,String operation) {
        this.num1 = num1;
        this.type = type;
        this.operation = operation;
    }
    abstract protected T toDo(T a);
    public T evaluate(T x, T y, T z) {
        if(x==null || y == null || z == null){
            return null;
        }
        T a = num1.evaluate(x, y, z);
        if (a==null){
            return null;
        }
        return toDo(a);
    }
    public int hashCode() {
        int a = num1.hashCode();
        int c = operation.hashCode();
        int b = type.hashCode();
        return a * 17 + c * 10 + 7 * b;
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != this.getClass()) {
            return false;
        }
        AbsGenUnary<T> b = (AbsGenUnary<T>) a;
        return num1.equals(b.num1);
    }

    public String toString() {
        return operation + "(" + num1.toString() + ")";
    }
}
