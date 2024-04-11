package expression.parser;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    char prev();
    char printNext();
    void moveBack(int i);
    IllegalArgumentException error(String message);
}
