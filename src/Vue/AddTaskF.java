package Vue;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import Model.MySQLConnection;

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

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));


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

        JRadioButton lowPriority = new JRadioButton("Faible");

        JRadioButton mediumPriority = new JRadioButton("Moyenne");

        JRadioButton highPriority = new JRadioButton("Élevée");

        ButtonGroup priorityGroup = new ButtonGroup();

        priorityGroup.add(lowPriority);

        priorityGroup.add(mediumPriority);

        priorityGroup.add(highPriority);

        JPanel priorityPanel = new JPanel(new GridLayout(1, 3));

        priorityPanel.add(lowPriority);

        priorityPanel.add(mediumPriority);

        priorityPanel.add(highPriority);

        formPanel.add(priorityPanel);

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

                String priority = "Moyenne";

                if (highPriority.isSelected()) {

                    priority = "Élevée";

                } else if (lowPriority.isSelected()) {

                    priority = "Faible";
                }

                boolean estFini = champEstFini.isSelected();

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

                        // Vider les champs après l'ajout réussi
                        champTitre.setText("");

                        champDescription.setText("");

                        champDateEcheance.setText("");

                        priorityGroup.clearSelection();

                        champEstFini.setSelected(false);

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(panel, "Erreur lors de l'insertion dans la base de données " );
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Erreur lors de l'ajout de la tâche.");
            }
        });

        return panel;
    }


}
