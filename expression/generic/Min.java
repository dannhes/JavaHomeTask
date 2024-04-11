package expression.generic;

public class Min<T> extends AbsGenBinary<T>{
    public Min(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"min");
    }
    public int oper(){
        return 1;
    }
    protected T toDo(T a,T b){
        return type.min(a,b);
    }
}
