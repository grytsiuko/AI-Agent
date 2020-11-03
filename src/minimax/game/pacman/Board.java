package minimax.game.pacman;

import minimax.game.Environment;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;
import java.util.List;

public class Board extends JPanel implements ActionListener, Environment<PacmanState, PacmanAgent, PacmanMove> {
    // HERE YOU COULD CHANGE DELAY BETWEEN MOVES
    private final int[][] initLevelMap;
    private final int MOVE_DELAY = 50;
    private final int LEVEL_BONUS = 50;

    private boolean gameOver = false;

    private boolean lastMoveEaten = false;

    private int BINARY_LEFT_WALL  = 0b1;
    private int BINARY_UP_WALL    = 0b10;
    private int BINARY_RIGHT_WALL = 0b100;
    private int BINARY_DOWN_WALL  = 0b1000;
    private int BINARY_SWEET      = 0b10000;

    private       Dimension d;
    private final Font      smallFont = new Font("Helvetica", Font.BOLD, 14);

    private       Image ii;
    private final Color dotColor = new Color(192, 192, 0);
    private       Color mazeColor;

    private final int BLOCK_SIZE = 24;
    private final int WIDTH;
    private final int HEIGHT;

    private final int N_BLOCKS;
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final int PAC_ANIM_DELAY    = 2;
    private final int PACMAN_ANIM_COUNT = 4;
    private final int PACMAN_SPEED      = 1;

    private int pacAnimCount  = PAC_ANIM_DELAY;
    private int pacAnimDir    = 1;
    private int pacmanAnimPos = 0;
    private int score;

    private Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    private Image pacman3up, pacman3down, pacman3left, pacman3right;
    private Image pacman4up, pacman4down, pacman4left, pacman4right;
    private Image ghost;

    private int pacmand_x, pacmand_y;
    private int pacman_x = 0 * BLOCK_SIZE;
    private int pacman_y = 0 * BLOCK_SIZE;
    private int req_dx, req_dy, view_dx, view_dy;

    private java.util.List<Integer> ghostsX = new ArrayList<>();
    private java.util.List<Integer> ghostsY = new ArrayList<>();

    private List<PacmanAgent> pendingEnemies = new ArrayList<>();

    private int oldPacmanX, oldPacmanY;
    private final int REPAINT_DELAY = 40;

    private final int[] levelData;

    private int[] screenData;
    private Timer timer;

    public Board(int[][] levelData) throws Exception {
        initLevelMap = levelData;
        WIDTH = initWidth(levelData);
        HEIGHT = levelData.length;
        N_BLOCKS = WIDTH * HEIGHT;
        SCREEN_WIDTH = WIDTH * BLOCK_SIZE;
        SCREEN_HEIGHT = HEIGHT * BLOCK_SIZE;
        this.levelData = new int[N_BLOCKS];
        generateLevelData(levelData);
        addLevelFrame();
        loadImages();
        initVariables();
        initBoard();
        initGame();
        addGhost();
    }

    private int initWidth(int[][] levelData) throws Exception {
        Optional<int[]> minRow = Arrays.stream(levelData).min(Comparator.comparingInt(o -> o.length));
        if (minRow.isEmpty()) {
            throw new Exception("Empty level data");
        }
        return minRow.get().length;
    }

    private void addLevelFrame() {
        int y = 0;
        for (int x = 0; x < WIDTH; ++x) {
            int coord = x + WIDTH * y;
            this.levelData[coord] = this.levelData[coord] | BINARY_UP_WALL;
        }

        for (y = 0; y < HEIGHT; ++y) {
            int x     = 0;
            int coord = x + WIDTH * y;
            this.levelData[coord] = this.levelData[coord] | BINARY_LEFT_WALL;

            x = WIDTH - 1;
            coord = x + WIDTH * y;
            this.levelData[coord] = this.levelData[coord] | BINARY_RIGHT_WALL;
        }

        y = HEIGHT - 1;
        for (int x = 0; x < WIDTH; ++x) {
            int coord = x + WIDTH * y;
            this.levelData[coord] = this.levelData[coord] | BINARY_DOWN_WALL;
        }

    }

    private void generateLevelData(int[][] levelData) {
        for (int y = 0; y < HEIGHT; ++y) {
            for (int x = 0; x < WIDTH; ++x) {
                int coord = x + WIDTH * y;
                int data  = levelData[y][x];
                if (data == 1) {
                    addBlock(coord);
                } else {
                    this.levelData[coord] = this.levelData[coord] | BINARY_SWEET;
                }
            }
        }
    }

    private void addBlock(int coord) {
        //levelData[coord] = BINARY_LEFT_WALL | BINARY_UP_WALL | BINARY_RIGHT_WALL | BINARY_DOWN_WALL;

        int upCoord = getUpCoord(coord);
        if (upCoord >= 0) {
            if ((levelData[coord] & BINARY_UP_WALL) == BINARY_UP_WALL) {
                levelData[coord] &= ~BINARY_UP_WALL;
            } else {
                levelData[upCoord] |= BINARY_DOWN_WALL;
            }
        }

        int downCoord = getDownCoord(coord);
        if (downCoord < N_BLOCKS) {

            if ((levelData[coord] & BINARY_DOWN_WALL) == BINARY_DOWN_WALL) {
                levelData[coord] &= ~BINARY_DOWN_WALL;
            } else {
                levelData[downCoord] |= BINARY_UP_WALL;
            }
        }

        int leftCoord = getLeftCoord(coord);
        if (leftCoord >= 0) {
            if ((levelData[coord] & BINARY_LEFT_WALL) == BINARY_LEFT_WALL) {
                levelData[coord] &= ~BINARY_LEFT_WALL;
            } else {
                levelData[leftCoord] |= BINARY_RIGHT_WALL;
            }
        }

        int rightCoord = getRightCoord(coord);
        if (rightCoord < N_BLOCKS) {

            if ((levelData[coord] & BINARY_RIGHT_WALL) == BINARY_RIGHT_WALL) {
                levelData[coord] = levelData[coord] & ~BINARY_RIGHT_WALL;
            } else {
                levelData[rightCoord] |=  BINARY_LEFT_WALL;
            }
        }
    }

    private int getUpCoord(int coord) {
        return coord - WIDTH;
    }

    private int getDownCoord(int coord) {
        return coord + WIDTH;
    }

    private int getLeftCoord(int coord) {
        return coord - 1;
    }

    private int getRightCoord(int coord) {
        return coord + 1;
    }

    public int getBoardWidth() {
        return WIDTH;
    }

    public int getBoardHeight() {
        return HEIGHT;
    }

    public boolean moveUp(int agentId) {
        if (agentId == -1) {
            req_dx = 0;
            req_dy = -1;
            moveAndCheck();
            return wasMoved();
        }
        return moveAndCheckGhost(agentId, 0, -1);
    }

    public boolean moveDown(int agentId) {
        if (agentId == -1) {
            req_dx = 0;
            req_dy = 1;
            moveAndCheck();
            return wasMoved();
        }
        return moveAndCheckGhost(agentId, 0, 1);
    }

    public boolean moveLeft(int agentId) {
        if (agentId == -1) {
            req_dx = -1;
            req_dy = 0;
            moveAndCheck();
            return wasMoved();
        }
        return moveAndCheckGhost(agentId, -1, 0);
    }

    public boolean moveRight(int agentId) {
        if (agentId == -1) {
            req_dx = 1;
            req_dy = 0;
            moveAndCheck();
            return wasMoved();
        }
        return moveAndCheckGhost(agentId, 1, 0);
    }

    private boolean wasMoved() {
        return oldPacmanX != pacman_x || oldPacmanY != pacman_y;
    }

    public int getPacmanX() {
        return pacman_x;
    }

    public int getPacmanY() {
        return pacman_y;
    }

    public int getBlocksAmount() {
        return N_BLOCKS;
    }

    public int getBlockSize() {
        return BLOCK_SIZE;
    }


    private void initBoard() {

//        setFocusable(true);

        setBackground(Color.black);
    }

    private void initVariables() {

        screenData = new int[N_BLOCKS];
        mazeColor = new Color(5, 100, 5);
        d = new Dimension(400, 400);

        timer = new Timer(REPAINT_DELAY, this);
        timer.start();
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

    private void doAnim() {

        pacAnimCount--;

        if (pacAnimCount <= 0) {
            pacAnimCount = PAC_ANIM_DELAY;
            pacmanAnimPos = pacmanAnimPos + pacAnimDir;

            if (pacmanAnimPos == (PACMAN_ANIM_COUNT - 1) || pacmanAnimPos == 0) {
                pacAnimDir = -pacAnimDir;
            }
        }
    }

    private void playGame(Graphics2D g2d) {
        drawPacman(g2d);
        drawGhosts(g2d);
    }

    private void moveAndCheck() {
        oldPacmanX = pacman_x;
        oldPacmanY = pacman_y;
        movePacman();
        checkMaze();
    }

    private boolean moveAndCheckGhost(int agentId, int dirX, int dirY) {
        if (!canMove(ghostsX.get(agentId), ghostsY.get(agentId), dirX, dirY)) {
            return false;
        }
        ghostsX.set(agentId, ghostsX.get(agentId) + dirX * PACMAN_SPEED * BLOCK_SIZE);
        ghostsY.set(agentId, ghostsY.get(agentId) + dirY * PACMAN_SPEED * BLOCK_SIZE);
        checkCollision();
        return true;
    }

    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, SCREEN_HEIGHT / 2 - 30, SCREEN_WIDTH - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, SCREEN_HEIGHT / 2 - 30, SCREEN_WIDTH - 100, 50);

        String      s     = "Press s to start.";
        Font        small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr  = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (SCREEN_WIDTH - metr.stringWidth(s)) / 2, SCREEN_HEIGHT / 2);
    }

    private void drawScore(Graphics2D g) {
        String s;

        g.setFont(smallFont);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + score;
        g.drawString(s, SCREEN_WIDTH / 2 + 96, SCREEN_HEIGHT + 16);
    }

    private void checkMaze() {
        if (isFinished()) {
            score += LEVEL_BONUS;
            initLevel();
            addGhost();
        }
    }

    public boolean isFinished() {
        short   i        = 0;
        boolean finished = true;

        while (i < N_BLOCKS && finished) {
            if ((screenData[i] & 16) != 0) {
                return false;
            }

            i++;
        }

        return finished;
    }

    private int getMinDistToDotFromState(int stateX, int stateY) {
        int   i        = 0;
        int min = -1;

        while (i < N_BLOCKS) {
            if ((screenData[i] & 16) != 0) {
                int x = i % WIDTH;
                int y = i / WIDTH;
                x *= BLOCK_SIZE;
                y *= BLOCK_SIZE;
                int dist = Math.abs(x - stateX) + Math.abs(y - stateY);
                if (min == -1 || dist < min) {
                    min = dist;
                }
            }

            i++;
        }

        return min;
    }

    public boolean isReached() {
        if (lastMoveEaten) {
            lastMoveEaten = false;
            return true;
        }
        return false;
    }

    public boolean isReached(int x, int y) {
        int pos = x / BLOCK_SIZE + WIDTH * (y / BLOCK_SIZE);
        int ch  = screenData[pos];
        return (ch & 32) != 0;
    }

    private void movePacman() {
        pacmand_x = req_dx;
        pacmand_y = req_dy;
        view_dx = pacmand_x;
        view_dy = pacmand_y;

        if (!canMove(pacman_x, pacman_y, pacmand_x, pacmand_y)) {
            pacmand_x = 0;
            pacmand_y = 0;
        }

        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x * BLOCK_SIZE;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y * BLOCK_SIZE;

        int pos = pacman_x / BLOCK_SIZE + WIDTH * (pacman_y / BLOCK_SIZE);
        int ch  = screenData[pos];

        if ((ch & 32) != 0) {
            lastMoveEaten = true;
        }

        if ((ch & 48) != 0) {
            screenData[pos] = (short) (ch & 15);
            score++;
        }
        checkCollision();
    }

    private void checkCollision() {
        for (int i = 0; i < ghostsX.size(); i++) {
            if (ghostsX.get(i) == pacman_x && ghostsY.get(i) == pacman_y) {
                gameOver = true;
                break;
            }
        }
    }


    public boolean canMove(int x, int y, int directionX, int directionY) {
        int pos = x / BLOCK_SIZE + WIDTH * (y / BLOCK_SIZE);
        int ch  = screenData[pos];

        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {

            // Check for standstill
            return !((directionX == -1 && directionY == 0 && (ch & 1) != 0) ||
                    (directionX == 1 && directionY == 0 && (ch & 4) != 0) ||
                    (directionX == 0 && directionY == -1 && (ch & 2) != 0) ||
                    (directionX == 0 && directionY == 1 && (ch & 8) != 0));
        }
        return true;
    }

    public double currentHeuristic(int x, int y) {
        return targetHeuristic(x, y, 0, 0);
    }

    public double targetHeuristic(int x, int y, int directionX, int directionY) {
        int xBlock = x / BLOCK_SIZE + directionX;
        int yBlock = y / BLOCK_SIZE + directionY;
        return calcHeuristic(xBlock, yBlock);
    }

    private double calcHeuristic(int x, int y) {
        Double min = null;
        for (int i = 0; i < HEIGHT; i++) {
            for (int k = 0; k < WIDTH; k++) {
                int pos = i * WIDTH + k;
                if ((screenData[pos] & 32) != 0) {
                    double curr = Math.abs(y - i) + Math.abs(x - k);
                    if (min == null || curr < min) {
                        min = curr;
                    }
                }
            }
        }
        return min == null ? 0 : min;
    }

    private void drawPacman(Graphics2D g2d) {

        if (view_dx == -1) {
            drawPacnanLeft(g2d);
        } else if (view_dx == 1) {
            drawPacmanRight(g2d);
        } else if (view_dy == -1) {
            drawPacmanUp(g2d);
        } else {
            drawPacmanDown(g2d);
        }
    }

    private void drawGhosts(Graphics2D g2d) {
        for (int i = 0; i < ghostsX.size(); i++) {
            int x = ghostsX.get(i);
            int y = ghostsY.get(i);
            g2d.drawImage(ghost, x, y, this);
        }
    }

    private void drawPacmanUp(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3up, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4up, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanDown(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3down, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4down, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacnanLeft(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3left, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4left, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawPacmanRight(Graphics2D g2d) {

        switch (pacmanAnimPos) {
            case 1:
                g2d.drawImage(pacman2right, pacman_x + 1, pacman_y + 1, this);
                break;
            case 2:
                g2d.drawImage(pacman3right, pacman_x + 1, pacman_y + 1, this);
                break;
            case 3:
                g2d.drawImage(pacman4right, pacman_x + 1, pacman_y + 1, this);
                break;
            default:
                g2d.drawImage(pacman1, pacman_x + 1, pacman_y + 1, this);
                break;
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int   x, y;

        for (y = 0; y < SCREEN_HEIGHT; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_WIDTH; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                if ((screenData[i] & 32) != 0) {
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 9, 9);
                }

                i++;
            }
        }
    }

    private void initGame() {
        score = 0;
        initLevel();
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {
        pacmand_x = 0;
        pacmand_y = 0;
        req_dx = 0;
        req_dy = 0;
        view_dx = -1;
        view_dy = 0;
    }

    private void addGhost() {
        pendingEnemies.add(new PacmanAgent(this, ghostsX.size()));
        int x;
        int y;
        do {
            x = new Random().nextInt(WIDTH);
            y = new Random().nextInt(HEIGHT);
        } while (initLevelMap[y][x] == 1 || Math.abs(x*BLOCK_SIZE - pacman_x)+Math.abs(y*BLOCK_SIZE-pacman_y) < 5*BLOCK_SIZE);
        x *= BLOCK_SIZE;
        y *= BLOCK_SIZE;
        ghostsX.add(x);
        ghostsY.add(y);
    }

    private void loadImages() {
        pacman1 = new ImageIcon("src/resources/images/pacman.png").getImage();
        pacman2up = new ImageIcon("src/resources/images/up1.png").getImage();
        pacman3up = new ImageIcon("src/resources/images/up2.png").getImage();
        pacman4up = new ImageIcon("src/resources/images/up3.png").getImage();
        pacman2down = new ImageIcon("src/resources/images/down1.png").getImage();
        pacman3down = new ImageIcon("src/resources/images/down2.png").getImage();
        pacman4down = new ImageIcon("src/resources/images/down3.png").getImage();
        pacman2left = new ImageIcon("src/resources/images/left1.png").getImage();
        pacman3left = new ImageIcon("src/resources/images/left2.png").getImage();
        pacman4left = new ImageIcon("src/resources/images/left3.png").getImage();
        pacman2right = new ImageIcon("src/resources/images/right1.png").getImage();
        pacman3right = new ImageIcon("src/resources/images/right2.png").getImage();
        pacman4right = new ImageIcon("src/resources/images/right3.png").getImage();
        ghost = new ImageIcon("src/resources/images/ghost.png").getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        drawMaze(g2d);
        drawScore(g2d);
        doAnim();

        playGame(g2d);


        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public List<PacmanAgent> getPendingEnemies() {
        List<PacmanAgent> copy = pendingEnemies;
        pendingEnemies = new ArrayList<>();
        return copy;
    }

    @Override
    public PacmanState getState() {
        return new PacmanState(-100, -100, pacman_x, pacman_y, new ArrayList<>(ghostsX), new ArrayList<>(ghostsY), Collections.emptyList(), Collections.emptyList());
    }

    @Override
    public boolean isFinish(PacmanState state) {
        return gameOver;
    }

    @Override
    public void refresh() {
        repaint();
        try {
            Thread.sleep(MOVE_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int calculateHeuristic(PacmanState state) {
        for (int i = 0; i < state.getGhostsX().size(); i++) {
            int gX = state.getGhostsX().get(i);
            int gY = state.getGhostsY().get(i);
            int old_gX = state.getPrevGhostsX().get(i);
            int old_gY = state.getPrevGhostsY().get(i);
            if (gX == state.getPrevPacmanX() && gY == state.getPrevPacmanY() &&
                state.getPacmanX() ==  old_gX && state.getPacmanY() == old_gY) {
                return Integer.MIN_VALUE;
            }
        }
        int minDistToGhost = -1;
        for (int i = 0; i < state.getGhostsX().size(); i++) {
            int gX = state.getGhostsX().get(i);
            int gY = state.getGhostsY().get(i);
            int gDist = Math.abs(state.getPacmanX() - gX) + Math.abs(state.getPacmanY() - gY);
            if (minDistToGhost == -1 || gDist < minDistToGhost) {
                minDistToGhost = gDist;
            }
        }
        if (minDistToGhost <= 2*BLOCK_SIZE) {
            return Integer.MIN_VALUE + minDistToGhost;
        }
        int minDistToDot = getMinDistToDotFromState(state.getPacmanX(), state.getPacmanY());
        return minDistToGhost - 2*minDistToDot;
    }
}
