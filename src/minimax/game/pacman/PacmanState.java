package minimax.game.pacman;

import java.util.List;

public class PacmanState {
    private int prevPacmanX;
    private int prevPacmanY;
    private int pacmanX;
    private int pacmanY;
    private List<Integer> ghostsX;
    private List<Integer> ghostsY;
    private List<Integer> prevGhostsX;
    private List<Integer> prevGhostsY;

    public PacmanState(int prevPacmanX, int prevPacmanY, int pacmanX, int pacmanY, List<Integer> ghostsX, List<Integer> ghostsY, List<Integer> prevGhostsX, List<Integer> prevGhostsY) {
        this.prevPacmanX = prevPacmanX;
        this.prevPacmanY = prevPacmanY;
        this.pacmanX = pacmanX;
        this.pacmanY = pacmanY;
        this.ghostsX = ghostsX;
        this.ghostsY = ghostsY;
        this.prevGhostsX = prevGhostsX;
        this.prevGhostsY = prevGhostsY;
    }

    public List<Integer> getPrevGhostsX() {
        return prevGhostsX;
    }

    public List<Integer> getPrevGhostsY() {
        return prevGhostsY;
    }

    public int getPrevPacmanX() {
        return prevPacmanX;
    }

    public int getPrevPacmanY() {
        return prevPacmanY;
    }

    public int getPacmanX() {
        return pacmanX;
    }

    public int getPacmanY() {
        return pacmanY;
    }

    public List<Integer> getGhostsX() {
        return ghostsX;
    }

    public List<Integer> getGhostsY() {
        return ghostsY;
    }
}
