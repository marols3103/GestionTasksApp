package Model;



import java.sql.*;

public class DeleteTask {

    // Obtenir une connexion à la base de données
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mydatabase"; // Votre URL de connexion
        String user = "root"; // Votre nom d'utilisateur
        String password = ""; // Votre mot de passe
        return DriverManager.getConnection(url, user, password);
    }

    // Méthode pour supprimer une tâche par ID et titre
    public boolean deleteTask(int taskId, String taskTitle) {
        String deleteQuery = "DELETE FROM Taches WHERE id = ? AND titre = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, taskId); // Premier paramètre pour l'ID
            pstmt.setString(2, taskTitle); // Deuxième paramètre pour le titre

            int affectedRows = pstmt.executeUpdate(); // Exécuter la suppression
            return affectedRows > 0; // Retourner `true` si la suppression a réussi

        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la tâche : " + e.getMessage());
            return false; // Retourner `false` si une erreur s'est produite
        }
    }
}
