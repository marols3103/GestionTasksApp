package Vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Sign_upF {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public JPanel createFormulaireSignUp() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // Réduisez les écarts de lignes et colonnes


        JTextField champName = new JTextField();
        champName.setBorder(new EmptyBorder(5, 5, 5, 5));

        JTextField champFirstName = new JTextField();
        champFirstName.setBorder(new EmptyBorder(5, 5, 5, 5));

        JTextField champEmail = new JTextField();
        champEmail.setBorder(new EmptyBorder(5, 1, 5, 1));

        JTextField champPassword = new JTextField();
        champPassword.setBorder(new EmptyBorder(5, 5, 5, 5));

        JTextField champRole = new JTextField();
        champRole.setBorder(new EmptyBorder(5, 5, 5, 5));

        formPanel.add(new JLabel("Name:"));
        formPanel.add(champName);

        formPanel.add(new JLabel("FirstName:"));
        formPanel.add(champFirstName);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(champEmail);

        formPanel.add(new JLabel("Password:"));
        formPanel.add(champPassword);

        formPanel.add(new JLabel("Role:"));
        formPanel.add(champRole);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton boutonSoumettre = new JButton("Sign_up");
        panel.add(boutonSoumettre, BorderLayout.SOUTH);

        boutonSoumettre.addActionListener(e -> {
            String message = "Vous êtes inscrits";
            JOptionPane.showMessageDialog(panel, message, "Résumé", JOptionPane.INFORMATION_MESSAGE);


            if (cardLayout != null && contentPanel != null) {
                cardLayout.show(contentPanel, "Accueil");
            }
        });

        return panel;
    }
}
