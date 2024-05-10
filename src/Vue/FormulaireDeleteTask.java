package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class FormulaireDeleteTask {

    private JPanel contentPanel;
    private CardLayout cardLayout;

    public FormulaireDeleteTask(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createDeleteForm() {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        formPanel.add(new JLabel("ID:"));

        JTextField idField = new JTextField(20); // Champ texte pour l'ID

        formPanel.add(idField);

        formPanel.add(new JLabel("Titre:"));

        JTextField titreField = new JTextField(20);

        formPanel.add(titreField);

        mainPanel.add(formPanel);


        JButton deleteButton = new JButton("Supprimer");

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(idField.getText().trim());

                    String titre = titreField.getText().trim();

                    DeleteTask delete = new DeleteTask();

                    if (delete.deleteTask(id, titre)) {

                        System.out.println("Tâche supprimée avec succès");

                    } else {
                        System.out.println("Échec de la suppression de la tâche");

                    }
                } catch (NumberFormatException nfe) {

                    System.err.println("L'ID doit être un nombre entier");
                }
            }
        });

        return mainPanel;
    }
}
