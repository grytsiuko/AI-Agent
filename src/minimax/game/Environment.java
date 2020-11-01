package minimax.game;

import java.util.List;

public interface Environment<S, A extends Agent<M, S>, M extends Move<M, S>> {

    List<A> getPendingEnemies();

    S getState();

    boolean isFinish(S state);

}
