package expression.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class BaseParser  {

    private static final char END = '\0';
    private CharSource source;
    private char ch = 0xffff;
    public int len = 0;
    protected void setCharSource(CharSource source){
        this.source = source;
        take();
    }

    public void SkpWhtSpc(){
        while (Character.isWhitespace(ch)){
            take();
        }
    }

    protected char  take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        len+=1;
        return result;
    }
    protected boolean takeString(String expression){
        char result = ch;
        for(int i = 0;i<expression.length();i++){
            if (eof() || expression.charAt(i)!=ch){
                source.moveBack(i);
                ch = result;
                return false;
            }
            take();
        }
        return true;
    }

    protected char previous(){
        final char result = ch;
        ch  = source.prev();
        len-=1;
        return result;
    }

    public char retChar(){
        return ch;
    }

    static Character[] operation  = {'+','-','/','*'};

    static Character[] brackets = {'(',')','[',']','{','}'};

    public Integer retLen(){
        return len;
    }
    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }


    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected boolean betweenVar(){
        return !(Character.isWhitespace(ch) || Arrays.asList(brackets).contains(ch) || Arrays.asList(operation).contains(ch));
    }
    protected boolean betweenConstTypes(){
        return ('0'<=ch && ch<= '9') || (ch==',') || ch=='.';
    }
}
