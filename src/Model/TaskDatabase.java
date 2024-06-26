package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class TaskDatabase {

    // Méthode pour trier les tâches par date d'échéance
    public List<Task> tasksSortedByDate() {
        List<Task> tasks = getAllTasks();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task2, Task task1) {
                return task2.getDueDate().compareTo(task1.getDueDate());
            }
        });
        return tasks;
    }

    // Méthode pour trier les tâches par priorité
    public List<Task> tasksSortedByPriority() {
        List<Task> tasks = getAllTasks();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task2, Task task1) {
                return task1.getPriority().compareTo(task2.getPriority());
            }
        });
        return tasks;
    }

    // Méthode pour récupérer toutes les tâches
    public List<Task> getAllTasks() {
        return getTasks("SELECT * FROM Taches");
    }

    // Méthode pour récupérer les tâches non terminées
    public List<Task> getUnfinishedTasks() {
        return getTasks("SELECT * FROM Taches WHERE estFini = false OR dateEcheance >= CURDATE()");
    }
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

    // Méthode de connexion à la base de données
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
