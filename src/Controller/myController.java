package Controller;

import Vue.*;
import javax.swing.*;
import java.awt.*;

public class myController extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private boolean isLoggedIn = false;

    private JToolBar currentToolbar;

    public myController() {
        super("Gestion des Tâches");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Panneaux principaux
        Accueil accueil = new Accueil();

        JPanel defaultAccueil = accueil.bienvenuMessage();

        JPanel loginPanel = createLoginPanel();

        JPanel signUpPanel = createSignUpPanel();

        JPanel logoutPanel = createLogoutPanel();

        JPanel addTask = formulaireTask();

        JPanel allTasks = allTasks();

        JPanel logOut = loGout();

        JPanel ModifierTask = modifierTask();

        JPanel suppression = supprimerTask();


        // Ajout des panneaux au CardLayout
        contentPanel.add(defaultAccueil, "Accueil");

        contentPanel.add(loginPanel, "Login");

        contentPanel.add(signUpPanel, "SignUp");

        contentPanel.add(logoutPanel, "Logout");

        contentPanel.add(addTask,"AjoutTache");

        contentPanel.add(allTasks,"Afficher");

        contentPanel.add(logOut,"LogOut");

        contentPanel.add(ModifierTask,"Modifier");
        contentPanel.add(suppression,"Supprimer");




        setLayout(new BorderLayout());

        // Ajouter le toolbar de connexion par défaut
        ToolbarConnexion toolbarConnexion = new ToolbarConnexion(contentPanel, cardLayout);

        currentToolbar = toolbarConnexion.createToolbarConnexion(); // Suivre le toolbar actuel

        getContentPane().add(currentToolbar, BorderLayout.NORTH);


        getContentPane().add(contentPanel, BorderLayout.CENTER);


        cardLayout.show(contentPanel, "Accueil");
    }

    private JPanel createLoginPanel() {

        LoginF loginF = new LoginF(contentPanel, cardLayout);

        JPanel loginForm = loginF.createLoginForm();



        loginButtonListener(loginForm, loginF);

        return loginForm;
    }

    private void loginButtonListener(JPanel loginForm, LoginF loginF) {

        loginForm.addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentShown(java.awt.event.ComponentEvent evt) {

                if (loginF.isLoginSuccessful()) {

                    isLoggedIn = true; // Mettre à jour l'état de connexion

                    // Remplacer le toolbar actuel par le toolbar des tâches
                    getContentPane().remove(currentToolbar); // Retirer l'ancien toolbar

                    TasksButtons tasksButtons = new TasksButtons(contentPanel, cardLayout);

                    JToolBar currentToolbar02 = tasksButtons.createToolbarTasks(); // Nouveau toolbar

                    getContentPane().add(currentToolbar02, BorderLayout.NORTH); // Ajouter le nouveau


                    // Revalider le layout pour prendre en compte les changements
                    getContentPane().revalidate();
                    getContentPane().repaint();
                }
            }
        });
    }

    private JPanel createSignUpPanel() {
        Sign_upF signUp = new Sign_upF(contentPanel, cardLayout);
        return signUp.createFormulaireSignUp();
    }

    private JPanel createLogoutPanel() {
        isLoggedIn = false; // Indiquer que l'utilisateur est déconnecté
        Accueil accueil = new Accueil();
        return accueil.bienvenuMessage();
    }

    private JPanel formulaireTask(){
        AddTaskF addTaskF = new AddTaskF();
        return addTaskF.createFormulaireTask();
    }
    private JPanel allTasks(){
        AfficherAllTask afficherAllTask = new AfficherAllTask();
        return afficherAllTask.createTaskPanel();

    }

    private JPanel loGout(){

        ToolbarConnexion toolbarConnexion = new ToolbarConnexion(contentPanel, cardLayout);
        currentToolbar = toolbarConnexion.createToolbarConnexion(); // Suivre le toolbar actuel
        getContentPane().add(currentToolbar, BorderLayout.NORTH);
        isLoggedIn = false;
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Accueil accueil = new Accueil();
        return accueil.bienvenuMessage();
    }

    private JPanel modifierTask(){
        ModifierTask modifierTask = new ModifierTask();
        return modifierTask.createTaskPanelModification();
    }

    private JPanel supprimerTask(){
        FormulaireDeleteTask formulaireDeleteTask = new FormulaireDeleteTask(contentPanel,cardLayout);
        return  formulaireDeleteTask.createDeleteForm();
    }

}
