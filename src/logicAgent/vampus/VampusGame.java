package logicAgent.vampus;

public class VampusGame {

    private final VampusAgent vampusAgent;

    public VampusGame(VampusAgent vampusAgent) {
        this.vampusAgent = vampusAgent;
    }

    public void start() {
        loop();
    }

    private void loop() {
        showBoard();
        while (!isFinish()) {
            VampusAgentMove move = this.vampusAgent.decideMove();
            doMove();
            showBoard();
        }
    }

    private void doMove() {

    }

    private void showBoard() {

    }

    private boolean isFinish() {
        return false;
    }

}
