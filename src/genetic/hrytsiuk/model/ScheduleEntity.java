package genetic.hrytsiuk.model;

public class ScheduleEntity {

    private final ScheduleEntityBlock scheduleEntityBlock;
    private final Classroom classroom;
    private final StudyDay studyDay;
    private final StudyLesson studyLesson;

    public ScheduleEntity(ScheduleEntityBlock scheduleEntityBlock, Classroom classroom, StudyDay studyDay, StudyLesson studyLesson) {
        this.scheduleEntityBlock = scheduleEntityBlock;
        this.classroom = classroom;
        this.studyDay = studyDay;
        this.studyLesson = studyLesson;
    }

    public ScheduleEntityBlock getScheduleEntityBlock() {
        return scheduleEntityBlock;
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
        return studyDay + " " + studyLesson + " " + classroom + ":\t" + scheduleEntityBlock;
    }
}
