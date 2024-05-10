package Model;
import java.sql.*;

public class DeleteTask {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }


    public boolean deleteTask(int taskId, String taskTitle) {
        String deleteQuery = "DELETE FROM Taches WHERE id = ? AND titre = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, taskId);
            pstmt.setString(2, taskTitle);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
            return false;
        }
    }
}
