package genetic.hrytsiuk.model;

public class ScheduleEntityBlock {

    private StudentsGroup studentsGroup;
    private Subject subject;
    private Teacher teacher;
    private boolean isLecture;

    public ScheduleEntityBlock(StudentsGroup studentsGroup, Subject subject, Teacher teacher, boolean isLecture) {
        this.studentsGroup = studentsGroup;
        this.subject = subject;
        this.teacher = teacher;
        this.isLecture = isLecture;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public boolean isLecture() {
        return isLecture;
    }
}
