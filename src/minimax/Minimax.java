package minimax;

import minimax.game.Agent;
import minimax.game.Environment;
import minimax.game.Heuristic;
import minimax.game.Move;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Minimax<A extends Agent<M, S>, M extends Move<M, S>, S> {

    private final Environment<S> environment;
    private final A       player;
    private final List<A> enemies;
    private final Heuristic<S> heuristic;
    private final int DEPTH;


    public Minimax(Environment<S> environment, A player, List<A> enemies, Heuristic<S> heuristic, int depth){
        this.environment = environment;
        this.player = player;
        this.enemies = enemies;
        this.heuristic = heuristic;
        this.DEPTH = depth;
    }

    public void start(){
        S state = environment.getState();

        while (!environment.isFinish(state)){
            Optional<M> optionalMove = player.getPossibleMoves(state).stream().max(Comparator.comparingInt(m -> heuristic.evaluate(m.getTargetState())));
            optionalMove.ifPresent(player::doMove);
        }

    }
}
