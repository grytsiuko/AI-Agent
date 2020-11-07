package logicAgent.vampus;

import java.util.Random;

public class VampusGame {

    private final int SLEEP_DELAY = 300;

    private final int WALLS_AMOUNT = 3;
    private final int HOLES_AMOUNT = 3;
    private final int GOLD_AMOUNT = 1;
    private final int VAMPUS_AMOUNT = 1;

    private final int WIDTH = 5;
    private final int HEIGHT = 5;

    private final int START_AGENT_X = 0;
    private final int START_AGENT_Y = 0;

    private int agentX;
    private int agentY;

    private final VampusCharacter[][] board;

    private final VampusAgent vampusAgent;

    public VampusGame(VampusAgent vampusAgent) {
        this.vampusAgent = vampusAgent;
        this.board = new VampusCharacter[HEIGHT][WIDTH];
        generateBoard();
        this.agentX = START_AGENT_X;
        this.agentY = START_AGENT_Y;
    }

    public void start() {
        loop();
    }

    private void generateBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                this.board[i][k] = new VampusCharacter(VampusCharacter.VampusCharacterEnum.EMPTY);
            }
        }
        for (int i = 0; i < WALLS_AMOUNT; i++) {
            generateRandom(VampusCharacter.VampusCharacterEnum.WALL);
        }
        for (int i = 0; i < HOLES_AMOUNT; i++) {
            generateRandom(VampusCharacter.VampusCharacterEnum.HOLE);
        }
        for (int i = 0; i < GOLD_AMOUNT; i++) {
            generateRandom(VampusCharacter.VampusCharacterEnum.GOLD);
        }
        for (int i = 0; i < VAMPUS_AMOUNT; i++) {
            generateRandom(VampusCharacter.VampusCharacterEnum.VAMPUS);
        }
    }

    private void generateRandom(VampusCharacter.VampusCharacterEnum vampusCharacterEnum) {
        int x, y;
        do {
            x = new Random().nextInt(WIDTH);
            y = new Random().nextInt(HEIGHT);
        } while (board[y][x].getVampusCharacterEnum() != VampusCharacter.VampusCharacterEnum.EMPTY || x == START_AGENT_X && y == START_AGENT_Y);
        board[y][x] = new VampusCharacter(vampusCharacterEnum);
    }

    private VampusSensors generateSensors() {
        boolean wallLeft = isWall(agentX - 1, agentY);
        boolean wallRight = isWall(agentX + 1, agentY);
        boolean wallUp = isWall(agentX, agentY - 1);
        boolean wallDown = isWall(agentX, agentY + 1);
        return new VampusSensors(wallLeft, wallRight, wallUp, wallDown);
    }

    private boolean isWall(int x, int y) {
        return
                x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT ||
                        board[y][x].getVampusCharacterEnum() == VampusCharacter.VampusCharacterEnum.WALL;
    }

    private void loop() {
        showBoard();
        while (!isFinish()) {
            sleep();
            VampusSensors vampusSensors = generateSensors();
            VampusAgentMove move = this.vampusAgent.decideMove(vampusSensors);
            doMove(move);
            showBoard();
        }
    }

    private void doMove(VampusAgentMove move) {
        switch (move.getDirection()) {
            case UP:
                moveIntoDirection(0, -1);
                break;
            case DOWN:
                moveIntoDirection(0, 1);
                break;
            case LEFT:
                moveIntoDirection(-1, 0);
                break;
            case RIGHT:
                moveIntoDirection(1, 0);
                break;
        }
    }

    private void moveIntoDirection(int x, int y) {
        int newX = agentX + x;
        int newY = agentY + y;

        if (isWall(newX, newY)) {
            throw new RuntimeException();
        }

        agentX = newX;
        agentY = newY;
    }

    private void showBoard() {
        System.out.println("#########################################");
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("#");
            for (int k = 0; k < WIDTH; k++) {
                if (i == agentY && k == agentX) {
                    System.out.print("|" + board[i][k] + "|");
                } else {
                    System.out.print(" " + board[i][k] + " ");
                }
            }
            System.out.println("#");
        }
        System.out.println("#########################################");
        System.out.println("\n\n");
    }

    // TODO
    private boolean isFinish() {
        return false;
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
