package Vue;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import Model.TaskDatabase;
import Model.TaskDatabase.Task;
import java.util.List;

public class ModifierTask {

    private Map<Integer, Map<Integer, Object>> changes = new HashMap<>();


    public JPanel createTaskPanelModification() {

        String[] columns = {"ID", "Titre", "Description", "Date d'échéance", "Priorité", "Est fini"};

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {

                return column > 0; // Permettre l'édition sauf pour l'ID
            }
        };

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);

        // Ajouter un bouton "Enregistrer"
        JButton saveButton = new JButton("Enregistrer");

        panel.add(saveButton, BorderLayout.SOUTH);

        // écouteur d'événements pour le bouton "Enregistrer"
        saveButton.addActionListener(e -> saveChangesToDatabase());

        // Détecter les modifications dans le tableau
        tableModel.addTableModelListener(e -> {

            if (e.getType() == TableModelEvent.UPDATE) {

                int row = e.getFirstRow();

                int column = e.getColumn();

                Object newValue = tableModel.getValueAt(row, column);

                // Conversion correcte pour "estFini"
                if (column == 5) { // Colonne "estFini"

                    newValue = "Oui".equalsIgnoreCase((String) newValue) ? 1 : 0;
                }

                // Stocker la modification dans la carte des changements

                changes.computeIfAbsent(row, k -> new HashMap<>()).put(column, newValue);
            }
        });

        // Charger les données des tâches dans le tableau
        loadTaskData(tableModel);

        return panel;
    }

    // Charger les données des tâches dans le modèle de table

    private void loadTaskData(DefaultTableModel tableModel) {

        TaskDatabase taskDatabase = new TaskDatabase();

        List<Task> tasks = taskDatabase.getAllTasks();

        for (Task task : tasks) {

            Object[] row = {

                    task.getId(),

                    task.getName(),

                    task.getDescription(),

                    task.getDueDate(),

                    task.getPriority(),

                    task.isFinished() ? 1 : 0
            };
            tableModel.addRow(row);
        }
    }

    // Méthode pour enregistrer les modifications dans la base de données
    private void saveChangesToDatabase() {

        TaskDatabase taskDatabase = new TaskDatabase();

        try (Connection conn = taskDatabase.getConnection()) {

            for (Map.Entry<Integer, Map<Integer, Object>> entry : changes.entrySet()) {

                int row = entry.getKey();

                Task task = taskDatabase.getAllTasks().get(row);

                for (Map.Entry<Integer, Object> change : entry.getValue().entrySet()) {

                    int column = change.getKey();

                    Object newValue = change.getValue();

                    if (column == 5) { // Conversion correcte pour "estFini"

                        newValue = ((int) newValue == 1) ? 1 : 0;
                    }

                    String columnName = getColumnName(column); // Obtenir le nom de la colonne correspondante

                    String updateQuery = String.format(
                            "UPDATE Taches SET %s = ? WHERE id = ?",
                            columnName
                    );

                    try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                        pstmt.setObject(1, newValue); // Nouvelle valeur

                        pstmt.setInt(2, task.getId()); // ID de la tâche

                        pstmt.executeUpdate(); // Exécuter la mise à jour

                    }
                }
            }

            changes.clear(); // Effacer les modifications une fois enregistrées

        } catch (SQLException ex) {
            System.err.println("Erreur lors de l'enregistrement des modifications : " + ex.getMessage());
        }
    }

    // Méthode pour obtenir le nom de la colonne correspondant au numéro de colonne
    private String getColumnName(int column) {
        switch (column) {
            case 1: return "titre";
            case 2: return "description";
            case 3: return "dateEcheance";
            case 4: return "priority";
            case 5: return "estFini";
            default: return "";
        }
    }
}