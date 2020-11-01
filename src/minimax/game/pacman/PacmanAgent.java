package minimax.game.pacman;


import minimax.game.Agent;
import minimax.game.Environment;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PacmanAgent implements Agent<PacmanMove, Integer> {

    public enum Direction {Up, Down, Left, Right}

    private Board   board;
    private int     currentId;
    private int     width;
    private int     blockSize;
    private boolean movedSuccessfully;

    public PacmanAgent(Board board) {
        this.board = board;
        blockSize = board.getBlockSize();
        width = board.getBlocksAmount() * blockSize;
        currentId = generateId(board.getPacmanX(), board.getPacmanY());
    }

    @Override
    public void doMove(PacmanMove move) {
        switch (move.getDirection()) {
            case Up:
                movedSuccessfully = board.moveUp();
                break;
            case Down:
                movedSuccessfully = board.moveDown();
                break;
            case Left:
                movedSuccessfully = board.moveLeft();
                break;
            case Right:
                movedSuccessfully = board.moveRight();
                break;
        }

        if (movedSuccessfully) {
            currentId = move.getTargetState();
        }
    }


    @Override
    public List<PacmanMove> getPossibleMoves(Integer id) {
        int x = getXById(id);
        int y = getYById(id);

        return Arrays.stream(Direction.values())
                .filter(direction -> board.canMove(x, y, directionToIntX(direction), directionToIntY(direction)))
                .map(direction -> new PacmanMove(this.currentId,
                        generateId(futureX(x, direction), futureY(y, direction)), board.currentHeuristic(x, y),
                        board.targetHeuristic(x, y, directionToIntX(direction), directionToIntY(direction)), direction,
                        1)).collect(Collectors.toList());
    }


    public static PacmanAgent.Direction reverseDirection(Direction direction) {
        Direction[] directions = Direction.values();
        int         index      = direction.ordinal();

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

