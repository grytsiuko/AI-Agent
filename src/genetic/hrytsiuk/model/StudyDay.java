package genetic.hrytsiuk.model;

public class StudyDay {
    private String name;

    public StudyDay(String name) {
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
        StudyDay studyDay = (StudyDay) obj;
        return studyDay.name.equals(this.name);
    }
}
