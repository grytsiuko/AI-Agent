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

    private int agentCol = START_AGENT_COL;
    private int agentRow = START_AGENT_ROW;

    private boolean wasBump = false;
    private boolean wasScream = false;

    private int killedVampuses = 0;
    private int grabbedGold = 0;
    private int errors = 0;

    private boolean gameOver = false;
    private boolean agentEnded = false;

    private final VampusCharacter[][] board;

    private final VampusAgent vampusAgent;

    public VampusGame(VampusAgent vampusAgent) {
        this.vampusAgent = vampusAgent;
        this.board = new VampusCharacter[HEIGHT][WIDTH];
        generateBoard();
    }

    public void start() {
        loop();
        System.out.println("\n");
        if (gameOver) {
            System.out.println("GAME OVER");
        }
        if (agentEnded) {
            // TODO
            System.out.println("Agent ended game");
        }
    }

    private void generateBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                this.board[i][k] = new VampusCharacter(VampusCharacter.Type.EMPTY);
            }
        }
        for (int i = 0; i < WALLS_AMOUNT; i++) {
            generateRandom(VampusCharacter.Type.WALL);
        }
        for (int i = 0; i < HOLES_AMOUNT; i++) {
            generateRandom(VampusCharacter.Type.HOLE);
        }
        for (int i = 0; i < GOLD_AMOUNT; i++) {
            generateRandom(VampusCharacter.Type.GOLD);
        }
        for (int i = 0; i < VAMPUS_AMOUNT; i++) {
            generateRandom(VampusCharacter.Type.VAMPUS);
        }
    }

    private void generateRandom(VampusCharacter.Type type) {
        int col, row;
        do {
            col = new Random().nextInt(WIDTH);
            row = new Random().nextInt(HEIGHT);
        } while (board[row][col].getType() != VampusCharacter.Type.EMPTY || col == START_AGENT_COL && row == START_AGENT_ROW);
        board[row][col] = new VampusCharacter(type);
    }

    private VampusSensors generateSensors() {
        boolean stench = isAround(agentRow, agentCol, VampusCharacter.Type.VAMPUS);
        boolean breeze = isAround(agentRow, agentCol, VampusCharacter.Type.HOLE);
        boolean glitter = isOn(agentRow, agentCol, VampusCharacter.Type.GOLD);
        boolean bump = wasBump;
        boolean scream = wasScream;

        wasScream = false;
        wasBump = false;
        return new VampusSensors(stench, breeze, glitter, bump, scream);
    }

    private boolean isAround(int row, int col, VampusCharacter.Type type) {
        return row > 0 && board[row - 1][col].getType() == type ||
                row < HEIGHT - 1 && board[row + 1][col].getType() == type ||
                col > 0 && board[row][col - 1].getType() == type ||
                col < WIDTH - 1 && board[row][col + 1].getType() == type;
    }

    private boolean isOn(int row, int col, VampusCharacter.Type type) {
        return board[row][col].getType() == type;
    }

    private boolean isWall(int col, int row) {
        return
                col < 0 || col >= WIDTH || row < 0 || row >= HEIGHT ||
                        board[row][col].getType() == VampusCharacter.Type.WALL;
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
            case MOVE_UP:
                tryMoveIntoDirection(0, -1);
                break;
            case MOVE_DOWN:
                tryMoveIntoDirection(0, 1);
                break;
            case MOVE_LEFT:
                tryMoveIntoDirection(-1, 0);
                break;
            case MOVE_RIGHT:
                tryMoveIntoDirection(1, 0);
                break;
            case GRAB_GOLD:
                tryGrabGold();
                break;
        }
    }

    private void tryMoveIntoDirection(int col, int row) {
        int newCol = agentCol + col;
        int newRow = agentRow + row;

        if (isWall(newCol, newRow)) {
            wasBump = true;
        } else {
            agentCol = newCol;
            agentRow = newRow;
        }
    }

    private void tryGrabGold() {
        if (isOn(agentRow, agentCol, VampusCharacter.Type.GOLD)) {
            grabbedGold++;
            board[agentRow][agentCol] = new VampusCharacter(VampusCharacter.Type.EMPTY);
        } else {
            errors++;
        }
    }

    private void showBoard() {
        System.out.println("\n\n\n");
        System.out.println("#################");
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
        System.out.println("#################");
        System.out.println("Vampuses killed: " + killedVampuses + "/" + VAMPUS_AMOUNT);
        System.out.println("Gold grabbed:    " + grabbedGold + "/" + GOLD_AMOUNT);
        System.out.println("Errors:          " + errors);
    }

    private boolean isFinish() {
        if (isOn(agentRow, agentCol, VampusCharacter.Type.HOLE) ||
                isOn(agentRow, agentCol, VampusCharacter.Type.VAMPUS)) {
            gameOver = true;
            return true;
        }
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
