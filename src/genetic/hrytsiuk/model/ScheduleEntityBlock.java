package genetic.hrytsiuk.model;

public class ScheduleEntityBlock {

    private StudentsGroup studentsGroup;
    private Subject subject;
    private Teacher teacher;
    private boolean isLecture;
    private Integer group;

    public ScheduleEntityBlock(StudentsGroup studentsGroup, Subject subject, Teacher teacher, boolean isLecture, Integer group) {
        this.studentsGroup = studentsGroup;
        this.subject = subject;
        this.teacher = teacher;
        this.isLecture = isLecture;
        this.group = group;
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

    public Integer getGroup() {
        return group;
    }

    @Override
    public String toString() {
        String meta = studentsGroup.getProgram() + " - " + subject.getName() + " - " + teacher.getName();
        return isLecture
                ? meta + " - LECTURE"
                : meta + " - " + group + " GROUP";
    }
}
