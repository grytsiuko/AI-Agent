package genetic.hrytsiuk.model;

public class TeacherPractice {

    private Teacher teacher;
    private int groups;

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
