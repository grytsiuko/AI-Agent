package genetic.hrytsiuk.model;

import java.util.List;

public class Subject {
    private final String name;
    private final int lectures;
    private final int practices;
    private final Teacher lectureTeacher;
    private final List<TeacherPractice> practiceTeachers;

    public Subject(String name, int lectures, int practices, Teacher lectureTeacher, List<TeacherPractice> practiceTeachers) {
        this.name = name;
        this.lectures = lectures;
        this.practices = practices;
        this.lectureTeacher = lectureTeacher;
        this.practiceTeachers = practiceTeachers;
    }

    public String getName() {
        return name;
    }

    public int getLectures() {
        return lectures;
    }

    public int getPractices() {
        return practices;
    }

    public Teacher getLectureTeacher() {
        return lectureTeacher;
    }

    public List<TeacherPractice> getPracticeTeachers() {
        return practiceTeachers;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Subject subject = (Subject) obj;
        return subject.name.equals(this.name);
    }
}
