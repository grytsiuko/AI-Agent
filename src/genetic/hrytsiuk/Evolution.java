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
    private List<ScheduleEntityBlock> scheduleEntityBlocks;
    private Schedule result;

    public Evolution(List<StudyDay> studyDays, List<StudyLesson> studyLessons, List<Classroom> classrooms, List<Teacher> teachers, List<Subject> subjects, List<StudentsGroup> studentsGroups) {
        this.studyDays = studyDays;
        this.studyLessons = studyLessons;
        this.classrooms = classrooms;
        this.teachers = teachers;
        this.subjects = subjects;
        this.studentsGroups = studentsGroups;
        this.result = null;
        this.scheduleEntityBlocks = calculateScheduleEntityBlocks();
    }

    public void start() {
        int generationNumber = 0;
        List<Schedule> generation = getInitGeneration();
        showGeneration(0, generation);

        while (result == null && generationNumber < MAX_GENERATIONS_AMOUNT) {
            generationNumber++;
            generation = nextGeneration(generation);
            showGeneration(generationNumber, generation);
        }
    }

    private List<ScheduleEntityBlock> calculateScheduleEntityBlocks() {
        List<ScheduleEntityBlock> result = new ArrayList<>();
        for (StudentsGroup studentsGroup: studentsGroups) {
            for (Subject subject : studentsGroup.getSubjects()) {
                for (int i = 0; i < subject.getLectures(); i++) {
                    result.add(new ScheduleEntityBlock(studentsGroup, subject, subject.getLectureTeacher(), true, null));
                }
                int currGroup = 0;
                for (TeacherPractice teacherPractice : subject.getPracticeTeachers()) {
                    for (int i = 0; i < teacherPractice.getGroups(); i++) {
                        currGroup++;
                        result.add(new ScheduleEntityBlock(studentsGroup, subject, teacherPractice.getTeacher(), false, currGroup));
                    }
                }
            }
        }
        return result;
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
