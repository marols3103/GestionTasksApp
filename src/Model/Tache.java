package Model;
import java.time.LocalDate;

public class Tache {

    private String titre;
    private String description;
    private LocalDate date_echeance;
    private String priorite;
    private Boolean estComplete;

    public Tache(String titre,String description,LocalDate date_echeance,String priorite, String status){
        this.titre = titre;
        this.description = description;
        this.date_echeance = date_echeance;
        this.priorite = priorite;
        this.estComplete = false;
    }

    public String getTitre(){
         return titre;
    }

    public String getDescription(){
        return description;
    }

    public LocalDate getDate_echeance(){
        return date_echeance;
    }

    public String getPriorite(){
        return  priorite;
    }

    public  void estComplete( Boolean estComplete){
        this.estComplete = estComplete;
    }


    //formulaire pour ajouter la tache


    @Override
    public String toString() {
        return "Tache{" +
                "titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", dateEcheance=" + date_echeance +
                "priorite = "+ priorite +
                ", estCompletee=" + estComplete +
                '}';
    }


}


