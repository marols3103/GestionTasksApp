package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulaireSaisie extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public JPanel createFormulairePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Nom:"));
        JTextField champNom = new JTextField();
        champNom.setPreferredSize(new Dimension(20,5));
        formPanel.add(champNom);

        formPanel.add(new JLabel("Prénom:"));
        JTextField champPrenom = new JTextField();
        formPanel.add(champPrenom);

        formPanel.add(new JLabel("Email:"));
        JTextField champEmail = new JTextField();
        formPanel.add(champEmail);

        formPanel.add(new JLabel("Genre:"));
        JComboBox<String> comboGenre = new JComboBox<>(new String[]{"Homme", "Femme", "Autre"});
        formPanel.add(comboGenre);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton boutonSoumettre = new JButton("Soumettre");
        panel.add(boutonSoumettre, BorderLayout.SOUTH);

        boutonSoumettre.addActionListener(e -> {
            String nom = champNom.getText();
            String prenom = champPrenom.getText();
            String email = champEmail.getText();
            String genre = (String) comboGenre.getSelectedItem();

            String message = "Nom: " + nom + "\nPrénom: " + prenom + "\nEmail: " + email + "\nGenre: " + genre;
            JOptionPane.showMessageDialog(panel, message, "Résultat", JOptionPane.INFORMATION_MESSAGE);

            // Retourner à l'accueil après soumission
            cardLayout.show(contentPanel, "Accueil");
        });

        return panel;
    }




}
