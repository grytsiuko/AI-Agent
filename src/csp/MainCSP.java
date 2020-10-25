package csp;

import csp.model.*;

import java.util.HashMap;
import java.util.List;

public class MainCSP {
    public static void main(String[] args) {
        example1();
        example2();
    }

    private static void example1(){
        System.out.println("---- Example 1 ----\n");
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
                        subjects.get(0), subjects.get(1), subjects.get(2), subjects.get(3)
                )),
                new StudentsGroup("SE-3", List.of(
                        subjects.get(0), subjects.get(1), subjects.get(2), subjects.get(3)
                ))
        );


        long start = System.nanoTime();
        CspAlgorithm cspAlgorithm = new CspAlgorithm(studyDays, studyLessons, classrooms, studentsGroups, true);
        cspAlgorithm.start();
        long end = System.nanoTime();
        System.out.println();
        cspAlgorithm.printLog();
        System.out.println("Spent time: " + (end - start / 1e+6) + "ms");
        System.out.println("\n\n");
    }

    private static void example2() {
        System.out.println("---- Example 2 ----\n");
        List<StudyDay> studyDays = List.of(
                new StudyDay("1.Monday   "),
                new StudyDay("2.Tuesday  "),
                new StudyDay("3.Wednesday")
        );

        List<StudyLesson> studyLessons = List.of(
//                new StudyLesson("08:30"),
                new StudyLesson("10:00")
        );

        List<Classroom> classrooms = List.of(
//                new Classroom(1, 225, true),
                new Classroom(1, 313, false),
                new Classroom(3, 220, false)
        );

        List<Teacher> teachers = List.of(
//                new Teacher("Hulayeva"),
//                new Teacher("Protsenko"),
//                new Teacher("Yushchenko"),
                new Teacher("Cherkasov"),
                new Teacher("Vozniuck"),
                new Teacher("Glybovets")
        );

        List<Subject> subjects = List.of(
                new Subject("DB", 0, 1, teachers.get(0), List.of(
                        new TeacherPractice(teachers.get(0), 1)
                )),
                new Subject("FP", 0, 1, teachers.get(0), List.of(
                        new TeacherPractice(teachers.get(0), 1)
                )),
                new Subject("Networks", 0, 2, teachers.get(2), List.of(
                        new TeacherPractice(teachers.get(2), 1)
                )),
                new Subject("IR", 0, 2, teachers.get(2), List.of(
                        new TeacherPractice(teachers.get(2), 1)
                ))
//                new Subject("MPA", 0, 1, teachers.get(4), List.of(
//                        new TeacherPractice(teachers.get(4), 1)
//                ))
        );

        List<StudentsGroup> studentsGroups = List.of(

                new StudentsGroup("CS-3", List.of(
                        subjects.get(0)
                )),
                new StudentsGroup("SE-3", List.of(
                        subjects.get(1)//, subjects.get(2)//, subjects.get(3)
                )),
                new StudentsGroup("AM-1", List.of(
                        subjects.get(3)
                ))
        );

        long start = System.nanoTime();
        CspAlgorithm cspAlgorithm = new CspAlgorithm(studyDays, studyLessons, classrooms, studentsGroups, false);
        cspAlgorithm.start();
        long end = System.nanoTime();
        System.out.println();
        cspAlgorithm.printLog();
        System.out.println("Spent time: " + (end - start / 1e+6) + "ms");
        System.out.println("\n\n");
    }
}
