package Vue;

import Model.TaskDatabase;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AfficherAllTask {

    private DefaultTableModel tableModel;
    private TaskDatabase taskDatabase;

    public AfficherAllTask() {
        taskDatabase = new TaskDatabase();
        tableModel = new DefaultTableModel(new String[]{"ID", "Titre", "Description", "Date d'échéance", "Priorité", "Est fini"}, 0);
    }

    public JPanel createTaskPanel() {
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton updateButton = new JButton("Mettre à jour");
        panel.add(updateButton, BorderLayout.SOUTH);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadTaskData();
            }
        });

        return panel;
    }

    private void reloadTaskData() {
        tableModel.setRowCount(0); // Clear previous data
        List<TaskDatabase.Task> tasks = taskDatabase.getUnfinishedTasks();
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
