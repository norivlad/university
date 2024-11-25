import java.util.List;

public class Main {
    public static void main(String[] args) {
        QueryExecutor executor = new QueryExecutor();

        System.out.println("Getting teachers by day and classroom:");
        List<Teacher> teachers = executor.getTeachersByDayAndClassroom("Monday", "608");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getFullName());
        }

        System.out.println("Getting teachers not working on Monday:");
        teachers = executor.getTeachersNotWorkingOnDay("Monday");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getFullName());
        }

        System.out.println("Getting days with classroom count:");
        List<String> days = executor.getDaysWithClassroomCount(3);
        for (String day : days) {
            System.out.println(day);
        }

        System.out.println("Getting days with subject count:");
        days = executor.getDaysWithSubjectCount(4);
        for (String day : days) {
            System.out.println(day);
        }

        System.out.println("Moving the first class to the end:");
        executor.moveFirstClassToEnd("Monday", "Saturday");
    }
}