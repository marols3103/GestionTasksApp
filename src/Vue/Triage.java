package Vue;

import Model.TaskDatabase;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Triage {

    public JPanel createTriagePanel() {

        String[] columns = {"ID", "Titre", "Description", "Date d'échéance", "Priorité", "Est fini"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());

        JCheckBox trierParDateCheckBox = new JCheckBox("Trier par date d'échéance");
        JCheckBox trierParPrioriteCheckBox = new JCheckBox("Trier par priorité");

        JButton updateButton = new JButton("Mettre à jour");

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(trierParDateCheckBox);
        checkBoxPanel.add(trierParPrioriteCheckBox);

        panel.add(checkBoxPanel, BorderLayout.NORTH);
        panel.add(updateButton, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadTaskData(tableModel, trierParDateCheckBox, trierParPrioriteCheckBox);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTaskData(tableModel, trierParDateCheckBox, trierParPrioriteCheckBox);
            }
        });

        return panel;
    }

    private void loadTaskData(DefaultTableModel tableModel, JCheckBox trierParDateCheckBox, JCheckBox trierParPrioriteCheckBox) {
        TaskDatabase taskDatabase = new TaskDatabase();
        List<TaskDatabase.Task> tasks;

        if (trierParDateCheckBox.isSelected()) {
            tasks = taskDatabase.tasksSortedByDate();
        } else if (trierParPrioriteCheckBox.isSelected()) {
            tasks = taskDatabase.tasksSortedByPriority();
        } else {
            tasks = taskDatabase.getUnfinishedTasks();
        }

        tableModel.setRowCount(0);

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
