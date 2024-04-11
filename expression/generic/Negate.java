package expression.generic;

public class Negate<T> extends AbsGenUnary<T>{
    public Negate(GenericExpr<T> a,Types<T> type){
        super(a,type,"-");
    }
    public int oper(){
        return 15;
    }
    protected T toDo(T a){
        return type.negate(a);
    }
}
