package GameReversy;

import GameReversy.Cell;
import GameReversy.Move;
import GameReversy.Position;
import GameReversy.Result;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
}
