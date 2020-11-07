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

    private final int START_AGENT_COL = 0;
    private final int START_AGENT_ROW = 0;

    private int agentCol;
    private int agentRow;

    private final VampusCharacter[][] board;

    private final VampusAgent vampusAgent;

    public VampusGame(VampusAgent vampusAgent) {
        this.vampusAgent = vampusAgent;
        this.board = new VampusCharacter[HEIGHT][WIDTH];
        generateBoard();
        this.agentCol = START_AGENT_COL;
        this.agentRow = START_AGENT_ROW;
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
        int col, row;
        do {
            col = new Random().nextInt(WIDTH);
            row = new Random().nextInt(HEIGHT);
        } while (board[row][col].getVampusCharacterEnum() != VampusCharacter.VampusCharacterEnum.EMPTY || col == START_AGENT_COL && row == START_AGENT_ROW);
        board[row][col] = new VampusCharacter(vampusCharacterEnum);
    }

    private VampusSensors generateSensors() {
        boolean wallLeft = isWall(agentCol - 1, agentRow);
        boolean wallRight = isWall(agentCol + 1, agentRow);
        boolean wallUp = isWall(agentCol, agentRow - 1);
        boolean wallDown = isWall(agentCol, agentRow + 1);
        return new VampusSensors(wallLeft, wallRight, wallUp, wallDown);
    }

    private boolean isWall(int col, int row) {
        return
                col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT ||
                        board[row][col].getVampusCharacterEnum() == VampusCharacter.VampusCharacterEnum.WALL;
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

    private void moveIntoDirection(int col, int row) {
        int newCol = agentCol + col;
        int newRow = agentRow + row;

        if (isWall(newCol, newRow)) {
            throw new RuntimeException();
        }

        agentCol = newCol;
        agentRow = newRow;
    }

    private void showBoard() {
        System.out.println("#########################################");
        for (int row = 0; row < HEIGHT; row++) {
            System.out.print("#");
            for (int col = 0; col < WIDTH; col++) {
                if (row == agentRow && col == agentCol) {
                    System.out.print("|" + board[row][col] + "|");
                } else {
                    System.out.print(" " + board[row][col] + " ");
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
