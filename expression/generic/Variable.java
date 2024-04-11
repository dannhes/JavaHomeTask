package expression.generic;

import expression.exceptions.CheckException;

public class Variable<T> implements GenericExpr<T> {
    public String name;
    public Variable(String name) {
        this.name = name;
    }
    public T evaluate(T x, T y, T z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new CheckException("Cannot parse" + name);
        };
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != expression.Variable.class) {
            return false;
        }
        expression.Variable b = (expression.Variable) a;
        return name.equals(b.name);
    }

    private int operat = 15;

    public int oper() {
        return 21;
    }
}
