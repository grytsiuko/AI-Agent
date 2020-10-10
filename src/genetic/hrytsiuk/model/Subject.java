package genetic.hrytsiuk.model;

import java.util.List;

public class Subject {
    private String name;
    private int lectures;
    private int practices;
    private int groups;
    private Teacher lectureTeacher;
    private List<Teacher> practiceTeachers;

    public String getName() {
        return name;
    }

    public int getLectures() {
        return lectures;
    }

    public int getPractices() {
        return practices;
    }

    public int getGroups() {
        return groups;
    }

    public Teacher getLectureTeacher() {
        return lectureTeacher;
    }

    public List<Teacher> getPracticeTeachers() {
        return practiceTeachers;
    }
}
