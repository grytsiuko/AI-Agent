package com.zetcode;

import agent.Agent;
import agent.dfs.AgentDfs;
import environment.EnvironmentInterface;
import environment.MoveInterface;

import javax.imageio.metadata.IIOMetadataNode;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Board board = new Board();
        Pacman pacman = new Pacman(board);
        EnvironmentInterface<PacmanMove, Integer> environment = new PacmanEnvironment(board);

        Agent agent = new AgentDfs<>(environment);

        board.changeAgent(agent);

        pacman.run();
        agent.run();
    }
}
