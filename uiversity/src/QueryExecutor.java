import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {
    public List<Teacher> getTeachersByDayAndClassroom(String day, String classroom) {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT t.* FROM Teacher t " +
                "JOIN Teacher_Subjects ts ON t.id = ts.teacher_id " +
                "JOIN Subjects s ON ts.subject_id = s.id " +
                "WHERE s.day_of_week = ? AND s.classroom = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, day);
            statement.setString(2, classroom);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                teachers.add(new Teacher(id, fullName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public List<Teacher> getTeachersNotWorkingOnDay(String day) {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT t.* FROM Teacher t " +
                "WHERE NOT EXISTS (" +
                "SELECT 1 FROM Teacher_Subjects ts " +
                "JOIN Subjects s ON ts.subject_id = s.id " +
                "WHERE ts.teacher_id = t.id AND s.day_of_week = ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, day);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullName = resultSet.getString("full_name");
                teachers.add(new Teacher(id, fullName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public List<String> getDaysWithSubjectCount(int count) {
        List<String> days = new ArrayList<>();
        String sql = "SELECT day_of_week, COUNT(*) AS subject_count " +
                "FROM Subjects " +
                "GROUP BY day_of_week " +
                "HAVING subject_count = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String day = resultSet.getString("day_of_week");
                days.add(day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }

    public List<String> getDaysWithClassroomCount(int count) {
        List<String> days = new ArrayList<>();
        String sql = "SELECT day_of_week, COUNT(DISTINCT classroom) AS classroom_count " +
                "FROM Subjects " +
                "GROUP BY day_of_week " +
                "HAVING classroom_count = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, count);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String day = resultSet.getString("day_of_week");
                days.add(day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return days;
    }
    public void moveFirstClassToEnd(String currentDay, String futureDay) {
        String selectSql = "SELECT id FROM Subjects WHERE day_of_week = ? ORDER BY id";
        String updateSql = "UPDATE Subjects SET day_of_week = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {

            selectStatement.setString(1, currentDay);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                updateStatement.setString(1, futureDay);
                updateStatement.setInt(2, id);
                int rowsAffected = updateStatement.executeUpdate();

                System.out.println(rowsAffected + " class for " + currentDay + " moved to " + futureDay);
            } else {
                System.out.println("No classes found for day: " + currentDay);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}