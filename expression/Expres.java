package expression;

public interface Expres extends Expression,TripleExpression,BigIntegerExpression,ListExpression{
    int oper();
    int countPer();
    String check_operation();

}
