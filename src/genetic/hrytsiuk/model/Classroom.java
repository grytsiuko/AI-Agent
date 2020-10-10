package genetic.hrytsiuk.model;

public class Classroom {

    private int building;
    private int number;
    private boolean isWide;

    public Classroom(int building, int number, boolean isWide) {
        this.building = building;
        this.number = number;
        this.isWide = isWide;
    }

    public int getBuilding() {
        return building;
    }

    public int getNumber() {
        return number;
    }

    public boolean isWide() {
        return isWide;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Classroom classroom = (Classroom) obj;
        return classroom.number == this.number && classroom.building == this.building;
    }

    @Override
    public String toString() {
        String meta = building + "-" + number;
        return isWide
                ? meta + " W"
                : meta + "  ";
    }
}
