package Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.LoginVerification;

public class LoginF {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private boolean loginSuccessful = false;

    public LoginF(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createLoginForm() {

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("Email:"));

        JTextField emailField = new JTextField(20);

        formPanel.add(emailField);

        formPanel.add(new JLabel("Mot de passe:"));

        JPasswordField passwordField = new JPasswordField(20);

        formPanel.add(passwordField);

        mainPanel.add(formPanel);


        JButton loginButton = new JButton("Connexion");

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(loginButton);


        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                String email = emailField.getText().trim();

                String password = new String(passwordField.getPassword());


                if (email.isEmpty() || password.isEmpty()) {

                    JOptionPane.showMessageDialog(mainPanel, "Veuillez remplir tous les champs");
                    return;
                }


                LoginVerification loginVerification = new LoginVerification();

                if (loginVerification.isValidUser(email, password)) {

                    loginSuccessful = true;


                    JOptionPane.showMessageDialog(mainPanel, "Connexion réussie!");


                    emailField.setText("");

                    passwordField.setText("");

                    // Naviguer vers un autre panneau
                    // cardLayout.show(contentPanel, "NextPanel");
                } else {
                    loginSuccessful = false;


                    JOptionPane.showMessageDialog(mainPanel, "Email ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return mainPanel;
    }


    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
