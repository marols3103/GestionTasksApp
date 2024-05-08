package Vue;

import javax.swing.*;
import java.awt.*;

public class Accueil {

    public JPanel bienvenuMessage() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Bienvenue sur notre application gestions des taches!");

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        panel.add(welcomeLabel, BorderLayout.CENTER);

        return panel;
    }
}
