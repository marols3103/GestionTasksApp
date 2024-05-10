package Model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginVerification {
    private static final Logger logger = Logger.getLogger(LoginVerification.class.getName());

    public boolean isValidUser(String email, String password) {

        String jdbcUrl = "jdbc:mysql://localhost:3306/mydatabase";

        String jdbcUser = "root";

        String jdbcPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {

            String query = "SELECT COUNT(*) FROM Utilisateurs WHERE email = ? AND password = ?";


            try (PreparedStatement pstmt = connection.prepareStatement(query)) {

                pstmt.setString(1, email);

                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next() && rs.getInt(1) > 0) {

                        return true;
                    }
                }
            }
        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erreur lors de la connexion à la base de données", e);
        }

        return false;
    }
}
