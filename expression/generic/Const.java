package expression.generic;

public class Const<T> implements GenericExpr<T>{
    private final T value;

    public Const(T value) {
        this.value = value;
    }
    public T evaluate(T x, T y, T z) {
        return this.value;
    }
    public String toString() {
        return this.value.toString();
    }
    public int oper() {
        return 21;
    }
    public int hashCode() {
        return value.hashCode();
    }
    public boolean equals(Object a) {
        // if (a instanceof Const c) {... c }
        if (a == null || a.getClass() != expression.Const.class) {
            return false;
        }
        Const b = (Const) a;
        return value.equals(b.value);
    }


}
