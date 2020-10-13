package genetic.boholii;

import genetic.boholii.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private final int SELECTION_POPULATION = 10;
    private final int OFFSPRING_AMOUNT = 100;

    private final int MAX_GENERATIONS = 100;

    private final int ROOM_SIZE_ERROR_COST = 1;
    private final int SAME_ROOM_ERROR_COST = 1;
    private final int TEACHER_ERROR_COST = 1;
    private final int STUDENT_ERROR_COST = 1;
    private final int NO_ERROR_COST = 0;

    private Random random = new Random();

    private ArrayList<Day>  days;
    private ArrayList<Time>   times;
    private ArrayList<Room>   rooms;
    private ArrayList<Lesson> lessons;

    GeneticAlgorithm(ArrayList<Day>  days, ArrayList<Time>   times, ArrayList<Room> rooms, ArrayList<Lesson> lessons){
        this.days = days;
        this.times = times;
        this.rooms = rooms;
        this.lessons = lessons;

    }

    public ArrayList<ScheduleRow> run(){
        ArrayList<ArrayList<ScheduleRow>> population = initPopulation();

        int generation = 1;
        while(true){
            population = selection(population);

            int bestCost = errorsCost(population.get(0));
            print(population.get(0), bestCost, generation);

            if(bestCost == 0 || generation == MAX_GENERATIONS) {
                return population.get(0);
            }

            population = mutate(population);
            ++generation;
        }
    }

    private void print(ArrayList<ScheduleRow> schedule, int cost, int generation){
        String scheduleStr = ScheduleRow.toString(schedule);
        System.out.println("Generation: " + generation +
                "\nBest schedule: \n" + scheduleStr +
                "\nErrors cost: " + cost +
                "\n-----------------------------------------\n");
    }

    private ArrayList<ArrayList<ScheduleRow>> mutate(ArrayList<ArrayList<ScheduleRow>> population){
        ArrayList<ArrayList<ScheduleRow>> result = new ArrayList<>();

        for(ArrayList<ScheduleRow> schedule : population) {
            for(int i = 0; i < OFFSPRING_AMOUNT; ++i){
                result.add(mutateEntity(schedule));
            }
        }
        return result;
    }

    private ArrayList<ScheduleRow> mutateEntity(ArrayList<ScheduleRow> schedule){
        ArrayList<ScheduleRow> scheduleCopy = new ArrayList<>(schedule);

        ScheduleRow row = scheduleCopy.remove(random.nextInt(scheduleCopy.size()));

        switch (random.nextInt(3)) {
            case 0:
                scheduleCopy.add(new ScheduleRow(getRandom(days), row.getTime(), row.getRoom(), row.getLesson()));
                break;
            case 1:
                scheduleCopy.add(new ScheduleRow(row.getDay(), getRandom(times), row.getRoom(), row.getLesson()));
                break;
            case 2:
                scheduleCopy.add(new ScheduleRow(row.getDay(), row.getTime(), getRandom(rooms), row.getLesson()));
                break;
        }
        return scheduleCopy;
    }

    private ArrayList<ArrayList<ScheduleRow>> selection(ArrayList<ArrayList<ScheduleRow>> population){
        ArrayList<Pair<ArrayList<ScheduleRow>, Integer>> costSchedule = new ArrayList<>();
        for(ArrayList<ScheduleRow> schedule : population){
            costSchedule.add(new Pair<>( schedule, errorsCost(schedule)));
        }
        costSchedule.sort(Comparator.comparingInt(Pair::getValue));

        ArrayList<ArrayList<ScheduleRow>> result = new ArrayList<>();
        for(int i = 0; i < SELECTION_POPULATION; ++i){
            result.add(costSchedule.get(i).getKey());
        }
        return result;
    }

    private ArrayList<ArrayList<ScheduleRow>> initPopulation(){
        ArrayList<ArrayList<ScheduleRow>> population = new ArrayList<>();
        for(int i = 0; i < OFFSPRING_AMOUNT; ++i) {
            ArrayList<ScheduleRow> schedule = new ArrayList<>();
            for (Lesson lesson : lessons) {
                schedule.add(new ScheduleRow(getRandom(days), getRandom(times), getRandom(rooms), lesson));
            }
            population.add(schedule);
        }
        return population;
    }

    private int errorsCost(ArrayList<ScheduleRow> schedule){
        int cost = NO_ERROR_COST;
        for(ScheduleRow row : schedule){
            cost += roomSizeCost(row);
            cost += sameRoomsCost(row, schedule);
            cost += teacherCost(row, schedule);
            cost += studentCost(row, schedule);
        }
        return cost;
    }

    private int studentCost(ScheduleRow row, ArrayList<ScheduleRow> schedule){
        int cost = NO_ERROR_COST;
        for(ScheduleRow scheduleRow : schedule){
            boolean sameDay = row.getDay().equals(scheduleRow.getDay());
            boolean sameTime = row.getTime().equals(scheduleRow.getTime());
            boolean diffLess = !row.getLesson().equals(scheduleRow.getLesson());
            boolean sameStudent = row.getLesson().sameStudent(scheduleRow.getLesson());
            if(sameDay && sameTime && diffLess && sameStudent){
                cost += STUDENT_ERROR_COST;
            }
        }
        return cost;
    }

    private int teacherCost(ScheduleRow row, ArrayList<ScheduleRow> schedule){
        int cost = NO_ERROR_COST;
        for(ScheduleRow scheduleRow : schedule){
            boolean sameDay = row.getDay().equals(scheduleRow.getDay());
            boolean sameTime = row.getTime().equals(scheduleRow.getTime());
            boolean diffLess = !row.getLesson().equals(scheduleRow.getLesson());
            boolean sameTeacher = row.getLesson().getTeacher().equals(scheduleRow.getLesson().getTeacher());
            if(sameDay && sameTime && diffLess && sameTeacher){
                cost += TEACHER_ERROR_COST;
            }
        }
        return cost;
    }

    private int sameRoomsCost(ScheduleRow row, ArrayList<ScheduleRow> schedule){
        int cost = NO_ERROR_COST;
        for(ScheduleRow scheduleRow : schedule){
            boolean sameDay = row.getDay().equals(scheduleRow.getDay());
            boolean sameTime = row.getTime().equals(scheduleRow.getTime());
            boolean diffLess = !row.getLesson().equals(scheduleRow.getLesson());
            boolean sameRoom = row.getRoom().equals(scheduleRow.getRoom());
            if(sameDay && sameTime && diffLess && sameRoom){
                cost += SAME_ROOM_ERROR_COST;
            }
        }
        return cost;
    }

    private int roomSizeCost(ScheduleRow row){
        if(row.getRoom().isForLecture() || !row.getLesson().isLecture()){
            return NO_ERROR_COST;
        }
        return ROOM_SIZE_ERROR_COST;
    }


    //developed by Sanya
    public <E> E getRandom(List<E> list) {
        return list.get(random.nextInt(list.size()));
    }

}
