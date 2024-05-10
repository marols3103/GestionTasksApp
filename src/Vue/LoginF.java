package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.LoginVerification;

public class LoginF {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private boolean loginSuccessful = false; // Variable d'état pour indiquer si la connexion a réussi

    public LoginF(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createLoginForm() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Création du panneau de formulaire
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Email:"));
        JTextField emailField = new JTextField(20); // Définir la taille du champ
        formPanel.add(emailField);

        formPanel.add(new JLabel("Mot de passe:"));
        JPasswordField passwordField = new JPasswordField(20); // Définir la taille du champ
        formPanel.add(passwordField);

        mainPanel.add(formPanel);

        // Bouton de connexion
        JButton loginButton = new JButton("Connexion");
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Ajout d'espace entre les composants
        mainPanel.add(loginButton);

        // ActionListener pour le bouton de connexion
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les entrées
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());

                // Vérification des champs vides
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Instancier la classe LoginVerification pour valider l'utilisateur
                LoginVerification loginVerification = new LoginVerification();

                if (loginVerification.isValidUser(email, password)) {
                    loginSuccessful = true; // Marquer la connexion comme réussie
/*
                    // Afficher un message de succès
                    JOptionPane.showMessageDialog(mainPanel, "Connexion réussie!", "Succès", JOptionPane.INFORMATION_MESSAGE);

                    // Créer le toolbar des tâches
                    TasksButtons tasksButtons = new TasksButtons(contentPanel, cardLayout);
                    JToolBar tasksToolbar = tasksButtons.createToolbarTasks();
                    // Ajouter le toolbar au conteneur principal
                    Container parentFrame = SwingUtilities.getAncestorOfClass(JFrame.class, mainPanel);
                    if (parentFrame != null && parentFrame instanceof JFrame) {
                        JFrame frame = (JFrame) parentFrame;
                        frame.getContentPane().add(tasksToolbar, BorderLayout.SOUTH); // Fixer le toolbar en haut
                    }
*/
                    // Naviguer vers un autre panneau
                    //cardLayout.show(contentPanel, "NextPanel");
                } else {
                    loginSuccessful = false;

                    // Afficher un message d'erreur
                    JOptionPane.showMessageDialog(mainPanel, "Email ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return mainPanel;
    }

    // Méthode pour savoir si la connexion a réussi
    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
