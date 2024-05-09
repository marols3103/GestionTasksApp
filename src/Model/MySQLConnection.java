package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    public static Connection getConnection() {

        try {
            // code permettant de charger le pilote JDBC MySQL

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.err.println("Pilote MySQL introuvable : " + e.getMessage());

            return null;
        }

        String baseUrl = "jdbc:mysql://localhost:3306/";

        String dbName = "mydatabase";

        String user = "root";

        String password = "";

        Connection conn = null;

        try {



            conn = DriverManager.getConnection(baseUrl, user, password);

            System.out.println("Connexion réussie!");


            try (Statement stmt = conn.createStatement()) {

                stmt.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
            }

            conn = DriverManager.getConnection(baseUrl + dbName, user, password);

            try (Statement stmt = conn.createStatement()) {

                // Table des utilisateurs
                String createTableUsersQuery = "CREATE TABLE IF NOT EXISTS Utilisateurs ("

                        + "id INT AUTO_INCREMENT PRIMARY KEY, "

                        + "name VARCHAR(40), "

                        + "firstName VARCHAR(40), "

                        + "email VARCHAR(50), "

                        + "password VARCHAR(30)"
                        + ")";

                // Table des tâches
                String createTableTasksQuery = "CREATE TABLE IF NOT EXISTS Taches ("

                        + "id INT AUTO_INCREMENT PRIMARY KEY, "

                        + "titre VARCHAR(100), "

                        + "description TEXT, "

                        + "dateEcheance DATE, "

                        + "priority VARCHAR(50)"

                        + ")";
                //Execution des requetes
                stmt.execute(createTableUsersQuery);

                stmt.execute(createTableTasksQuery);
            }

        } catch (SQLException e) {

            System.err.println("Erreur lors de la connexion à MySQL  ");
            e.printStackTrace();
            return null;
        }

        return conn;
    }
}
