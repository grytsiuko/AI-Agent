package genetic.hrytsiuk;

import genetic.hrytsiuk.model.*;

import java.util.ArrayList;
import java.util.List;

public class Evolution {

    private static final int MAX_INDIVIDUALS_AMOUNT = 1;
    private static final int MAX_GENERATIONS_AMOUNT = 10;

    private List<StudyDay> studyDays;
    private List<StudyLesson> studyLessons;
    private List<Classroom> classrooms;
    private List<Teacher> teachers;
    private List<Subject> subjects;
    private List<StudentsGroup> studentsGroups;
    private int lessonsAmount;
    private boolean finished;

    public Evolution(List<StudyDay> studyDays, List<StudyLesson> studyLessons, List<Classroom> classrooms, List<Teacher> teachers, List<Subject> subjects, List<StudentsGroup> studentsGroups) {
        this.studyDays = studyDays;
        this.studyLessons = studyLessons;
        this.classrooms = classrooms;
        this.teachers = teachers;
        this.subjects = subjects;
        this.studentsGroups = studentsGroups;
        this.lessonsAmount = calculateLessonsAmount();
        this.finished = false;
    }

    public void start() {
        int generationNumber = 0;
        List<Schedule> generation = getInitGeneration();
        showGeneration(0, generation);

        while (!finished && generationNumber < MAX_GENERATIONS_AMOUNT) {
            generationNumber++;
            generation = nextGeneration(generation);
            showGeneration(generationNumber, generation);
        }
    }

    private int calculateLessonsAmount() {
        return studentsGroups
                .stream()
                .flatMap(studentsGroup -> studentsGroup.getSubjects().stream())
                .map(subject -> subject.getLectures() + subject.getGroups() * subject.getPractices())
                .mapToInt(Integer::intValue)
                .sum();
    }

    // TODO
    private void showGeneration(int number, List<Schedule> generation) {

    }

    // TODO
    private List<Schedule> getInitGeneration() {
        List<Schedule> schedules = new ArrayList<>(MAX_INDIVIDUALS_AMOUNT);
        return schedules;
    }

    // TODO
    private List<Schedule> nextGeneration(List<Schedule> generation) {
        return generation;
    }
}
