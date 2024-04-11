package GameMat;

import GameMat.Cell;
import GameMat.Move;
import GameMat.Position;
import GameMat.Result;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
}
