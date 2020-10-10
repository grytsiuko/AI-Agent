package genetic.hrytsiuk.model;

public class StudyLesson {
    private String name;

    public StudyLesson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
