package Vue;

import javax.swing.*;
import java.awt.*;

public class ToolbarConnexion {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    //Boolean isLoggedIn = false;
    public ToolbarConnexion(JPanel panel,CardLayout cardLayout){
        this.contentPanel = panel;
        this.cardLayout = cardLayout;
    }
 public JToolBar createToolbarConnexion() {

    JToolBar toolbar = new JToolBar();

    JButton btHome = new JButton("Accueil");

    btHome.addActionListener(e -> {

        /*if (isLoggedIn) {

            cardLayout.show(contentPanel, "Buttons");

        } else {*/

            cardLayout.show(contentPanel, "Accueil");

    });

    JButton btLogin = new JButton("Login");

    btLogin.addActionListener(e -> {

        cardLayout.show(contentPanel, "Login");
    });

    JButton btSignUp = new JButton("SignUp");

    btSignUp.addActionListener(e -> {

        cardLayout.show(contentPanel, "SignUp");
    });




    toolbar.add(btHome);

    toolbar.add(btLogin);

    toolbar.add(btSignUp);



    return toolbar;
 }
}