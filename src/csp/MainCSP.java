package csp;

import csp.model.*;

import java.util.HashMap;
import java.util.List;

public class MainCSP {
    public static void main(String[] args) {
        example1(true);
        example2(true);
        example1(false);
        example2(false);
    }

    private static void example1(boolean withHeuristics) {
        String comment = withHeuristics ? "with all heuristics" : "just forward checking heuristic";
        System.out.println("---- Example 1 " + comment + " ----\n");
        List<StudyDay> studyDays = List.of(
                new StudyDay("1.Monday   "),
                new StudyDay("2.Tuesday  "),
                new StudyDay("3.Wednesday"),
                new StudyDay("4.Thursday "),
                new StudyDay("5.Friday   ")
        );

        List<StudyLesson> studyLessons = List.of(
                new StudyLesson("08:30"),
                new StudyLesson("10:00"),
                new StudyLesson("11:40"),
                new StudyLesson("13:30"),
                new StudyLesson("15:00"),
                new StudyLesson("16:30")
        );

        List<Classroom> classrooms = List.of(
                new Classroom(1, 225, true),
                new Classroom(1, 313, false),
                new Classroom(3,302, true),
                new Classroom(3, 220, false)
        );

        List<Teacher> teachers = List.of(
                new Teacher("Hulayeva"),
                new Teacher("Protsenko"),
                new Teacher("Yushchenko"),
                new Teacher("Cherkasov"),
                new Teacher("Vozniuck"),
                new Teacher("Glybovets")
        );

        List<Subject> subjects = List.of(
                new Subject("DB", 1, 1, teachers.get(0), List.of(
                        new TeacherPractice(teachers.get(0), 3), new TeacherPractice(teachers.get(2), 2)
                )),
                new Subject("FP", 2, 2, teachers.get(1), List.of(
                        new TeacherPractice(teachers.get(1), 1), new TeacherPractice(teachers.get(2), 4)
                )),
                new Subject("Networks", 1, 2, teachers.get(3), List.of(
                        new TeacherPractice(teachers.get(3), 1), new TeacherPractice(teachers.get(4), 2)
                )),
                new Subject("IR", 2, 1, teachers.get(5), List.of(
                        new TeacherPractice(teachers.get(4), 4)
                ))
        );

        List<StudentsGroup> studentsGroups = List.of(
                new StudentsGroup("CS-3", List.of(
                        subjects.get(0), subjects.get(1)
                )),
                new StudentsGroup("SE-3", List.of(
                        subjects.get(0), subjects.get(1)
                ))
        );


        long start = System.nanoTime();
        CspAlgorithm cspAlgorithm = new CspAlgorithm(studyDays, studyLessons, classrooms, studentsGroups, withHeuristics);
        cspAlgorithm.start();
        long end = System.nanoTime();
        System.out.println();
        cspAlgorithm.printLog();
        System.out.println("Spent time: " + ((end - start) / 1e+6) + "ms");
        System.out.println("\n\n");
    }

    private static void example2(boolean withHeuristics){
        String comment = withHeuristics ? "with all heuristics" : "just forward checking heuristic";
        System.out.println("---- Example 2 " + comment + " ----\n");
        List<StudyDay> studyDays = List.of(
//                new StudyDay("1.Monday   "),
//                new StudyDay("2.Tuesday  "),
//                new StudyDay("3.Wednesday"),
                new StudyDay("4.Thursday "),
                new StudyDay("5.Friday   ")
        );

        List<StudyLesson> studyLessons = List.of(
//                new StudyLesson("08:30"),
//                new StudyLesson("10:00"),
//                new StudyLesson("11:40"),
//                new StudyLesson("13:30"),
                new StudyLesson("15:00"),
                new StudyLesson("16:30")
        );

        List<Classroom> classrooms = List.of(
                new Classroom(1, 225, true),
                new Classroom(1, 313, false),
                new Classroom(3,302, true),
                new Classroom(3, 220, false)
        );

        List<Teacher> teachers = List.of(
                new Teacher("Hulayeva"),
                new Teacher("Protsenko"),
                new Teacher("Yushchenko"),
                new Teacher("Cherkasov"),
                new Teacher("Vozniuck"),
                new Teacher("Glybovets")
        );

        List<Subject> subjects = List.of(
                new Subject("DB", 1, 1, teachers.get(0), List.of(
                        new TeacherPractice(teachers.get(0), 3), new TeacherPractice(teachers.get(2), 2)
                )),
                new Subject("FP", 2, 2, teachers.get(1), List.of(
                        new TeacherPractice(teachers.get(1), 1), new TeacherPractice(teachers.get(2), 4)
                )),
                new Subject("Networks", 1, 2, teachers.get(3), List.of(
                        new TeacherPractice(teachers.get(3), 1), new TeacherPractice(teachers.get(4), 2)
                )),
                new Subject("IR", 2, 1, teachers.get(5), List.of(
                        new TeacherPractice(teachers.get(4), 4)
                ))
        );

        List<StudentsGroup> studentsGroups = List.of(
                new StudentsGroup("CS-3", List.of(
                        subjects.get(0), subjects.get(1)
                )),
                new StudentsGroup("SE-3", List.of(
                        subjects.get(0), subjects.get(1)
                ))
        );


        long start = System.nanoTime();
        CspAlgorithm cspAlgorithm = new CspAlgorithm(studyDays, studyLessons, classrooms, studentsGroups, withHeuristics);
        cspAlgorithm.start();
        long end = System.nanoTime();
        System.out.println();
        cspAlgorithm.printLog();
        System.out.println("Spent time: " + ((end - start) / 1e+6) + "ms");
        System.out.println("\n\n");
    }
}
