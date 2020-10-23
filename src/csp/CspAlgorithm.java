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
            return Optional.of(convertStackToSchedule());
        }

        Optional<ScheduleEntityBlock> nextBlockOptional = getNextBlock();
        return nextBlockOptional.isEmpty()
                ? Optional.empty()
                : tryNextBlock(nextBlockOptional.get());
    }

    private Optional<Schedule> tryNextBlock(ScheduleEntityBlock nextBlock) {
        for (ScheduleEntityPlaceTime nextPlaceTime : getPossiblePlaceTimes(nextBlock)) {
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

    private void pushCspStep(ScheduleEntityBlock block, ScheduleEntityPlaceTime placeTime) {
        Set<ScheduleEntity> toDelete = new HashSet<>();

        // delete all placeTimes from given block
        pool.get(block)
                .stream()
                .map(currPlaceTime -> new ScheduleEntity(block, currPlaceTime))
                .forEach(toDelete::add);

        // delete given placeTime from all blocks
        scheduleEntityBlocks.forEach(
                b -> appendToDeleteIfExists(toDelete, b, placeTime)
        );

        // delete given time from all other teacher blocks
        scheduleEntityBlocks
                .stream()
                .filter(b -> b.getTeacher().equals(block.getTeacher()))
                .forEach(b -> appendToDeleteSameTime(toDelete, b, placeTime));

        // delete given time from all other student groups blocks
        scheduleEntityBlocks
                .stream()
                .filter(b -> b.getStudentsGroup().equals(block.getStudentsGroup()))
                .filter(b -> b.isLecture() || block.isLecture() || !b.getSubject().equals(block.getSubject()))
                .forEach(b -> appendToDeleteSameTime(toDelete, b, placeTime));

        toDelete.forEach(
                entity -> pool.get(entity.getScheduleEntityBlock()).remove(entity.getScheduleEntityPlaceTime())
        );

        CspStep cspStep = new CspStep(new ScheduleEntity(block, placeTime), toDelete);
        stack.push(cspStep);
    }

    private void appendToDeleteSameTime(Set<ScheduleEntity> toDelete, ScheduleEntityBlock b, ScheduleEntityPlaceTime placeTime) {
        pool.get(b)
                .stream()
                .filter(pt -> pt.getStudyLesson().equals(placeTime.getStudyLesson()))
                .filter(pt -> pt.getStudyDay().equals(placeTime.getStudyDay()))
                .forEach(pt -> appendToDeleteIfExists(toDelete, b, pt));
    }

    private void appendToDeleteIfExists(Set<ScheduleEntity> toDelete, ScheduleEntityBlock block, ScheduleEntityPlaceTime placeTime) {
        if (pool.get(block).contains(placeTime)) {
            toDelete.add(new ScheduleEntity(block, placeTime));
        }
    }

    private void popCspStep() {
        CspStep cspStep = stack.pop();

        cspStep.getDeletedScheduleEntities().forEach(
                entity -> pool.get(entity.getScheduleEntityBlock()).add(entity.getScheduleEntityPlaceTime())
        );
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
    private List<ScheduleEntityPlaceTime> getPossiblePlaceTimes(ScheduleEntityBlock scheduleEntityBlock) {
        Set<ScheduleEntityPlaceTime> placeTimeSet = pool.get(scheduleEntityBlock);
        return new ArrayList<>(placeTimeSet);
    }

    private Schedule convertStackToSchedule() {
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
            Set<ScheduleEntityPlaceTime> placeTimes = new HashSet<>();
            for (ScheduleEntityPlaceTime scheduleEntityPlaceTime : scheduleEntityPlaceTimes) {
                if (scheduleEntityBlock.isLecture() && !scheduleEntityPlaceTime.getClassroom().isWide()) {
                    continue;
                }
                placeTimes.add(scheduleEntityPlaceTime);
            }
            result.put(scheduleEntityBlock, placeTimes);
        }
        return result;
    }
}
