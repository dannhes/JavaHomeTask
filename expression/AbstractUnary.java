package expression;

import java.math.BigInteger;
import java.util.*;

public abstract class AbstractUnary implements Expres {
    private final Expres num1;
    private final String operation;

    public AbstractUnary(Expres num1, String operation) {
        this.num1 = num1;
        this.operation = operation;
    }

    abstract protected int toDo(int num1);

    //abstract protected int oper();

    abstract protected BigInteger toDo(BigInteger num1);
    StringBuilder sb = new StringBuilder();

    public int evaluate(int x) {
        return toDo(num1.evaluate(x));
    }

    public BigInteger evaluate(BigInteger x) {
        return toDo(num1.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        int a = num1.evaluate(x, y, z);
        return toDo(a);
    }
    public int evaluate(List<Integer> variables){
        int a = num1.evaluate(variables);
        return toDo(a);
    }

    public int hashCode() {
        int a = num1.hashCode();
        int c = operation.hashCode();
        return a * 17 + c * 10;
    }

    public String check_operation() {
        return operation;
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != this.getClass()) {
            return false;
        }
        AbstractUnary b = (AbstractUnary) a;
        return num1.equals(b.num1);
    }

    public String toString() {
        return operation + "(" + num1.toString() + ")";
    }

    public String toMiniString() {
        String nl = num1.toMiniString();
        int numl = num1.oper();
        int numc = this.oper();

        String operationsCur = this.check_operation();
        if (numc > numl){
            return sb.append(operationsCur).append("(").append(nl).append(")").toString();
        }
        sb.append(operationsCur).append(" ").append(nl);
        return sb.toString();
    }
}
