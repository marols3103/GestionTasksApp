package Vue;

import javax.swing.*;
import java.awt.*;

public class TasksButtons {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    //Boolean isLoggedIn = false;
    public TasksButtons(JPanel panel,CardLayout cardLayout){
        this.contentPanel = panel;
        this.cardLayout = cardLayout;
    }
    public JToolBar createToolbarTasks() {

        JToolBar toolbar = new JToolBar();

        JButton btAddTask = new JButton("AjoutTache");

        btAddTask.addActionListener(e -> {

        /*if (isLoggedIn) {

            cardLayout.show(contentPanel, "Buttons");

        } else {*/

            cardLayout.show(contentPanel, "AjoutTache");

        });


        JButton btAffichage = new JButton("Afficher");

        btAffichage.addActionListener(e -> {

            cardLayout.show(contentPanel, "Afficher");
        });

        JButton btSuppression= new JButton("Supprimer");

        btSuppression.addActionListener(e -> {

            cardLayout.show(contentPanel, "Supprimer");
        });

        JButton btTriage= new JButton("Trier");

        btTriage.addActionListener(e -> {

            cardLayout.show(contentPanel, "Trier");
        });

        JButton btModification= new JButton("Modifier");

        btModification.addActionListener(e -> {


            cardLayout.show(contentPanel, "Modifier");
        });

        JButton btLogOut= new JButton("LogOut");

        btLogOut.addActionListener(e -> {


            cardLayout.show(contentPanel, "LogOut");
        });



        toolbar.add(btModification);
        toolbar.add(btAddTask);
        toolbar.add(btSuppression);
        toolbar.add(btTriage);
        toolbar.add(btAffichage);
        toolbar.add(btLogOut);



        return toolbar;
    }

}