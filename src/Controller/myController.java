package Controller;

import Vue.Accueil;
import Vue.AddTaskF;
import Vue.LoginF;
import Vue.Sign_upF;
import Vue.TasksButtons;

import javax.swing.*;
import java.awt.*;

public class myController extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private boolean isLoggedIn = false; // Indicateur d'état de connexion

    public myController() {
        super("Gestion des Tâches");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Crée les panneaux pour le CardLayout
        Accueil accueil = new Accueil();
        JPanel defaultAccueil = accueil.bienvenuMessage(); // Page d'accueil par défaut
      // JPanel formulairePanel = createFormulairePanel(); // Panneau de tâches

        TasksButtons tasksButtons =new TasksButtons(contentPanel,cardLayout);
        JPanel formulaireButtons = tasksButtons.createButtonsTasks();

        JPanel loginPanel = createLoginPanel(); // Correction de l'erreur de création multiple
        JPanel signUpPanel = createSignUpPanel();

        // Ajoute les panneaux au contentPanel avec des identifiants uniques
        contentPanel.add(defaultAccueil, "Accueil");
        contentPanel.add(formulaireButtons, "Buttons");
        contentPanel.add(loginPanel, "Login");
        contentPanel.add(signUpPanel, "SignUp");

        // Ajout des composants à la fenêtre
        add(createToolbar(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createLoginPanel() {
        LoginF loginF = new LoginF(contentPanel, cardLayout);
        JPanel loginPanel = loginF.createFormulaireLogin(); // Crée le panneau de connexion

        JButton loginButton = findLoginButton(loginPanel); // Trouve le bouton de connexion
        if (loginButton != null) {
            loginButton.addActionListener(e -> {
                // Suppose que le login est réussi
                isLoggedIn = true; // L'utilisateur est maintenant connecté
                cardLayout.show(contentPanel, "Buttons"); // Afficher le panneau de tâches
            });
        }

        return loginPanel;
    }

    private JButton findLoginButton(JPanel loginPanel) {
        // Parcours les composants pour trouver le bouton de connexion
        for (Component comp : loginPanel.getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {
                return (JButton) comp; // Retourne le bouton s'il est trouvé
            }
        }
        return null; // Retourne null si aucun bouton n'est trouvé
    }

    /*private JPanel createFormulairePanel() {
        AddTaskF addTaskF = new AddTaskF();
        return addTaskF.createFormulairePanel();
    }*/

    private JPanel createSignUpPanel() {
        Sign_upF signUp = new Sign_upF(contentPanel, cardLayout);
        return signUp.createFormulaireSignUp();
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();

       JButton btHome = new JButton("Accueil");
        btHome.addActionListener(e -> {
            if (isLoggedIn) {
                cardLayout.show(contentPanel, "Buttons"); // Afficher le panneau de tâches
            } else {
                cardLayout.show(contentPanel, "Accueil"); // Afficher la page d'accueil par défaut
            }
        });

        JButton btLogin = new JButton("Login");
        btLogin.addActionListener(e -> {
            cardLayout.show(contentPanel, "Login");
        });

        JButton btSignUp = new JButton("SignUp");
        btSignUp.addActionListener(e -> {
            cardLayout.show(contentPanel, "SignUp");
        });

        toolbar.add(btHome);
        toolbar.add(btLogin);
        toolbar.add(btSignUp);

        return toolbar;
    }
}
