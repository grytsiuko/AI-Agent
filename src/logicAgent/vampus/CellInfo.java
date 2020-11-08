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

    public CellInfo(Type ok, Type vampus, Type hole, Type wall){
        this.ok = ok;
        this.vampus = vampus;
        this.hole = hole;
        this.wall = wall;
    }

//    public boolean isOk(){
//        if(wall != null && wall){
//            return false;
//        }
//
//        if(vampus != null && hole != null){
//            return !vampus && !hole;
//        }
//
//        return false;
//    }


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
}
