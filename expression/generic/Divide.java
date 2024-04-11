package expression.generic;

public class Divide<T> extends AbsGenBinary<T>{
    public Divide(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"/");
    }

    public int oper(){
        return 14;
    }
    protected T toDo(T a,T b){
        return type.divide(a,b);
    }
}
