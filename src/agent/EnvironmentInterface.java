package agent;

import java.util.List;

public interface EnvironmentInterface<M extends MoveInterface<?>, I> {

    void doMove(M move);

    List<M> getPossibleMoves();

    boolean isFinish();

    I getId();
}
