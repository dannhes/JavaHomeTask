package expression.generic;

public interface Types<T> {
    T add(T a, T b);
    T subtract(T a, T b);
    T multiply(T a, T b);
    T divide(T a, T b);
    T negate(T a);
    T count(T a);
    T min(T a, T b);
    T max(T a, T b);
    T cast(int a);
    T parse(String s);
}
