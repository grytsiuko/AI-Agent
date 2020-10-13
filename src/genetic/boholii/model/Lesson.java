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

    public boolean sameStudent(Lesson that){
        return this.speciality.equals(that.speciality) &&
                (this.isLecture() || that.isLecture() ||
                        !this.title.equals(that.title) ||  this.group.equals(that.group));
    }

    @Override
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }
        if(object == null || object.getClass()!= this.getClass()) {
            return false;
        }
        Lesson that = (Lesson) object;
        return this.title.equals(that.title) &&
                this.teacher.equals(that.teacher) &&
                this.speciality.equals(that.speciality) &&
                this.isLecture() == that.isLecture() && (this.isLecture() || this.group.equals(that.group));
    }

}
