package expression;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;

public class Variable implements Expres {
    public String name;
    public int var;
    public Variable(String name) {
        this.name = name;
    }
    public Variable(int numb){
        this.name="-";
        this.var=numb;
    }
    public Variable(String name,int numb){
        this.name = name;
        this.var = numb;
    }

    HashMap<String,Integer> words= new HashMap<>();
    int count = 0;
    public String check_operation() {
        return " ";
    }

    public int evaluate(int x) {
        return x;
    }

    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        return switch (name) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }
    public int evaluate(List<Integer> variables){

        return variables.get(var);
    }

    public int countPer(){
        return words.size();
    }
    public String toMiniString() {
        return name;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object a) {
        if (a == null || a.getClass() != Variable.class) {
            return false;
        }
        Variable b = (Variable) a;
        return name.equals(b.name);
    }

    private int operat = 15;

    public int oper() {
        return 21;
    }

}
