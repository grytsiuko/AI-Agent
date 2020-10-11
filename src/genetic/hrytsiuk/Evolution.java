package genetic.hrytsiuk;

import genetic.hrytsiuk.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Evolution {

    private static final int INDIVIDUALS_AMOUNT = 100;
    private static final int ANCESTORS_AMOUNT = 100;
    private static final int GENERATIONS_AMOUNT = 60;

    private final List<StudyDay> studyDays;
    private final List<StudyLesson> studyLessons;
    private final List<Classroom> classrooms;
    private final List<StudentsGroup> studentsGroups;
    private final List<ScheduleEntityBlock> scheduleEntityBlocks;

    private Schedule result;

    public Evolution(List<StudyDay> studyDays, List<StudyLesson> studyLessons, List<Classroom> classrooms, List<StudentsGroup> studentsGroups) {
        this.studyDays = studyDays;
        this.studyLessons = studyLessons;
        this.classrooms = classrooms;
        this.studentsGroups = studentsGroups;

        this.result = null;
        this.scheduleEntityBlocks = calculateScheduleEntityBlocks();
    }

    public void start() {
        int generationNumber = 1;
        List<Schedule> generation = getInitGeneration();
        showGeneration(generationNumber, generation);
        checkResult(generation);

        while (result == null && generationNumber <= GENERATIONS_AMOUNT) {
            generationNumber++;
            generation = nextGeneration(generation);
            showGeneration(generationNumber, generation);
            checkResult(generation);
        }

        if (result == null) {
            System.out.println("RESULT NOT FOUND");
        } else {
            System.out.println(result);
            System.out.println();
            System.out.println("RESULT FOUND ON " + generationNumber + " GENERATION");
        }
    }

    private List<ScheduleEntityBlock> calculateScheduleEntityBlocks() {
        List<ScheduleEntityBlock> result = new ArrayList<>();
        for (StudentsGroup studentsGroup : studentsGroups) {
            for (Subject subject : studentsGroup.getSubjects()) {
                for (int i = 0; i < subject.getLectures(); i++) {
                    result.add(new ScheduleEntityBlock(studentsGroup, subject, subject.getLectureTeacher(), true, null));
                }
                int currGroup = 0;
                for (TeacherPractice teacherPractice : subject.getPracticeTeachers()) {
                    for (int i = 0; i < teacherPractice.getGroups(); i++) {
                        currGroup++;
                        for (int k = 0; k < subject.getPractices(); k++) {
                            result.add(new ScheduleEntityBlock(studentsGroup, subject, teacherPractice.getTeacher(), false, currGroup));
                        }
                    }
                }
            }
        }
        return result;
    }

    private void showGeneration(int number, List<Schedule> generation) {
        System.out.println("********************** GENERATION " + number + " **********************");
        System.out.println();
        for (Schedule schedule : generation) {
            System.out.println();
            System.out.println("------------------");
            System.out.println(schedule);
            System.out.println("------------------");
            System.out.println("Error rate: " + schedule.getErrorRate());
            System.out.println("------------------");
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private List<Schedule> getInitGeneration() {
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < INDIVIDUALS_AMOUNT; i++) {
            schedules.add(getRandomGeneration());
        }
        return schedules;
    }

    private Schedule getRandomGeneration() {
        return new Schedule(
                scheduleEntityBlocks.stream()
                        .map(block -> new ScheduleEntity(
                                block, getRandom(classrooms), getRandom(studyDays), getRandom(studyLessons))
                        )
                        .collect(Collectors.toList())
        );
    }

    public <E> E getRandom(List<E> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private List<Schedule> nextGeneration(List<Schedule> generation) {
        return generation
                .stream()
                .flatMap(schedule -> getOffsprings(schedule).stream())
                .sorted(Comparator.comparingInt(Schedule::getErrorRate))
                .limit(INDIVIDUALS_AMOUNT)
                .collect(Collectors.toList());
    }

    private void checkResult(List<Schedule> generation) {
        Optional<Schedule> winner = generation
                .stream()
                .filter(schedule -> schedule.getErrorRate() == 0)
                .findFirst();
        winner.ifPresent(schedule -> result = schedule);
    }

    private List<Schedule> getOffsprings(Schedule schedule) {
        List<Schedule> res = new ArrayList<>();
        for (int i = 0; i < ANCESTORS_AMOUNT; i++) {
            List<ScheduleEntity> entities = new ArrayList<>(schedule.getEntities());
            mutateRandom(entities);
            res.add(new Schedule(entities));
        }
        return res;
    }

    public void mutateRandom(List<ScheduleEntity> entities) {
        Random rand = new Random();
        int i = rand.nextInt(entities.size());
        ScheduleEntity entity = entities.get(i);
        ScheduleEntity newEntity = new ScheduleEntity(entity.getScheduleEntityBlock(), getRandom(classrooms), getRandom(studyDays), getRandom(studyLessons));
        entities.set(i, newEntity);
    }
}
