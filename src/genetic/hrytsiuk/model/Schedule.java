package genetic.hrytsiuk.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private List<ScheduleEntity> entities;
    private int errorRate;

    public Schedule(List<ScheduleEntity> entities) {
        this.entities = entities;
        this.errorRate = calculateErrorRate();
    }

    private int calculateErrorRate() {
        int res = 0;

        for (ScheduleEntity entity1 : entities) {
            res += calculateErrorRate(entity1);
            for (ScheduleEntity entity2 : entities) {
                if (entity1 == entity2) {
                    continue;
                }
                res += calculateErrorRate(entity1, entity2);
            }
        }

        return res;
    }

    private int calculateErrorRate(ScheduleEntity entity) {
        int res = 0;
        if (entity.getScheduleEntityBlock().isLecture() && !entity.getClassroom().isWide()) {
            res += 1;
        }
        return res;
    }

    private int calculateErrorRate(ScheduleEntity entity1, ScheduleEntity entity2) {
        int res = 0;
        if (entity1.getStudyDay().equals(entity2.getStudyDay())) {
            if (entity1.getStudyLesson().equals(entity2.getStudyLesson())) {
                // if same time:
                // same classrooms
                if (entity1.getClassroom().equals(entity2.getClassroom())) {
                    res += 1;
                }
                // same teachers
                if (entity1.getScheduleEntityBlock().getTeacher().equals(entity2.getScheduleEntityBlock().getTeacher())) {
                    res += 1;
                }
                // same student groups
                if (entity1.getScheduleEntityBlock().getStudentsGroup().equals(entity2.getScheduleEntityBlock().getStudentsGroup())) {
                    if (entity1.getScheduleEntityBlock().isLecture() || entity2.getScheduleEntityBlock().isLecture()) {
                        res += 1;
                    } else if (!entity1.getScheduleEntityBlock().getSubject().equals(entity2.getScheduleEntityBlock().getSubject())){
                        res += 1;
                    } else if (entity1.getScheduleEntityBlock().getGroup().equals(entity2.getScheduleEntityBlock().getGroup())){
                        res += 1;
                    }
                }
            }
        }
        return res;
    }

    public List<ScheduleEntity> getEntities() {
        return entities;
    }

    public int getErrorRate() {
        return errorRate;
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
