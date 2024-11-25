public class TeacherSubject {
    private int teacherId;
    private int subjectId;
    private int classesPerWeek;
    private int studentsPerClass;

    public TeacherSubject(int teacherId, int subjectId, int classesPerWeek, int studentsPerClass) {
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.classesPerWeek = classesPerWeek;
        this.studentsPerClass = studentsPerClass;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getClassesPerWeek() {
        return classesPerWeek;
    }

    public void setClassesPerWeek(int classesPerWeek) {
        this.classesPerWeek = classesPerWeek;
    }

    public int getStudentsPerClass() {
        return studentsPerClass;
    }

    public void setStudentsPerClass(int studentsPerClass) {
        this.studentsPerClass = studentsPerClass;
    }
}