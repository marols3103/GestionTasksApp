package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDatabase {


    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";

        String user = "root";

        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    public List<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM Taches WHERE estFini =false";

        try (Connection conn = getConnection();

             Statement stmt = conn.createStatement();

             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {

                int id = rs.getInt("id");

                String titre = rs.getString("titre");

                String description = rs.getString("description");

                Date dateEcheance = rs.getDate("dateEcheance");

                String priority = rs.getString("priority");

                boolean estFini = rs.getBoolean("estFini");

                Task task = new Task(id, titre, description,dateEcheance,priority,estFini);

                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }


    public static class Task {

        private int id;

        private String titre;

        private String description;

        private Date dateEcheance;

        private String priority;

        private boolean isFinished;


        public Task(int id, String titre, String description, Date dateEcheance, String priority, boolean isFinished) {

            this.id = id;

            this.titre = titre;

            this.description = description;

            this.dateEcheance = dateEcheance;

            this.priority = priority;

            this.isFinished = isFinished;
        }


        public Date getDueDate() {

            return dateEcheance;
        }

        public String getPriority() {

            return priority;
        }

        public boolean isFinished() {

            return isFinished;
        }

        // Getters existants
        public int getId() {

            return id;
        }

        public String getName() {

            return titre;

        }

        public String getDescription() {

            return description;
        }
    }

}
