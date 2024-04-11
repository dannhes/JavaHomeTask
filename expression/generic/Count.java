package expression.generic;

public class Count<T> extends AbsGenUnary<T> {
    public Count(GenericExpr<T> a,Types<T> type){
        super(a,type,"count");
    }
    public int oper(){
        return 15;
    }
    protected T toDo(T a){
        return type.count(a);
    }
}
