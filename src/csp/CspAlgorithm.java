package csp;

import csp.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class CspAlgorithm {

    private final List<StudyDay> studyDays;
    private final List<StudyLesson> studyLessons;
    private final List<Classroom> classrooms;
    private final List<StudentsGroup> studentsGroups;
    private final List<ScheduleEntityBlock> scheduleEntityBlocks;
    private final List<ScheduleEntityPlaceTime> scheduleEntityPlaceTimes;
    private final Map<ScheduleEntityBlock, Set<ScheduleEntityPlaceTime>> pool;
    private final Stack<CspStep> stack;

    public CspAlgorithm(List<StudyDay> studyDays, List<StudyLesson> studyLessons, List<Classroom> classrooms, List<StudentsGroup> studentsGroups) {
        this.studyDays = studyDays;
        this.studyLessons = studyLessons;
        this.classrooms = classrooms;
        this.studentsGroups = studentsGroups;

        this.scheduleEntityBlocks = calculateScheduleEntityBlocks();
        this.scheduleEntityPlaceTimes = calculateScheduleEntityPlaceTimes();
        this.pool = calculatePool();
        this.stack = new Stack<>();
    }

    public void start() {
        Optional<Schedule> scheduleOptional = calculateSchedule();
        scheduleOptional.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("No possible schedule")
        );
    }

    private Optional<Schedule> calculateSchedule() {
        if (stack.size() == scheduleEntityBlocks.size()) {
            return Optional.of(getScheduleFromStack());
        }

        Optional<ScheduleEntityBlock> nextBlockOptional = getNextBlock();
        return nextBlockOptional.isEmpty()
                ? Optional.empty()
                : tryNextBlock(nextBlockOptional.get());
    }

    private Optional<Schedule> tryNextBlock(ScheduleEntityBlock nextBlock) {
        for (ScheduleEntityPlaceTime nextPlaceTime : getNextPlaceTime(nextBlock)) {
            pushCspStep(nextBlock, nextPlaceTime);
            Optional<Schedule> result = calculateSchedule();
            if (result.isPresent()) {
                return result;
            } else {
                popCspStep();
            }
        }
        return Optional.empty();
    }

    // TODO
    private void pushCspStep(ScheduleEntityBlock block, ScheduleEntityPlaceTime placeTime) {
        List<ScheduleEntity> deletedFromPool = new ArrayList<>();

        pool.get(block)
                .stream()
                .map(pt -> new ScheduleEntity(block, pt))
                .forEach(deletedFromPool::add);
        pool.get(block).clear();

        CspStep cspStep = new CspStep(new ScheduleEntity(block, placeTime), deletedFromPool);
        stack.push(cspStep);
        // clean pool
    }

    // TODO
    private void popCspStep() {
        CspStep cspStep = stack.pop();

        cspStep.getDeletedScheduleEntities()
                .forEach(entity -> pool.get(entity.getScheduleEntityBlock()).add(entity.getScheduleEntityPlaceTime()));
        // restore pool
    }

    // TODO heuristic
    private Optional<ScheduleEntityBlock> getNextBlock() {
        for (ScheduleEntityBlock scheduleEntityBlock : scheduleEntityBlocks) {
            if (pool.get(scheduleEntityBlock).isEmpty()) {
                continue;
            }
            return Optional.of(scheduleEntityBlock);
        }
        return Optional.empty();
    }

    // TODO heuristic
    private List<ScheduleEntityPlaceTime> getNextPlaceTime(ScheduleEntityBlock scheduleEntityBlock) {
        Set<ScheduleEntityPlaceTime> placeTimeSet = pool.get(scheduleEntityBlock);
        return new ArrayList<>(placeTimeSet);
    }

    private Schedule getScheduleFromStack() {
        List<ScheduleEntity> scheduleEntities = stack
                .stream()
                .map(CspStep::getScheduleEntity)
                .collect(Collectors.toList());
        return new Schedule(scheduleEntities);
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

    private List<ScheduleEntityPlaceTime> calculateScheduleEntityPlaceTimes() {
        List<ScheduleEntityPlaceTime> result = new ArrayList<>();
        for (StudyDay studyDay : studyDays) {
            for (StudyLesson studyLesson : studyLessons) {
                for (Classroom classroom : classrooms) {
                    result.add(new ScheduleEntityPlaceTime(classroom, studyDay, studyLesson));
                }
            }
        }
        return result;
    }

    private Map<ScheduleEntityBlock, Set<ScheduleEntityPlaceTime>> calculatePool() {
        Map<ScheduleEntityBlock, Set<ScheduleEntityPlaceTime>> result = new HashMap<>();
        for (ScheduleEntityBlock scheduleEntityBlock : scheduleEntityBlocks) {
            Set<ScheduleEntityPlaceTime> placeTimes = new HashSet<>(scheduleEntityPlaceTimes);
            result.put(scheduleEntityBlock, placeTimes);
        }
        return result;
    }
}
