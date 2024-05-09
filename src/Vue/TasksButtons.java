package Vue;

import javax.swing.*;
import java.awt.*;

public class TasksButtons extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;

    public TasksButtons(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createButtonsTasks() {
        JPanel panel = new JPanel();

        JButton btAddTask = new JButton("Ajouter Tâche");
        JButton btTrie = new JButton("Trier Tâches");
        JButton btAffichage = new JButton("Afficher Tâches");
        JButton btSupprimer = new JButton("Supprimer Tâches");


        btAddTask.addActionListener(e -> {

            JPanel ajouterTachesPanel = AjouterTaches();
            contentPanel.add(ajouterTachesPanel, "AjouterTaches");
            cardLayout.show(contentPanel, "AjouterTaches");
        });

        panel.add(btAddTask);
        panel.add(btTrie);
        panel.add(btAffichage);
        panel.add(btSupprimer);

        return panel;
    }

    public JPanel AjouterTaches() {
        AddTaskF addTaskF = new AddTaskF();
        return addTaskF.createFormulaireTask(); // Votre méthode de création de tâches
    }
}
