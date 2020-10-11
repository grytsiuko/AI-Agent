package genetic.hrytsiuk;

import genetic.hrytsiuk.model.*;

import java.util.List;

public class MainHrytsiukGenetic {
    public static void main(String[] args) {

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
                new StudyLesson("15:00")
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

        Evolution evolution = new Evolution(studyDays, studyLessons, classrooms, studentsGroups);
        evolution.start();
    }
}
