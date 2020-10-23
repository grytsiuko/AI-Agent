package csp.model;

public class Classroom {

    private final int building;
    private final int number;
    private final boolean isWide;

    public Classroom(int building, int number, boolean isWide) {
        this.building = building;
        this.number = number;
        this.isWide = isWide;
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
