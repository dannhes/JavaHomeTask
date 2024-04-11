package expression.generic;

public class Subtract<T> extends AbsGenBinary<T> {
    public Subtract(GenericExpr<T> a,GenericExpr<T> b,Types<T> type){
        super(a,b,type,"-");
    }
    public int oper(){
        return 12;
    }
    protected T toDo(T a,T b){
        return type.subtract(a,b);
    }
}
