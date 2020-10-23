package csp;

import csp.model.*;

import java.util.*;

public class CspAlgorithm {

    private final List<StudyDay> studyDays;
    private final List<StudyLesson> studyLessons;
    private final List<Classroom> classrooms;
    private final List<StudentsGroup> studentsGroups;
    private final List<ScheduleEntityBlock> scheduleEntityBlocks;

    public CspAlgorithm(List<StudyDay> studyDays, List<StudyLesson> studyLessons, List<Classroom> classrooms, List<StudentsGroup> studentsGroups) {
        this.studyDays = studyDays;
        this.studyLessons = studyLessons;
        this.classrooms = classrooms;
        this.studentsGroups = studentsGroups;

        this.scheduleEntityBlocks = calculateScheduleEntityBlocks();
    }

    public void start() {
        System.out.println("Starting");
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
}
