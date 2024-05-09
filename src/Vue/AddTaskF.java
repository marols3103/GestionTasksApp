package Vue;

import Model.MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date; // Pour les dates SQL
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskF extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public AddTaskF() {
        setTitle("Ajouter une Tâche");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(createFormulaireTask(), "Formulaire");
        setContentPane(contentPanel);
    }

    public JPanel createFormulaireTask() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Ajout des champs au formulaire
        /*formPanel.add(new JLabel("Id:"));
        JTextField champId = new JTextField();
        formPanel.add(champId);*/

        formPanel.add(new JLabel("Titre:"));
        JTextField champTitre = new JTextField();
        formPanel.add(champTitre);

        formPanel.add(new JLabel("Description:"));
        JTextField champDescription = new JTextField();
        formPanel.add(champDescription);

        formPanel.add(new JLabel("Date d'échéance:"));
        JTextField champDateEcheance = new JTextField();
        formPanel.add(champDateEcheance);

        formPanel.add(new JLabel("Priorité:"));
        JTextField champPriority = new JTextField();
        formPanel.add(champPriority);

        panel.add(formPanel, BorderLayout.CENTER);

        // Ajout du bouton de soumission
        JButton boutonSoumettre = new JButton("Soumettre");
        panel.add(boutonSoumettre, BorderLayout.SOUTH);

        boutonSoumettre.addActionListener(e -> {
            try {

                String titre = champTitre.getText();
                String description = champDescription.getText();
                String priority = champPriority.getText();

                String dateEcheanceStr = champDateEcheance.getText();
                Date dateEcheance;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false); // Pour valider les dates strictement
                    dateEcheance = new Date(dateFormat.parse(dateEcheanceStr).getTime());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(panel, "Format de date incorrect. Utilisez 'yyyy-MM-dd'.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection conn = MySQLConnection.getConnection();
                if (conn != null) {
                    try {
                        String sql = "INSERT INTO Taches ( titre, description, dateEcheance, priority) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(sql);

                        //pstmt.setInt(1, id);
                        pstmt.setString(2, titre);
                        pstmt.setString(3, description);
                        pstmt.setDate(4, dateEcheance); // Date au bon format
                        pstmt.setString(5, priority);

                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(panel, "Tâche ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(panel, "Erreur lors de l'insertion dans la base de données : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        try {
                            conn.close(); // Fermer la connexion
                        } catch (SQLException ex) {
                            System.err.println("Erreur lors de la fermeture de la connexion : " + ex.getMessage());
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "L'ID doit être un nombre entier.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }

            cardLayout.show(contentPanel, "Accueil");
        });

        return panel;
    }
}
