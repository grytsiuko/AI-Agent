package logicAgent.vampus;

public class VampusGame {

    private final int SLEEP_DELAY = 300;

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

    // TODO
    private void generateBoard() {
        for(int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                this.board[i][k] = new VampusCharacter(VampusCharacter.VampusCharacterEnum.EMPTY);
            }
        }
    }

    private void loop() {
        showBoard();
        while (!isFinish()) {
            sleep();
            VampusAgentMove move = this.vampusAgent.decideMove();
            doMove(move);
            showBoard();
        }
    }

    // TODO
    private void doMove(VampusAgentMove move) {

    }

    private void showBoard() {
        System.out.println("#########################################");
        for(int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                if (i == agentX && k == agentY) {
                    System.out.print("|" + board[i][k] + "|");
                } else {
                    System.out.print(" " + board[i][k] + " ");
                }
            }
            System.out.println();
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
