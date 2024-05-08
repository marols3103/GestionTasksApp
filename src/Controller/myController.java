package Controller;
import  Vue.Accueil;
import Vue.AddTaskF;
import Vue.LoginF;
import Vue.Sign_upF;

import javax.swing.*;
import java.awt.*;

public class myController extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public myController() {
        super("Gestion des Tâches");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Crée les panneaux à ajouter au CardLayout
       //JPanel accueilPanel = createAccueilPanel();
        Accueil accueil = new Accueil();
        JPanel mesageBienvenu = accueil.bienvenuMessage();
        //JPanel formulairePanel = createFormulairePanel();
        JPanel loginPanel = createLoginPanel();
        JPanel signUpPanel = createSignUpPanel(); // Correction de nom

        // Ajoute les panneaux au contentPanel avec des identifiants uniques
        contentPanel.add(mesageBienvenu, "Accueil");
        //contentPanel.add(formulairePanel, "Formulaire");
        contentPanel.add(loginPanel, "Login");
        contentPanel.add(signUpPanel, "SignUp");

        // Ajout des composants à la fenêtre
        add(createToolbar(), BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

    }

    private JPanel createAccueilPanel() {
        JPanel panel = new JPanel();

        JButton btAddTask = new JButton("Ajouter Tâche");
        JButton btTrie = new JButton("Trier Tâches");
        JButton btAffichage = new JButton("Afficher Tâches");
        JButton btSupprimer = new JButton("Supprimer Tâches");

        btAddTask.addActionListener(e -> {
            cardLayout.show(contentPanel, "Formulaire");
        });

        panel.add(btAddTask);
        panel.add(btTrie);
        panel.add(btAffichage);
        panel.add(btSupprimer);

        return panel;
    }

    private JPanel createFormulairePanel() {
        AddTaskF addTaskF = new AddTaskF();
        return addTaskF.createFormulairePanel();
    }

    private JPanel createLoginPanel() {
        LoginF loginF = new LoginF(contentPanel, cardLayout);
        return loginF.createFormulaireLogin();
    }

    private JPanel createSignUpPanel() { // Correction de nom
        Sign_upF signUp = new Sign_upF();
        return signUp.createFormulaireSignUp();
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();

        JButton btHome = new JButton("Accueil");
        btHome.addActionListener(e -> {
            cardLayout.show(contentPanel, "Accueil");
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
