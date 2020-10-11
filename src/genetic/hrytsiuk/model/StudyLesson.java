package genetic.hrytsiuk.model;

public class StudyLesson {
    private final String name;

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

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        StudyLesson studyLesson = (StudyLesson) obj;
        return studyLesson.name.equals(this.name);
    }
}
