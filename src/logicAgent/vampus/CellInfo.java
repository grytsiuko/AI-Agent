package logicAgent.vampus;

public class CellInfo {

    public enum Type {
        UNKNOWN,
        TRUE,
        FALSE,
        MAYBE
    }

    private Type ok;
    private Type vampus;
    private Type hole;
    private Type wall;


    public CellInfo(){
        this.vampus = Type.UNKNOWN;
        this.hole = Type.UNKNOWN;
        this.wall = Type.UNKNOWN;
        this.ok = Type.UNKNOWN;
    }

    public boolean isSomethingTrue() {
        return isOk() || isWall() || isVampus() || isHole();
    }

    public boolean isOk() {
        return ok == Type.TRUE;
    }

    public boolean isWall() {
        return wall == Type.TRUE;
    }

    public boolean isVampus() {
        return vampus == Type.TRUE;
    }

    public boolean isHole() {
        return hole == Type.TRUE;
    }

    public Type getVampus() {
        return vampus;
    }

    public Type getHole() {
        return hole;
    }

    public Type getWall() {
        return wall;
    }

    public Type getOk() {
        return ok;
    }

    public void setOk(Type ok) {
        this.ok = ok;
    }

    public void setVampus(Type vampus) {
        this.vampus = vampus;
    }

    public void setHole(Type hole) {
        this.hole = hole;
    }

    public void setWall(Type wall) {
        this.wall = wall;
    }
}
