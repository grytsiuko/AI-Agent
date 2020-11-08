package logicAgent.vampus;

import genetic.hrytsiuk.model.Subject;

public class Coord {

    private final int row;
    private final int col;

    public Coord(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) {
            return true;
        }
        if(o == null || o.getClass()!= this.getClass()) {
            return false;
        }
        Coord   that    = (Coord)o;
        return this.col == that.col && this.row == that.row;
    }

    @Override
    public int hashCode(){
        return col + row * VampusGame.WIDTH;
    }


}
