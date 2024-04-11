package expression.generic;

public interface GenericExpr<T> {
    T evaluate(T x, T y, T z);
}