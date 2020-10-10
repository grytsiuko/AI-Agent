package genetic.hrytsiuk;

import genetic.hrytsiuk.model.*;

import java.util.List;

public class MainHrytsiukGenetic {
    public static void main(String[] args) {

        List<StudyDay> studyDays = List.of(
                new StudyDay("Monday"),
                new StudyDay("Tuesday"),
                new StudyDay("Wednesday"),
                new StudyDay("Thursday"),
                new StudyDay("Friday")
        );

        List<StudyLesson> studyLessons = List.of(
                new StudyLesson("8:30"),
                new StudyLesson("10:00"),
                new StudyLesson("11:40")
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
                new Teacher("Yushchenko")
        );

        List<Subject> subjects = List.of(
                new Subject("DB", 1, 1, 5, teachers.get(0), List.of(
                        new TeacherPractice(teachers.get(0), 3), new TeacherPractice(teachers.get(2), 2)
                )),
                new Subject("FP", 1, 1, 5, teachers.get(1), List.of(
                        new TeacherPractice(teachers.get(1), 1), new TeacherPractice(teachers.get(2), 4)
                ))
        );

        List<StudentsGroup> studentsGroups = List.of(
                new StudentsGroup("CS-2", List.of(subjects.get(0), subjects.get(1)))
        );

        Evolution evolution = new Evolution(studyDays, studyLessons, classrooms, teachers, subjects, studentsGroups);
        evolution.start();
    }
}
