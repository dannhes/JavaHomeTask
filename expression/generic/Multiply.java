package expression.generic;

public class Multiply<T> extends AbsGenBinary<T> {
    public Multiply(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"*");
    }
    public int oper(){
        return 13;
    }
    protected T toDo(T a,T b){
        return type.multiply(a,b);
    }
}
