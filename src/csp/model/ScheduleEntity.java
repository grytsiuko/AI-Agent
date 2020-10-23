package csp.model;

public class ScheduleEntity {

    private final ScheduleEntityBlock scheduleEntityBlock;
    private final ScheduleEntityPlaceTime scheduleEntityPlaceTime;

    public ScheduleEntity(ScheduleEntityBlock scheduleEntityBlock, ScheduleEntityPlaceTime scheduleEntityPlaceTime) {
        this.scheduleEntityBlock = scheduleEntityBlock;
        this.scheduleEntityPlaceTime = scheduleEntityPlaceTime;
    }

    public ScheduleEntityBlock getScheduleEntityBlock() {
        return scheduleEntityBlock;
    }

    public ScheduleEntityPlaceTime getScheduleEntityPlaceTime() {
        return scheduleEntityPlaceTime;
    }

    @Override
    public String toString() {
        return scheduleEntityPlaceTime + ":\t" + scheduleEntityBlock;
    }
}
