package logicAgent;

import logicAgent.vampus.VampusAgent;
import logicAgent.vampus.VampusGame;

public class LogicMain {

    public static void main(String[] args) {
        VampusAgent vampusAgent = new VampusAgent();
        VampusGame vampusGame = new VampusGame(vampusAgent);
        vampusGame.start();
    }
}
