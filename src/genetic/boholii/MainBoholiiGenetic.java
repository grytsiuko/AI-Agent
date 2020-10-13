package genetic.boholii;

import genetic.boholii.model.*;

import java.util.ArrayList;
import java.util.List;

public class MainBoholiiGenetic {
    public static void main(String[] args) {

        ArrayList<Day> days = new ArrayList<>();
        days.add(new Day("1.Monday"));
        days.add(new Day("2.Tuesday"));
        days.add(new Day("3.Wednesday"));
        days.add(new Day("4.Thursday"));
//        days.add(new Day("5.Friday"));


        ArrayList<Time> times = new ArrayList<>();
        times.add(new Time(8, 30));
        times.add(new Time(10, 0));
        times.add(new Time(11, 40));
        times.add(new Time(13, 30));

        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("1", true));
        rooms.add(new Room("11",false));
        rooms.add(new Room("111", false));
        rooms.add(new Room("2", true));
        rooms.add(new Room("22", false));
        rooms.add(new Room("222", false));

        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("FP", "Brown", "CS-1"));
        lessons.add(new Lesson("FP", "Brown", "CS-1", "G1"));
        lessons.add(new Lesson("FP", "Jones", "CS-1", "G2"));
        lessons.add(new Lesson("FP", "Jones", "CS-1", "G3"));

        lessons.add(new Lesson("Math", "Smith", "CS-1"));
        lessons.add(new Lesson("Math", "Smith", "CS-1", "G1"));
        lessons.add(new Lesson("Math", "Smith", "CS-1", "G2"));

        lessons.add(new Lesson("MOOP", "Johnson", "CS-3"));
        lessons.add(new Lesson("MOOP", "Johnson", "CS-3", "G1"));
        lessons.add(new Lesson("MOOP", "Johnson", "CS-3", "G2"));
        lessons.add(new Lesson("MOOP", "Johnson", "CS-3", "G3"));

        lessons.add(new Lesson("OOP", "Johnson", "CS-1"));
        lessons.add(new Lesson("OOP", "Johnson", "CS-1", "G1"));
        lessons.add(new Lesson("OOP", "Miller", "CS-1", "G2"));
        lessons.add(new Lesson("OOP", "Miller", "CS-1", "G3"));
    }
}


