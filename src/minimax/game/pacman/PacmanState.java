package minimax.game.pacman;

import java.util.List;

public class PacmanState {
    private int pacmanX;
    private int pacmanY;
    private List<Integer> ghostsX;
    private List<Integer> ghostsY;

    public PacmanState(int pacmanX, int pacmanY, List<Integer> ghostsX, List<Integer> ghostsY) {
        this.pacmanX = pacmanX;
        this.pacmanY = pacmanY;
        this.ghostsX = ghostsX;
        this.ghostsY = ghostsY;
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
