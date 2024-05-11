import Vue.AddTaskF;
import javax.swing.*;
import Controller.*;
import Model.MySQLConnection;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.sql.Connection;
public class Main {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    public static void main(String[] args) throws Exception{

        try{
            // ajouter  lookAndFeel pour mise en page
            UIManager.setLookAndFeel(new NimbusLookAndFeel());

            Connection connection = MySQLConnection.getConnection();

            myController myInterface = new myController();

            myInterface.setVisible(true);

        }catch(ExceptionInInitializerError e){

            e.getStackTrace();

            System.out.println("Erreur au niveau de main");

        }

    }
}