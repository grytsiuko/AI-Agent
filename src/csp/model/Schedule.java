package csp.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {

    private final List<ScheduleEntity> entities;

    public Schedule(List<ScheduleEntity> entities) {
        this.entities = entities;
    }

    public List<ScheduleEntity> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return entities
                .stream()
                .sorted(Comparator.comparing(a -> a.getScheduleEntityPlaceTime().getStudyLesson().getName()))
                .sorted(Comparator.comparing(a -> a.getScheduleEntityPlaceTime().getStudyDay().getName()))
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}
