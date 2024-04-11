package expression.generic;

import expression.generic.GenericParser;

@FunctionalInterface
public interface TripleParser<T> {
    GenericExpr<T> parse(String expression);
}