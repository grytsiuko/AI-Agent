package csp.model;

import java.util.List;

public class CspStep {
    ScheduleEntity scheduleEntity;
    private final List<ScheduleEntity> deletedScheduleEntities;

    public CspStep(ScheduleEntity scheduleEntity, List<ScheduleEntity> deletedScheduleEntities) {
        this.scheduleEntity = scheduleEntity;
        this.deletedScheduleEntities = deletedScheduleEntities;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public List<ScheduleEntity> getDeletedScheduleEntities() {
        return deletedScheduleEntities;
    }
}
