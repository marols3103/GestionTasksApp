package Vue;

import Model.MySQLConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
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

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

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

        formPanel.add(new JLabel("Est fini ?"));

        JCheckBox champEstFini = new JCheckBox();

        formPanel.add(champEstFini);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton boutonSoumettre = new JButton("Soumettre");

        panel.add(boutonSoumettre, BorderLayout.SOUTH);

        boutonSoumettre.addActionListener(e -> {
            try {
                String titre = champTitre.getText();

                String description = champDescription.getText();

                String priority = champPriority.getText();

                boolean estFini = champEstFini.isSelected(); // Booléen


                String dateEcheanceStr = champDateEcheance.getText();
                Date dateEcheance;

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    dateFormat.setLenient(false);

                    dateEcheance = new Date(dateFormat.parse(dateEcheanceStr).getTime());

                } catch (ParseException ex) {

                    JOptionPane.showMessageDialog(panel, "Format de date incorrect. Utilisez 'yyyy-MM-dd'.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Connection conn = MySQLConnection.getConnection();

                if (conn != null) {

                    try {
                        String sql = "INSERT INTO Taches (titre, description, dateEcheance, priority, estFini) VALUES (?, ?, ?, ?, ?)";

                        PreparedStatement pstmt = conn.prepareStatement(sql);

                        pstmt.setString(1, titre);

                        pstmt.setString(2, description);

                        pstmt.setDate(3, dateEcheance);

                        pstmt.setString(4, priority);

                        pstmt.setBoolean(5, estFini);

                        pstmt.executeUpdate();

                        JOptionPane.showMessageDialog(panel, "Tâche ajoutée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(panel, "Erreur lors de l'insertion dans la base de données: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);

                    } finally {

                        try {

                            conn.close();

                        } catch (SQLException ex) {

                            System.err.println("Erreur lors de la fermeture de la connexion: " + ex.getMessage());
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                
                JOptionPane.showMessageDialog(panel, "Erreur lors de l'ajout de la tâche.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            cardLayout.show(contentPanel, "Accueil");
        });

        return panel;
    }
}
