package csp.model;

import java.util.Set;

public class CspStep {
    ScheduleEntity scheduleEntity;
    private final Set<ScheduleEntity> deletedScheduleEntities;

    public CspStep(ScheduleEntity scheduleEntity, Set<ScheduleEntity> deletedScheduleEntities) {
        this.scheduleEntity = scheduleEntity;
        this.deletedScheduleEntities = deletedScheduleEntities;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public Set<ScheduleEntity> getDeletedScheduleEntities() {
        return deletedScheduleEntities;
    }
}
