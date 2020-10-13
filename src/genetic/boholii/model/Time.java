package genetic.boholii.model;

import genetic.hrytsiuk.model.Classroom;

public class Time implements Comparable<Time>{
    private int hours;
    private int minutes;

    public Time(int hours, int minutes){
        this.hours = checkHours(hours);
        this.minutes = checkMinutes(minutes);
    }

    private int checkHours(int hours){
        if(hours > 23){
           hours = 23;
        } else if(hours < 0){
            hours = 0;
        }
        return hours;
    }

    private int checkMinutes(int minutes){
        if(minutes > 59){
            minutes = 59;
        } else if(minutes < 0){
            minutes = 0;
        }
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return (hours < 10 ? "0" + hours : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || object.getClass()!= this.getClass()) {
            return false;
        }
        Time that = (Time) object;
        return this.hours == that.hours && this.minutes == that.minutes;
    }


    @Override
    public int compareTo(Time o) {
        return (this.hours - o.hours)*60 + this.minutes - o.minutes;
    }
}
