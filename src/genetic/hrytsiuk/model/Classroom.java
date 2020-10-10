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
}
