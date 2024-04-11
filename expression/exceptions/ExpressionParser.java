package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.StringSource;


import java.util.*;

public class ExpressionParser extends BaseParser implements TripleParser, ListParser {


    boolean flag = false;
    int countVar;
    int expressionLength;

    List<String> ourList;

    Set<String> l = new HashSet<>();


    public Expres parse(String expression) throws Exception {
        setCharSource(new StringSource(expression));
        // :NOTE: отдельная проверка -- плохо
        checkBrackets(expression);
        flag = false;
        System.err.println(expression);
        Expres a = parser();
        if (!eof()) {
            throw new CheckException("Cannot parse symbols");
        }
        return a;
    }

    public Expres parse(String expression, List<String> list) throws Exception {
        setCharSource(new StringSource(expression));
        flag = true;
        checkBrackets(expression);
        len = 0;
        expressionLength = expression.length();
        // :NOTE: не нужно
        countVar = -1;
        ourList = list;
        Expres a = parser();
        if (retLen() < expression.length()) {
            throw new CheckException("Cannot parse symbols");
        }
        return a;
    }

    public void checkBrackets(String expression) {
        int cOpen = 0;
        int cClose = 0;
        Character[] openBrackets = {'(', '[', '{'};
        Character[] closeBrackets = {')', '}', ']'};
        for (int i = 0; i < expression.length(); i++) {
            if (Arrays.asList(openBrackets).contains(expression.charAt(i))) {
                cOpen += 1;
            }
            if (Arrays.asList(closeBrackets).contains(expression.charAt(i))) {
                cClose += 1;
            }
        }
        if (cClose > cOpen) {
            throw new CheckException("closed brackets more than open");
        }
        if (cOpen > cClose) {
            throw new CheckException("open brackets more than closed");
        }

    }
    // parseBin() { result = parseBin() for (i in operations) result  = makeoperation(map[i],result,parseBinary)
    // result = func

    private Expres parser() {
        return parseMinMax();
    }

    private Expres parseMinMax() {
        Expres result = parseOr();
        SkpWhtSpc();
        while (true) {
            SkpWhtSpc();
            if (takeString("min")) {
                if (between('0', '9')) {
                    throw new CheckException("after min must be not a number");
                }
                result = new CheckedMin(result, parseOr());
            } else if (takeString("max")) {
                if (between('0', '9')) {
                    throw new CheckException("after min must be not a number");
                }
                result = new CheckedMax(result, parseOr());
            }
            return result;
            /*if (take('m')) {
                if (take('i')) {
                    expect('n');
                    if (between('0', '9')) {
                        throw new CheckException("after min must be not a number");
                    }
                    result = new CheckedMin(result, parseOr());
                } else if (take('a')) {
                    expect('x');
                    if (between('0', '9')) {
                        throw new CheckException("after min must be not a number");
                    }
                    result = new CheckedMax(result, parseOr());
                } else {
                    return result;
                }
            } else {
                return result;
            }*/
        }
    }

    public Expres parseDivideMulty() {
        Expres result = varAndSk();
        SkpWhtSpc();
        while (true) {
            /*SkpWhtSpc();

            makeExpression(Character.toString(take()), result, varAndSk());
            */
            if (take('/')) {
                SkpWhtSpc();
                result = new CheckedDivide(result, varAndSk());
            } else if (take('*')) {

                SkpWhtSpc();
                result = new CheckedMultiply(result, varAndSk());
            } else {
                return result;
            }
        }
    }

    private Expres makeExpression(String operation, Expres left, Expres right) {
        switch (operation) {
            case "+" -> {
                return new CheckedAdd(left, right);
            }
            case "-" -> {
                return new CheckedSubtract(left, right);
            }
            case "/" -> {
                return new CheckedDivide(left, right);
            }
            case "*" -> {
                return new CheckedMultiply(left, right);
            }
            case "min" -> {
                return new CheckedMin(left, right);
            }
            case "max" -> {
                return new CheckedMax(left, right);
            }
            case "&" -> {
                return new And(left, right);
            }
            case "|" -> {
                return new Or(left, right);
            }
            case "^" -> {
                return new Xor(left, right);
            }
            default -> {
                return left;
            }
        }
    }


    public Expres parseAddSubst() {
        Expres result = parseDivideMulty();
        SkpWhtSpc();
        while (true) {
            /*SkpWhtSpc();
            makeExpression(Character.toString(take()), result, parseDivideMulty());
            return result;*/
            if (take('+')) {
                SkpWhtSpc();
                result = new CheckedAdd(result, parseDivideMulty());
                //System.err.println(result);
            } else if (take('-')) {

                SkpWhtSpc();
                result = new CheckedSubtract(result, parseDivideMulty());
            } else {
                return result;
            }
        }
    }

    public Expres parseOr() {
        Expres result = parseXor();
        SkpWhtSpc();
        while (true) {
            /*SkpWhtSpc();
            makeExpression(Character.toString(take()), result, parseXor());
            return result;*/
            if (take('|')) {

                result = new Or(result, parseXor());
            } else {
                return result;
            }
        }
    }

    public Expres parseXor() {
        Expres result = parseBinOper();
        SkpWhtSpc();
        while (true) {
            /*SkpWhtSpc();
            makeExpression(Character.toString(take()), result, parseBinOper());
            return result;*/
            if (take('^')) {
                result = new Xor(result, parseBinOper());
            } else {
                return result;
            }
        }
    }

    public Expres parseBinOper() {
        Expres result = parseAddSubst();
        SkpWhtSpc();
        while (true) {
            /*SkpWhtSpc();
            makeExpression(Character.toString(take()), result, parseAddSubst());
            return result;*/
            if (take('&')) {
                result = new And(result, parseAddSubst());
            } else {
                return result;
            }
        }
    }

    protected Expres varAndSk() {
        SkpWhtSpc();
        if (flag) {
            if (take('(')) {
                Expres s = parser();
                if (!take(')')) {
                    throw new CheckException("Expected '" + ')' + "', found '" + take() + "'");
                }
                return s;
            } else if (take('[')) {
                Expres s = parser();
                expect(']');
                return s;
            } else if (take('{')) {
                Expres s = parser();
                expect('}');
                return s;
            } else if (between('0', '9')) {
                return Con(0);
            } else if (take('-')) {
                if (between('0', '9')) {
                    return Con(1);
                }
                SkpWhtSpc();
                return new CheckedNegate(varAndSk());
            } else if (take('l')) {
                expect('1');
                return new UnaryL1(varAndSk());
            } else if (take('t')) {
                expect('1');
                return new UnaryT1(varAndSk());
            }else if(take('~')){
                return new Not(varAndSk());
            }
            else if (take(')')) {
                throw new CheckException("Closing bracket before the opening bracket");
            } else if (betweenVar()) {
                return parseVar(1);
            }else {
                throw new CheckException("Not avaliable symbol");
            }
        } else {
            if (take('(')) {
                Expres s = parser();
                if (!take(')')) {
                    throw new CheckException("Expected '" + ')' + "', found '" + take() + "'");
                }
                return s;
            } else if (take('[')) {
                Expres s = parser();
                expect(']');
                return s;
            } else if (take('{')) {
                Expres s = parser();
                expect('}');
                return s;
            } else if (between('0', '9')) {
                return Con(0);
            } else if (between('x', 'z')) {
                return parseVar(0);
            } else if (take('-')) {
                if (between('0', '9')) {
                    return Con(1);
                }
                SkpWhtSpc();
                return new CheckedNegate(varAndSk());
            } else if (take('l')) {
                expect('1');
                return new UnaryL1(varAndSk());
            } else if (take('t')) {
                expect('1');
                return new UnaryT1(varAndSk());
            }else if(take('~')){
                return new Not(varAndSk());
            }
            else if (take(')')) {
                throw new CheckException("Closing bracket before the opening bracket");
            } else {
                throw new CheckException("Not avaliable symbol");
            }
        }
    }

    public Expres Con(int flag) {
        SkpWhtSpc();
        int curNumb;
        StringBuilder sb = new StringBuilder();
        while (between('0', '9')) {
            sb.append(take());
        }
        if (sb.isEmpty()) {
            throw new CheckException("Expected number");
        }
        if (sb.toString().equals("2147483648")) {
            if (flag == 1) {
                return new Const(Integer.MIN_VALUE);
            } else {
                throw new CheckException("Big number");
            }
        }
        curNumb = Integer.parseInt(String.valueOf(sb));
        if (flag == 1) {
            curNumb *= (-1);
        }
        return new Const(curNumb);

    }

    public Expres parseVar(int flag) {
        if (flag == 0) {
            SkpWhtSpc();
            char c = retChar();
            take();
            return new Variable(String.valueOf(c));
        } else {
            SkpWhtSpc();
            StringBuilder sb = new StringBuilder();
            /*while (betweenVar() && !eof()) {
                sb.append(take());
            }*/
            char c = retChar();
            take();
            sb.append(c);
            String variable = String.valueOf(sb);
            int index = ourList.indexOf(variable);
            if (index == -1) {
                throw new CheckException("Symbol is not from list");
            }
            l.add(variable);
            return new Variable(variable, index);
        }
    }

    public Set<String> retLit(){
        return l;
    }
}
