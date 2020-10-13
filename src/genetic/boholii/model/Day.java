package genetic.boholii.model;

public class Day {

    private String title;

    public Day(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || object.getClass()!= this.getClass()) {
            return false;
        }
        Day that = (Day) object;
        return this.title.equals(that.title);
    }
}
