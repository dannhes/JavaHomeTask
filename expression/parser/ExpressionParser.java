package expression.parser;

import expression.*;
import expression.exceptions.CheckException;
import markup.Paragraph;

import java.util.ArrayList;
import java.util.List;

public class ExpressionParser extends BaseParser implements TripleParser {
    public boolean isNumb(char symbol){
        if (symbol<='9' && symbol>='0'){
            return true;
        }
        return false;
    }
    public Expres parse(String expression) {
        setCharSource(new StringSource(expression));
        return parser();
    }
    public Expres parse(String expression, List<String> list) {
        setCharSource(new StringSource(expression));
        Expres a = parser();
        return a;

    }
    Expres parser(){
        return Or();
    }

    public Expres DivideMulty(){
        Expres result = VarAndSk();
        SkpWhtSpc();
        while (true){
            SkpWhtSpc();
            if (take('/')){
                SkpWhtSpc();
                result =  new Divide(result, VarAndSk());
            } else if (take('*')){
                SkpWhtSpc();
                result =  new Multiply(result,VarAndSk());

            }
            else {
                return result;
            }
        }
    }
    public Expres AddSubst(){
        Expres result = DivideMulty();
        SkpWhtSpc();
        while (true){
            SkpWhtSpc();
            if (take('+')){
                SkpWhtSpc();
                result =  new Add(result,DivideMulty());
                //System.err.println(result);
            } else if(take('-')){
                SkpWhtSpc();
                result = new Subtract(result,DivideMulty());
            }
            else{
                return result;
            }
        }
    }

    public Expres Or(){
        Expres result = Xor();
        SkpWhtSpc();
        while (true){
            SkpWhtSpc();
            if (take('|')) {
                result = new Or(result, Xor());
            }
            else{
                return result;
            }
        }
    }
    public Expres Xor(){
        Expres result =  BinOper();
        SkpWhtSpc();
        while (true){
            SkpWhtSpc();
            if (take('^')) {
                result = new Xor(result, BinOper());
            }
            else{
                return result;
            }
        }
    }
    public Expres BinOper(){
        Expres result = AddSubst();
        SkpWhtSpc();
        while (true){
            SkpWhtSpc();
            if (take('&')) {
                result = new And(result, AddSubst());
            }
            else{
                return result;
            }
        }
    }
    public Expres VarAndSk(){
        SkpWhtSpc();
        if(take('(')){
            Expres s = parser();
            expect(')');
            return s;
        } else if(take('[')){
            Expres s = parser();
            expect(']');
            return s;
        } else if(take('{')){
            Expres s = parser();
            expect('}');
            return s;
        }
        else if(between('0','9')){
            return Con(0);
        } else if(between('a','z')){
            return Var();
        } else if (take('-')){
            if (between('0','9')){
                return Con(1);
            }
            SkpWhtSpc();
            return new UnaryMinus(VarAndSk());
        } else if (take('l')){
            expect('1');
            return new UnaryL1(VarAndSk());
        } else if(take('t')){
            expect('1');
            return new UnaryT1(VarAndSk());
        }
        else {
            throw error("Not avaliable symbol");
        }
    }
    public Expres Con(int flag){
        SkpWhtSpc();
        int curNumb = 0;
        ArrayList<Integer> digits = new ArrayList<>();
        while (between('0','9')){
            digits.add(Integer.parseInt(String.valueOf(take())));
        }
        for (int i = 0;i<digits.size();i++){
            curNumb+= (int) (digits.get(i)*Math.pow(10,digits.size() - i-1));
        }
        if (flag==1){
            curNumb*=(-1);
        }
        return new Const(Integer.parseInt(String.valueOf(curNumb)));
    }
    public Expres Var(){
        SkpWhtSpc();
        char c = retChar();
        take();
        return new Variable(String.valueOf(c));
    }
}
