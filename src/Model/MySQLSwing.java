
package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MySQLSwing{
    public static void main(String[] args) {
        // Créer l'interface Swing
        JFrame frame = new JFrame("Exemple Swing MySQL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JButton button = new JButton("Récupérer les données");
        frame.getContentPane().add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = MySQLConnection.getConnection()) {
                    if (conn != null) {
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery("SELECT * FROM test")) {

                            StringBuilder results = new StringBuilder();
                            while (rs.next()) {
                                results.append("Donnée : ")
                                        .append(rs.getInt("id"))
                                        .append(rs.getString("name"))
                                        .append(rs.getString("firstName"))
                                        .append(rs.getInt("age"))
                                        .append("\n");
                            }

                            // Afficher les résultats dans une boîte de dialogue
                            JOptionPane.showMessageDialog(frame, results.toString(),
                                    "Résultats de la base de données",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Impossible de se connecter à la base de données.",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erreur lors de l'accès à la base de données : " + ex.getMessage(),
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}







