package expression.generic;

public class Max<T> extends AbsGenBinary<T> {
    public Max(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"max");
    }
    public int oper(){
        return 1;
    }
    protected T toDo(T a,T b){
        return type.max(a,b);
    }
}
