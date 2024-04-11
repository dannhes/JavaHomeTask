package expression.exceptions;

import expression.TripleExpression;
import java.util.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@FunctionalInterface
public interface TripleParser {
    TripleExpression parse(String expression) throws Exception;

    //List returnList();
}
