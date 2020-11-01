package minimax.game;

import java.util.List;

public interface Agent<M extends Move<M, S>, S> {

    List<M> getPossibleMoves(S state);

    void doMove(M move);

}
