package genetic.hrytsiuk.model;

public class ScheduleEntity {

    private Classroom classroom;
    private StudentsGroup studentsGroup;
    private StudyDay studyDay;
    private StudyLesson studyLesson;
    private Subject subject;
    private Teacher teacher;
    private boolean isLecture;

    public Classroom getClassroom() {
        return classroom;
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public StudyDay getStudyDay() {
        return studyDay;
    }

    public StudyLesson getStudyLesson() {
        return studyLesson;
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
