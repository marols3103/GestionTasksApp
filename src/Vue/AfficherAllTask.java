package Vue;

import Model.TaskDatabase;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AfficherAllTask {

    public JPanel createTaskPanel() {

        String[] columns = {"ID", "Titre", "Description", "Date d'échéance", "Priorité", "Est fini"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);


        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);

        loadTaskData(tableModel);

        return panel;
    }


    private void loadTaskData(DefaultTableModel tableModel) {

        TaskDatabase taskDatabase = new TaskDatabase();

        List<TaskDatabase.Task> tasks = taskDatabase.getAllTasks();

        // Ajouter les tâches au modèle de table
        for (TaskDatabase.Task task : tasks) {
            Object[] row = {
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getDueDate(),
                    task.getPriority(),
                    task.isFinished() ? "Oui" : "Non"
            };
            tableModel.addRow(row);
        }
    }


}
