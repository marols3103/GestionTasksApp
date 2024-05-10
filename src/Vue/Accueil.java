package Vue;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class Accueil {

    public JPanel bienvenuMessage() {
        JPanel panel = new JPanel(new BorderLayout());

        // Texte de bienvenue
        JLabel welcomeLabel = new JLabel("Bienvenue sur notre application de gestion des tâches!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(new EmptyBorder(70, 0, 0, 0));


        ImageIcon originalIcon = new ImageIcon("F:/boss/projet/projetGestionTache/logo.jpg");
        Image originalImage = originalIcon.getImage();
        int newWidth = 400;
        int newHeight = 400;
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);


        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);

        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);

        JPanel techPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout aligne horizontalement
        techPanel.add(new JLabel("Technologies utilisées :"));
        techPanel.add(new JLabel("POO JAVA ,"));
        techPanel.add(new JLabel("SWING ," ));
        techPanel.add(new JLabel("MYSQL ,"));

        // Ajouter le panel des technologies en bas de l'interface
        panel.add(techPanel, BorderLayout.SOUTH);

        return panel;
    }
}
