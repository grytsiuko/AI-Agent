package minimax.game;

public interface Heuristic<S> {

    Integer evaluate(S state);

}
