package Game;

public interface Board {
    Position getPosition();
    int getVal();
    boolean drawCheck();
    int getWinner();
    void print();
    Result makeMove(Move move);
}
