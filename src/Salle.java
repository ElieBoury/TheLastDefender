import java.util.*;

public class Salle extends Description{

    ArrayList <Item> items;

    public Salle(String nom, String description) {
        super(nom, description);
        this.items = new ArrayList<>();
    }



}
