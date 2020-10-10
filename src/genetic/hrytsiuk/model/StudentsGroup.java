package genetic.hrytsiuk.model;

import java.util.List;

public class StudentsGroup {

    private String program;
    private int year;
    private List<Subject> subjects;

    public String getProgram() {
        return program;
    }

    public int getYear() {
        return year;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }
}
