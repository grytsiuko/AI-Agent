package csp.model;

public class CspStep {
    ScheduleEntity scheduleEntity;

    public CspStep(ScheduleEntity scheduleEntity) {
        this.scheduleEntity = scheduleEntity;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }
}
