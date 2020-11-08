package logicAgent.vampus;

public class CellInfo {

    public Boolean vampus;
    public Boolean hole;
    public Boolean wall;


    public CellInfo(){}

    public CellInfo(Boolean vampus, Boolean hole, Boolean wall){
        this.vampus = vampus;
        this.hole = hole;
        this.wall = wall;
    }

    public boolean isOk(){
        if(wall != null && wall){
            return false;
        }

        if(vampus != null && hole != null){
            return !vampus && !hole;
        }

        return false;
    }

}
