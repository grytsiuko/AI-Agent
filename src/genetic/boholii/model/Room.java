package genetic.boholii.model;

public class Room {

    private String title;
    private boolean forLecture;

    public Room(String title, boolean forLecture){
        this.title = title;
        this.forLecture = forLecture;
    }

    public String getTitle() {
        return title;
    }

    public boolean isForLecture() {
        return forLecture;
    }

    @Override
    public String toString() {
        return  title + (forLecture ? " (big)" : " (small)");
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || object.getClass()!= this.getClass()) {
            return false;
        }
        Room that = (Room) object;
        return this.title.equals(that.title) && this.forLecture == that.forLecture;
    }

}
