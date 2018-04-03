import java.util.*;

public class Salle extends Description{

    ArrayList <Item> items;
    ArrayList <Personnage> personnages;

    public Salle(String nom, String description) {
        super(nom, description);
        this.items = new ArrayList<>();
        this.personnages = new ArrayList<>();
    }

    static void présenterSalle(Salle salle){
        System.out.println("Dans cette salle sont présents ces items: ");
        for(Item item :  salle.items){
            System.out.println("   " + item.nom);
        }
        System.out.println("Dans cette salle sont présents ces personnages: ");
        for(Personnage personnages :  salle.personnages){
            System.out.println("   " + personnages.nom);
        }
    }

    static void getPersonnage(String nom){
        /*for(

                ){

        }*/
    }



}
