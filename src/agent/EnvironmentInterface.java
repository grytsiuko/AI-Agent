package agent;

import java.util.List;

public interface EnvironmentInterface<M extends MoveInterface<?>> {

    void init();

    void doMove(M move);

    List<M> getPossibleMoves();

    boolean isFinish();
}
