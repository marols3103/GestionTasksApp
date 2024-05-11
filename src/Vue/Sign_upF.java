package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.*;

public class Sign_upF {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public Sign_upF(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createFormulaireSignUp() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        JTextField champName = new JTextField();

        JTextField champFirstName = new JTextField();

        JTextField champEmail = new JTextField();

        JPasswordField champPassword = new JPasswordField();

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(champName);

        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(champFirstName);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(champEmail);

        formPanel.add(new JLabel("Mot de passe:"));
        formPanel.add(champPassword);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton boutonSoumettre = new JButton("S'inscrire");
        panel.add(boutonSoumettre, BorderLayout.SOUTH);

        boutonSoumettre.addActionListener(e -> {

            String name = champName.getText();

            String firstName = champFirstName.getText();

            String email = champEmail.getText();

            String password = new String(champPassword.getPassword());

            // Envoyer  les données dans la base de données MySQL
            try {
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "");

                     PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Utilisateurs (name, firstName, email, password) VALUES (?, ?, ?, ?)")) {

                    pstmt.setString(1, name);
                    pstmt.setString(2, firstName);
                    pstmt.setString(3, email);
                    pstmt.setString(4, password);


                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Vous êtes inscrit  !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(panel,"votre email et votre password sont vos login");

                    if (cardLayout != null && contentPanel != null) {
                        cardLayout.show(contentPanel, "Accueil");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de l'inscription: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        return panel;
    }
}
