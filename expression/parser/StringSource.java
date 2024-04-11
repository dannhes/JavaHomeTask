package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    public Integer retPos(){
        return pos;
    }



    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    public void moveBack(int i){
        pos-=i;
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }
    public char prev(){
        return data.charAt(pos--);
    }

    public char printNext(){
        return data.charAt(pos+1);
    }


    public void SkipWhitesp(){
        if (Character.isWhitespace(data.charAt(pos))){
            pos++;
        }
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
