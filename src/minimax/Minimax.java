package minimax;

import minimax.game.Agent;
import minimax.game.Environment;
import minimax.game.Heuristic;
import minimax.game.Move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class Minimax<A extends Agent<M, S>, M extends Move<M, S>, S> {

    private final Environment<S, A, M> environment;
    private final A       player;
    private final List<A> enemies;
    private final Heuristic<S> heuristic;
    private final int DEPTH;


    public Minimax(Environment<S, A, M> environment, A player, Heuristic<S> heuristic, int depth){
        this.environment = environment;
        this.player = player;
        this.enemies = new ArrayList<>();
        this.heuristic = heuristic;
        this.DEPTH = depth;
    }

    public void start(){
        S state = environment.getState();

        while (!environment.isFinish(state)){
            tryToFetchPendingEnemies();
            state = environment.getState();
            // pacman
            Optional<M> optionalMove = player.getPossibleMoves(state).stream().max(Comparator.comparingInt(m -> heuristic.evaluate(m.getTargetState())));
            optionalMove.ifPresent(player::doMove);
            // ghosts
            for (A enemy:enemies) {
                Optional<M> enemyMove = enemy.getPossibleMoves(state).stream().max(Comparator.comparingInt(m -> heuristic.evaluate(m.getTargetState())));
                enemyMove.ifPresent(enemy::doMove);
            }
        }

    }

    private void tryToFetchPendingEnemies() {
        List<A> pending = environment.getPendingEnemies();
        enemies.addAll(pending);
    }
}
