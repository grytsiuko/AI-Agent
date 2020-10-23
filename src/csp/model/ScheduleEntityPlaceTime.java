package csp.model;

public class ScheduleEntityPlaceTime {

    private final Classroom classroom;
    private final StudyDay studyDay;
    private final StudyLesson studyLesson;

    public ScheduleEntityPlaceTime(Classroom classroom, StudyDay studyDay, StudyLesson studyLesson) {
        this.classroom = classroom;
        this.studyDay = studyDay;
        this.studyLesson = studyLesson;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public StudyDay getStudyDay() {
        return studyDay;
    }

    public StudyLesson getStudyLesson() {
        return studyLesson;
    }

    @Override
    public String toString() {
        return studyDay + " " + studyLesson + " " + classroom;
    }
}
