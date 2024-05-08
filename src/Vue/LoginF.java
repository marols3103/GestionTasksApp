package Vue;

import javax.swing.*;
import java.awt.*;

public class LoginF {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public LoginF(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createFormulaireLogin() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        int margin = 20; // Taille des marges
        panel.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));


        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));


        formPanel.add(new JLabel("Email:"));
        JTextField champEmail = new JTextField();
        formPanel.add(champEmail);

        formPanel.add(new JLabel("Mot de passe:"));
        JPasswordField champPassword = new JPasswordField();
        formPanel.add(champPassword);


        panel.add(formPanel);


        JButton boutonSoumettre = new JButton("Login");
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace entre le formulaire et le bouton
        panel.add(boutonSoumettre);

        boutonSoumettre.addActionListener(e -> {
            String message = "Vous êtes connecté";
            JOptionPane.showMessageDialog(panel, message, "Résumé", JOptionPane.INFORMATION_MESSAGE);

            cardLayout.show(contentPanel, "Accueil");
        });

        return panel;
    }
}
