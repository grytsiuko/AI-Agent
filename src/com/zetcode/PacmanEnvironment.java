package com.zetcode;

import environment.EnvironmentInterface;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PacmanEnvironment implements EnvironmentInterface<PacmanMove, Integer> {

    public enum Direction{Up, Down, Left, Right};

    private Board board;
    private int currentId;
    private int width;
    private int blockSize;
    private boolean movedSuccessfully;

    PacmanEnvironment(Board board){
        this.board = board;
        width = board.getBlocksAmount();
        currentId = generateId(board.getPacmanX(), board.getPacmanY());
        blockSize = board.getBlockSize();
    }

    @Override
    public void doMove(PacmanMove move) {
        switch (move.getDirection()){
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

        if(movedSuccessfully) {
            currentId = move.getTargetId();
        }
    }

    @Override
    public List<PacmanMove> getPossibleMoves() {
        return  Arrays
                .stream(Direction.values())
                .map(direction -> new PacmanMove(this.currentId,
                        generateId(futureX(board.getPacmanX(), direction),
                                   futureY(board.getPacmanY(), direction)),
                        direction, 1))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isFinish() {
        return board.isFinished();
    }

    @Override
    public boolean movedSuccessfully() {
        return movedSuccessfully;
    }

    @Override
    public Integer getId() {
        return currentId;
    }

    public static Direction reverseDirection(Direction direction){
        Direction[] directions = Direction.values();
        int index = direction.ordinal();

        if(index % 2 == 0){
            return directions[index + 1];
        }else {
            return directions[(index + 3) % 4];
        }
    }

    public int generateId(int x, int y){
        return x + y * width;
    }

    private int futureX(int x, Direction direction){
        switch (direction) {
            case Left:
                return x - blockSize;
            case Right:
                return x + blockSize;
            default:
                return x;
        }
    }

    private int futureY(int y, Direction direction){
        switch (direction) {
            case Up:
                return y - blockSize;
            case Down:
                return y + blockSize;
            default:
                return y;
        }
    }
}
