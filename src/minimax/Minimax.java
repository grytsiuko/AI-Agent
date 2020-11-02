package minimax;

import minimax.game.*;

import java.util.*;


public class Minimax<A extends Agent<M, S>, M extends Move<M, S>, S> {

    private final Environment<S, A, M> environment;
    private final A                    player;
    private final List<A>              enemies;
    private final int                  MAX_DEPTH;


    public Minimax(Environment<S, A, M> environment, A player, int maxDepth) {
        this.environment = environment;
        this.player = player;
        this.enemies = new ArrayList<>();
        this.MAX_DEPTH = maxDepth;
    }

    public void start() {
        S state = environment.getState();

        while (!environment.isFinish(state)) {
            tryToFetchPendingEnemies();
            state = environment.getState();
            //todo find better way to init move
            M       initMove  = player.getPossibleMoves(state).get(0).getReverseMove();
            List<M> bestMoves = alphaBeta(initMove, 0, Integer.MIN_VALUE, Integer.MAX_VALUE).getKey();

            Collections.reverse(bestMoves);
            //remove init move
            bestMoves.remove(0);

            M playerMove = bestMoves.remove(0);
            player.doMove(playerMove);

            for(int i = 0; i < enemies.size(); ++i){
                enemies.get(i).doMove(bestMoves.get(i));
            }

            environment.refresh();
        }

    }

    private void tryToFetchPendingEnemies() {
        List<A> pending = environment.getPendingEnemies();
        enemies.addAll(pending);
    }

    private Pair<List<M>, Integer> alphaBeta(M moveHere, int depth, int alpha, int beta) {

        int agentsAmount = enemies.size() + 1;
        int agentIndex   = depth % agentsAmount;

        if (depth >= MAX_DEPTH * agentsAmount) {
            ArrayList<M> res = new ArrayList<>();
            res.add(moveHere);
            return new Pair<>(res, environment.calculateHeuristic(moveHere.getTargetState()));
        }


        if (agentIndex == 0) {
            Pair<List<M>, Integer> result = new Pair<>(new ArrayList<>(), Integer.MIN_VALUE);
            for (M move : player.getPossibleMoves(moveHere.getTargetState())) {
                Pair<List<M>, Integer> steps = alphaBeta(move, depth + 1, alpha, beta);

                if (result.getValue() < steps.getValue()) {
                    steps.getKey().add(moveHere);
                    result = steps;
                }

                alpha = Integer.max(steps.getValue(), alpha);

                if (alpha >= beta) {
                    return result;
                }
            }
            return result;

        } else {
            Pair<List<M>, Integer> result = new Pair<>(new ArrayList<>(), Integer.MAX_VALUE);

            for (M move : enemies.get(agentIndex - 1).getPossibleMoves(moveHere.getTargetState())) {

                Pair<List<M>, Integer> steps = alphaBeta(move, depth + 1, alpha, beta);

                if (result.getValue() > steps.getValue()) {
                    steps.getKey().add(moveHere);
                    result = steps;
                }

//                if (depth % agentsAmount == 1) {
                beta = Integer.min(beta, result.getValue());
                if (beta <= alpha) {
                    return result;
                }
//                }
            }
            return result;
        }
    }
}
