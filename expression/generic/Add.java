package expression.generic;

public class Add<T> extends AbsGenBinary<T>{
    public Add(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"+");
    }
    public int oper(){
        return 11;
    }
    protected T toDo(T a,T b){
        return type.add(a,b);
    }
}
