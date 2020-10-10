package genetic.hrytsiuk.model;

import java.util.List;

public class Subject {
    private String name;
    private int lectures;
    private int practices;
    private int groups;
    private Teacher lectureTeacher;
    private List<TeacherPractice> practiceTeachers;

    public Subject(String name, int lectures, int practices, int groups, Teacher lectureTeacher, List<TeacherPractice> practiceTeachers) {
        this.name = name;
        this.lectures = lectures;
        this.practices = practices;
        this.groups = groups;
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

    public int getGroups() {
        return groups;
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
