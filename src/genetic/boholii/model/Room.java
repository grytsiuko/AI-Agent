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
}
