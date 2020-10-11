package genetic.hrytsiuk.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private final List<ScheduleEntity> entities;
    private final int errorRate;

    public Schedule(List<ScheduleEntity> entities) {
        this.entities = entities;
        this.errorRate = calculateErrorRate();
    }

    public List<ScheduleEntity> getEntities() {
        return entities;
    }

    public int getErrorRate() {
        return errorRate;
    }

    private int calculateErrorRate() {
        int res = 0;

        for (ScheduleEntity entity1 : entities) {
            res += calculateErrorRate(entity1);
            for (ScheduleEntity entity2 : entities) {
                if (entity1 != entity2) {
                    res += calculateErrorRate(entity1, entity2);
                }
            }
        }

        return res;
    }

    private int calculateErrorRate(ScheduleEntity entity) {
        int res = 0;
        res += calculateErrorRateCorrectClassroom(entity);
        return res;
    }

    private int calculateErrorRate(ScheduleEntity entity1, ScheduleEntity entity2) {
        int res = 0;
        if (entitiesTheSameTime(entity1, entity2)) {
            res += calculateErrorRateSameClassrooms(entity1, entity2);
            res += calculateErrorRateSameTeachers(entity1, entity2);
            if (entitiesTheSameStudentsGroups(entity1, entity2)) {
                res += calculateErrorRateSameGroups(entity1, entity2);
            }
        }
        return res;
    }

    private boolean entitiesTheSameStudentsGroups(ScheduleEntity entity1, ScheduleEntity entity2) {
        StudentsGroup studentsGroup1 = entity1.getScheduleEntityBlock().getStudentsGroup();
        StudentsGroup studentsGroup2 = entity2.getScheduleEntityBlock().getStudentsGroup();
        return studentsGroup1.equals(studentsGroup2);
    }

    private boolean entitiesTheSameTime(ScheduleEntity entity1, ScheduleEntity entity2) {
        boolean daysEqual = entity1.getStudyDay().equals(entity2.getStudyDay());
        boolean lessonsEqual = entity1.getStudyLesson().equals(entity2.getStudyLesson());
        return daysEqual && lessonsEqual;
    }

    private int calculateErrorRateCorrectClassroom(ScheduleEntity entity) {
        return entity.getScheduleEntityBlock().isLecture() && !entity.getClassroom().isWide()
                ? 1
                : 0;
    }

    private int calculateErrorRateSameClassrooms(ScheduleEntity entity1, ScheduleEntity entity2) {
        Classroom classroom1 = entity1.getClassroom();
        Classroom classroom2 = entity2.getClassroom();
        return classroom1.equals(classroom2)
                ? 1
                : 0;
    }

    private int calculateErrorRateSameTeachers(ScheduleEntity entity1, ScheduleEntity entity2) {
        Teacher teacher1 = entity1.getScheduleEntityBlock().getTeacher();
        Teacher teacher2 = entity2.getScheduleEntityBlock().getTeacher();
        return teacher1.equals(teacher2)
                ? 1
                : 0;
    }

    private int calculateErrorRateSameGroups(ScheduleEntity entity1, ScheduleEntity entity2) {
        boolean isLecture1 = entity1.getScheduleEntityBlock().isLecture();
        boolean isLecture2 = entity2.getScheduleEntityBlock().isLecture();

        if (isLecture1 || isLecture2) {
            return 1;
        }

        Subject subject1 = entity1.getScheduleEntityBlock().getSubject();
        Subject subject2 = entity2.getScheduleEntityBlock().getSubject();
        boolean sameSubjects = subject1.equals(subject2);

        int group1 = entity1.getScheduleEntityBlock().getGroup();
        int group2 = entity2.getScheduleEntityBlock().getGroup();
        boolean sameGroups = group1 == group2;

        return (!sameSubjects || sameGroups)
                ? 1
                : 0;
    }

    @Override
    public String toString() {
        return entities
                .stream()
                .sorted(Comparator.comparing(a -> a.getStudyLesson().getName()))
                .sorted(Comparator.comparing(a -> a.getStudyDay().getName()))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
