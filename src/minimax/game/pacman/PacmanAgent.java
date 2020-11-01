package minimax.game.pacman;


import minimax.game.Agent;
import search.environment.pacman.Pacman;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PacmanAgent implements Agent<PacmanMove, PacmanState> {

    public enum Direction {Up, Down, Left, Right}

    private Board board;
    private int width;
    private int blockSize;
    private boolean movedSuccessfully;
    private final int agentId;

    public PacmanAgent(Board board, int agentId) {
        this.board = board;
        blockSize = board.getBlockSize();
        width = board.getBlocksAmount() * blockSize;
        this.agentId = agentId;
    }

    @Override
    public void doMove(PacmanMove move) {
        switch (move.getDirection()) {
            case Up:
                movedSuccessfully = board.moveUp(agentId);
                break;
            case Down:
                movedSuccessfully = board.moveDown(agentId);
                break;
            case Left:
                movedSuccessfully = board.moveLeft(agentId);
                break;
            case Right:
                movedSuccessfully = board.moveRight(agentId);
                break;
        }
    }


    @Override
    public List<PacmanMove> getPossibleMoves(PacmanState state) {
        int x;
        int y;

        if (agentId == -1) {
            x = state.getPacmanX();
            y = state.getPacmanY();
        } else {
            x = state.getGhostsX().get(agentId);
            y = state.getGhostsY().get(agentId);
        }

        return Arrays.stream(Direction.values())
                .filter(direction -> board.canMove(x, y, directionToIntX(direction), directionToIntY(direction)))
                .map(direction -> new PacmanMove(state,
                        generateTargetId(state, x, y, direction), direction,
                        1)).collect(Collectors.toList());
    }

    private PacmanState generateTargetId(PacmanState start, int x, int y, Direction direction) {
        if (agentId == -1) {
            return new PacmanState(
                    futureX(x, direction),
                    futureY(y, direction),
                    new ArrayList<>(start.getGhostsX()),
                    new ArrayList<>(start.getGhostsY())
            );
        } else {
            List<Integer> newGhostsX = new ArrayList<>(start.getGhostsX());
            List<Integer> newGhostsY = new ArrayList<>(start.getGhostsY());
            newGhostsX.set(agentId, futureX(newGhostsX.get(agentId), direction));
            newGhostsY.set(agentId, futureY(newGhostsY.get(agentId), direction));
            return new PacmanState(
                    start.getPacmanX(),
                    start.getPacmanY(),
                    newGhostsX,
                    newGhostsY
            );
        }
    }


    public static PacmanAgent.Direction reverseDirection(Direction direction) {
        Direction[] directions = Direction.values();
        int index = direction.ordinal();

        if (index % 2 == 0) {
            return directions[index + 1];
        } else {
            return directions[(index + 3) % 4];
        }
    }

    public int generateId(int x, int y) {
        return x + y * width;
    }

    private int futureX(int x, Direction direction) {
        switch (direction) {
            case Left:
                return x - blockSize;
            case Right:
                return x + blockSize;
            default:
                return x;
        }
    }

    private int futureY(int y, PacmanAgent.Direction direction) {
        switch (direction) {
            case Up:
                return y - blockSize;
            case Down:
                return y + blockSize;
            default:
                return y;
        }
    }

    private int directionToIntX(Direction direction) {
        switch (direction) {
            case Left:
                return -1;
            case Right:
                return 1;
            default:
                return 0;
        }
    }

    private int directionToIntY(Direction direction) {
        switch (direction) {
            case Up:
                return -1;
            case Down:
                return 1;
            default:
                return 0;
        }
    }

    private int getYById(int id) {
        return id / width;
    }

    private int getXById(int id) {
        return id % width;
    }

//    @Override
//    public Integer getState() {
//        return currentId;
//    }
//
//    @Override
//    public boolean isFinish(Integer state) {
//        return false;
//    }

}

