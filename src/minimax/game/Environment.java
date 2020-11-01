package minimax.game;

public interface Environment<S> {

    S getState();

    boolean isFinish(S state);

}
