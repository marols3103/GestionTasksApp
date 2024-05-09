package Vue;

import javax.swing.*;
import java.awt.*;
import Model.LoginVerification;

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

        int margin = 20;

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

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(boutonSoumettre);

        boutonSoumettre.addActionListener(e -> {

            String email = champEmail.getText();

            String password = new String(champPassword.getPassword());

            LoginVerification loginVerification = new LoginVerification();

            if (loginVerification.isValidUser(email, password)) {

                TasksButtons tasksButtons = new TasksButtons(contentPanel, cardLayout);

                JPanel panelTasks = tasksButtons.createButtonsTasks();

                contentPanel.add(panelTasks, "Tasks"); // Ajouter le panneau des tâches au CardLayout


                String message = "Vous êtes connecté";
               JOptionPane.showMessageDialog(panel, message, "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);

                cardLayout.show(contentPanel, "Tasks"); // Passez au panneau des tâches
            } else {
                JOptionPane.showMessageDialog(panel, "Email ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }
}
