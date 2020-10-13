package genetic.boholii.model;

import java.util.ArrayList;
import java.util.Comparator;

public class ScheduleRow {

    private Day day;
    private Time time;
    private Room room;
    private Lesson lesson;


    public ScheduleRow(Day day, Time time, Room room, Lesson lesson){
        this.day = day;
        this.time = time;
        this.room = room;
        this.lesson = lesson;

    }

    public Day getDay() {
        return day;
    }

    public Time getTime() {
        return time;
    }

    public Room getRoom() {
        return room;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public static String toString(ArrayList<ScheduleRow> rows){
        int biggestDayTitle = 0;
        int biggestRoomTitle = 0;

        for(ScheduleRow row : rows){
            if(row.getDay().toString().length() > biggestDayTitle){
                biggestDayTitle = row.getDay().toString().length();
            }
            if(row.getRoom().toString().length() > biggestRoomTitle){
                biggestRoomTitle = row.getRoom().toString().length();
            }
        }

        rows.sort((r1, r2) -> r1.getDay().toString().compareTo(r2.getDay().toString())*24*60 + r1.getTime().compareTo(r2.getTime()));

        StringBuilder result = new StringBuilder();
        for(ScheduleRow row : rows){
            String dayString = row.getDay().toString();
            String roomString = row.getRoom().toString();
            result.append(dayString).append(" ".repeat(biggestDayTitle - dayString.length())).append(" | ")
                    .append(row.getTime().toString()).append(" | ")
                    .append(roomString).append(" ".repeat(biggestRoomTitle - roomString.length())).append(" | ")
                    .append(row.getLesson().toString())
                    .append('\n');
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return  day.toString() + " | " + time.toString() + " | " + room.toString() + " | " + lesson.toString();
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || object.getClass()!= this.getClass()) {
            return false;
        }
        ScheduleRow that = (ScheduleRow) object;
        return this.day.equals(that.day) && this.time.equals(that.time)
                && this.room.equals(that.room) && this.lesson.equals(that.lesson);
    }
}
