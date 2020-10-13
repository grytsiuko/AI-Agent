package genetic.boholii.model;

public class Lesson {

    private String title;
    private String teacher;
    private String speciality;
    private String group;


    public Lesson(String title, String teacher, String speciality, String group){
        this(title, teacher, speciality);
        this.group = group;
    }

    public Lesson(String title, String teacher, String speciality){
        this.title = title;
        this.teacher = teacher;
        this.speciality = speciality;
    }

    public boolean isLecture(){
        return group == null;
    }

    public String getTitle() {
        return title;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return title + " " + teacher + " " + speciality + " " + (group == null ? "LECTURE" : group);
    }
}
