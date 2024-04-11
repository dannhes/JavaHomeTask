package expression.generic;
import java.util.Arrays;
import java.util.Map;

import expression.*;
import expression.exceptions.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;

public class GenericParser<T> extends BaseParser implements TripleParser {

    private final Types<T> mode;

    public GenericParser(Types<T> mode) {
        this.mode = mode;
    }

    public GenericExpr<T> parse(String expression) {
        setCharSource(new StringSource(expression));
        /*if (!eof()) {
            throw new CheckException("Cannot parse symbols");
        }*/
        return parser();
    }

    public GenericExpr<T> parser(){
        return parseMinMax();
    }

    private GenericExpr<T> parseMinMax(){
        GenericExpr<T> result = parseAddSubst();
        SkpWhtSpc();
        while (true) {
            SkpWhtSpc();
            if (takeString("min")) {
                if (between('0', '9')) {
                    throw new CheckException("after min must be not a number");
                }
                result = new Min<>(result, parseAddSubst(),mode);
            } else if (takeString("max")) {
                if (between('0', '9')) {
                    throw new CheckException("after min must be not a number");
                }
                result = new Max<>(result, parseAddSubst(),mode);
            }
            return result;
        }
    }

    private GenericExpr<T> parseAddSubst(){
        GenericExpr<T> result = parseDivideMulty();
        SkpWhtSpc();
        while (true) {
            SkpWhtSpc();
            if (take('+')) {
                SkpWhtSpc();
                result = new Add<>(result, parseDivideMulty(),mode);
                //System.err.println(result);
            } else if (take('-')) {

                SkpWhtSpc();
                result = new Subtract<>(result, parseDivideMulty(),mode);
            } else {
                return result;
            }
        }
    }

    private GenericExpr<T> parseDivideMulty(){
        GenericExpr<T> result = parseVarAndSc();
        SkpWhtSpc();
        while (true) {
            SkpWhtSpc();
            if (take('/')) {
                SkpWhtSpc();
                result = new Divide<>(result, parseVarAndSc(),mode);
            } else if (take('*')) {

                SkpWhtSpc();
                result = new Multiply<>(result, parseVarAndSc(),mode);
            } else {
                return result;
            }
        }
    }

    private GenericExpr<T> parseVarAndSc(){
        SkpWhtSpc();
        if (take('(')) {
            GenericExpr<T> s = parser();
            if (!take(')')) {
                throw new CheckException("Expected '" + ')' + "', found '" + take() + "'");
            }
            return s;
        } else if (between('0', '9')) {
            return parseCon(0);
        } else if (take('-')) {
            if (between('0', '9')) {
                return parseCon(1);
            }
            SkpWhtSpc();
            return new Negate<>(parseVarAndSc(),mode);
        }else if (takeString("count")){
            return new Count<>(parseVarAndSc(),mode);
        }
        else if (take(')')) {
            throw new CheckException("Closing bracket before the opening bracket");
        } else if (betweenVar()) {
            return parseVar();
        } else {
            throw new CheckException("Not avaliable symbol");
        }
    }

    private GenericExpr<T> parseVar(){
        SkpWhtSpc();
        char c = retChar();
        take();
        return new Variable<>(String.valueOf(c));
    }

    private GenericExpr<T> parseCon(int flag){
        SkpWhtSpc();
        StringBuilder sb = new StringBuilder();
        if (flag==1){
            sb.append('-');
        }
        while (betweenConstTypes()){
            sb.append(take());
        }
        return new Const<>(mode.parse(sb.toString()));
    }


}
