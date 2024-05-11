package Model;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllTasks {


    public Connection getConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";

        String user = "root";

        String password = "";

        return DriverManager.getConnection(url, user, password);
    }

    // Méthode pour récupérer toutes les tâches
    public List<Task> getAllTasks() {
        return getTasks("SELECT * FROM Taches");
    }
/*
    // Méthode pour récupérer les tâches non terminées
    public List<Task> getUnfinishedTasks() {
        return getTasks("SELECT * FROM Taches");
    }
*/
    // Méthode générique pour récupérer les tâches en fonction d'une requête SQL donnée
    private List<Task> getTasks(String query) {
        List<Task> tasks = new ArrayList<>();

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

                Task task = new Task(id, titre, description, dateEcheance, priority, estFini);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    // Classe Task interne
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

        // Getters existants
        public Date getDueDate() {
            return dateEcheance;
        }

        public String getPriority() {
            return priority;
        }

        public boolean isFinished() {
            return isFinished;
        }

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
