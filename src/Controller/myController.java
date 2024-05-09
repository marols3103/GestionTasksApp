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

        Accueil accueil = new Accueil();
        JPanel defaultAccueil = accueil.bienvenuMessage(); // Page d'accueil par défaut


        TasksButtons tasksButtons =new TasksButtons(contentPanel,cardLayout);

        JPanel formulaireButtons = tasksButtons.createButtonsTasks();

        JPanel loginPanel = createLoginPanel();

        JPanel signUpPanel = createSignUpPanel();


        contentPanel.add(defaultAccueil, "Accueil");

        contentPanel.add(formulaireButtons, "Buttons");

        contentPanel.add(loginPanel, "Login");

        contentPanel.add(signUpPanel, "SignUp");

        add(createToolbar(), BorderLayout.NORTH);

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createLoginPanel() {

        LoginF loginF = new LoginF(contentPanel, cardLayout);

        JPanel loginPanel = loginF.createFormulaireLogin();

        JButton loginButton = findLoginButton(loginPanel);

        if (loginButton != null) {

            loginButton.addActionListener(e -> {

                isLoggedIn = true;

                cardLayout.show(contentPanel, "Buttons");
            });
        }

        return loginPanel;
    }

    private JButton findLoginButton(JPanel loginPanel) {

        for (Component comp : loginPanel.getComponents()) {

            if (comp instanceof JButton && ((JButton) comp).getText().equals("Login")) {

                return (JButton) comp;
            }
        }
        return null;
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

                cardLayout.show(contentPanel, "Buttons");

            } else {

                cardLayout.show(contentPanel, "Accueil");
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
