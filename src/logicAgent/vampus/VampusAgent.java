package logicAgent.vampus;

import logicAgent.vampus.rules.*;

import java.util.*;

public class VampusAgent {

    public static final int START_AGENT_ROW = VampusGame.START_AGENT_ROW;
    public static final int START_AGENT_COL = VampusGame.START_AGENT_COL;

    private int agentRow = START_AGENT_ROW;
    private int agentCol = START_AGENT_COL;

    private final int WIDTH = VampusGame.WIDTH;
    private final int HEIGHT = VampusGame.HEIGHT;

    private final List<VampusAbstractRule> rules;

    private final CellInfo[][] cellsInfo;

    private VampusAgentMove.Type lastMoveType = null;

    private Stack<VampusAgentMove.Type> path;
    private Set<Coord> visited;

    private Bool foundNewOk;

    public VampusAgent() {
        this.cellsInfo = initCellsInfo();
        foundNewOk = new Bool(false);
        this.path = new Stack<>();
        this.visited = new HashSet<>();
        this.rules = List.of(
                new VampusStenchRule(cellsInfo, foundNewOk),
                new VampusBreezeRule(cellsInfo, foundNewOk),
                new VampusOkRule(cellsInfo, foundNewOk),
                new VampusHereRule(cellsInfo, foundNewOk),
                new VampusBumpRule(cellsInfo, foundNewOk),
                new VampusScreamRule(cellsInfo, foundNewOk)
        );
    }

    public CellInfo[][] initCellsInfo(){
        CellInfo[][] info = new CellInfo[HEIGHT][WIDTH];

        for(int row = 0; row < VampusGame.HEIGHT; row++){
            for(int col = 0; col < VampusGame.WIDTH; col++){
                info[row][col] = new CellInfo();
            }
        }
        return info;
    }

    public VampusAgentMove decideMove(VampusSensors vampusSensors) {
        moveBackIfBump(vampusSensors);
        concludeAll(vampusSensors);

        VampusAgentMove.Type choice = decideMoveType(vampusSensors);
        move(choice);
        lastMoveType = choice;

        return new VampusAgentMove(choice);
    }

    private void concludeAll(VampusSensors vampusSensors) {
        for (VampusAbstractRule rule:rules) {
            rule.conclude(agentRow, agentCol, vampusSensors, lastMoveType);
        }
    }

    private VampusAgentMove.Type decideMoveType(VampusSensors vampusSensors) {

        if (vampusSensors.isGlitter()) {
            return VampusAgentMove.Type.GRAB_GOLD;
        }

        if (agentRow < HEIGHT - 1 && cellsInfo[agentRow + 1][agentCol].isVampus()) {
            return VampusAgentMove.Type.ARROW_DOWN;
        }
        if (agentRow > 0 && cellsInfo[agentRow - 1][agentCol].isVampus()) {
            return VampusAgentMove.Type.ARROW_UP;
        }
        if (agentCol < WIDTH - 1 && cellsInfo[agentRow][agentCol + 1].isVampus()) {
            return VampusAgentMove.Type.ARROW_RIGHT;
        }
        if (agentCol > 0 && cellsInfo[agentRow][agentCol - 1].isVampus()) {
            return VampusAgentMove.Type.ARROW_LEFT;
        }

        Optional<VampusAgentMove.Type> optionalMove = getNextMove();

        if(optionalMove.isPresent()){
            VampusAgentMove.Type move = optionalMove.get();
            visited.add(getTargetCoord(move));
            path.push(move);
            return move;

        } else {
            if(path.empty()){
                if(foundNewOk.getValue()){
                    visited.clear();
                    foundNewOk.setValue(false);
                    return decideMoveType(vampusSensors);
                }
                return VampusAgentMove.Type.FINISH;
            }
            return reverseMove(path.pop());
        }
    }

    private Optional<VampusAgentMove.Type> getNextMove(){
        List<VampusAgentMove.Type> posMoves = getPossibleMoves();
        Collections.shuffle(posMoves);

        for(VampusAgentMove.Type move:posMoves){
            if(toVisited(move)){
                continue;
            }
            return Optional.of(move);
        }
        return Optional.empty();
    }

    private boolean toVisited(VampusAgentMove.Type move){
        return visited.contains(getTargetCoord(move));
    }

    private List<VampusAgentMove.Type> getPossibleMoves(){
        List<VampusAgentMove.Type> moves = new ArrayList<>();
        if (agentRow < HEIGHT - 1 && cellsInfo[agentRow + 1][agentCol].isOk()) {
            moves.add(VampusAgentMove.Type.MOVE_DOWN);
        }
        if (agentRow > 0 && cellsInfo[agentRow - 1][agentCol].isOk()) {
            moves.add(VampusAgentMove.Type.MOVE_UP);
        }
        if (agentCol < WIDTH - 1 && cellsInfo[agentRow][agentCol + 1].isOk()) {
            moves.add(VampusAgentMove.Type.MOVE_RIGHT);
        }
        if (agentCol > 0 && cellsInfo[agentRow][agentCol - 1].isOk()) {
            moves.add(VampusAgentMove.Type.MOVE_LEFT);
        }
        return moves;
    }

    private void moveBackIfBump(VampusSensors sensors) {
        if (sensors.isBump()) {
            switch (lastMoveType) {
                case MOVE_UP:
                    agentRow++;
                    break;
                case MOVE_DOWN:
                    agentRow--;
                    break;
                case MOVE_LEFT:
                    agentCol++;
                    break;
                case MOVE_RIGHT:
                    agentCol--;
                    break;
            }
        }
    }

    private void move(VampusAgentMove.Type type) {
            switch (type) {
                case MOVE_UP:
                    agentRow--;
                    break;
                case MOVE_DOWN:
                    agentRow++;
                    break;
                case MOVE_LEFT:
                    agentCol--;
                    break;
                case MOVE_RIGHT:
                    agentCol++;
                    break;
            }
    }

    private Coord getTargetCoord(VampusAgentMove.Type move){
        switch (move) {
            case MOVE_UP:
                return new Coord(agentRow-1, agentCol);
            case MOVE_DOWN:
                return new Coord(agentRow+1, agentCol);
            case MOVE_LEFT:
                return new Coord(agentRow, agentCol-1);
            case MOVE_RIGHT:
                return new Coord(agentRow, agentCol+1);
            default:
                throw new RuntimeException("Illegal state");
        }
    }

    private VampusAgentMove.Type reverseMove(VampusAgentMove.Type move){
        switch (move) {
            case MOVE_UP:
                return VampusAgentMove.Type.MOVE_DOWN;
            case MOVE_DOWN:
                return VampusAgentMove.Type.MOVE_UP;
            case MOVE_LEFT:
                return VampusAgentMove.Type.MOVE_RIGHT;
            case MOVE_RIGHT:
                return VampusAgentMove.Type.MOVE_LEFT;
            default:
                throw new RuntimeException("Illegal state");
        }
    }

}
