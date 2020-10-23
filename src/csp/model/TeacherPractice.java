package csp.model;

public class TeacherPractice {

    private final Teacher teacher;
    private final int groups;

    public TeacherPractice(Teacher teacher, int groups) {
        this.teacher = teacher;
        this.groups = groups;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getGroups() {
        return groups;
    }
}
