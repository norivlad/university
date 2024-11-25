public class Subject {
    private int id;
    private String name;
    private String dayOfWeek;
    private String classroom;

    public Subject(int id, String name, String dayOfWeek, String classroom) {
        this.id = id;
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}