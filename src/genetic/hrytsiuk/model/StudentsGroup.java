package genetic.hrytsiuk.model;

import java.util.List;

public class StudentsGroup {

    private String program;
    private List<Subject> subjects;

    public StudentsGroup(String program, List<Subject> subjects) {
        this.program = program;
        this.subjects = subjects;
    }

    public String getProgram() {
        return program;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        StudentsGroup studentsGroup = (StudentsGroup) obj;
        return studentsGroup.program.equals(this.program);
    }
}
