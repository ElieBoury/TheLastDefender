package Classes;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.TextArea;

public class Character extends GameObject {
    private boolean player;
    private boolean wicked;
    private int lowerDice;
    private int upperDice;
    private int nbDice;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    /**
     * Constructor
     * @param name Name of the character
     * @param wicked Classes.Character is wicked or not
     * @param description Description of the character
     * @param player Classes.Character is the player or not
     * @param lowerDice Lower bound of the dice
     * @param upperDice Upper bound of the dice
     * @param nbDice Number of dice owned
     * @param inventory Inventory of the character
     */
    public Character(String name, boolean wicked, String description, boolean player, int lowerDice, int upperDice, int nbDice, ArrayList<Item> inventory) {
        super(name, description);
        this.player = player;
        this.wicked = wicked;
        this.lowerDice = lowerDice;
        this.upperDice = upperDice;
        this.nbDice = nbDice;
        this.inventory = inventory;
    }

    /**
     * player getter
     * @return If the character is a player or not
     */
    public boolean isPlayer() {
        return player;
    }

    /**
     * player setter
     * @param player
     */
    public void setPlayer(boolean player) {
        this.player = player;
    }

    /**
     * wicked getter
     * @return If the character is wicked or not
     */
    public boolean isWicked() {
        return wicked;
    }

    /**
     * wicked setter
     * @param wicked
     */
    public void setWicked(boolean wicked) {
        this.wicked = wicked;
    }

    /**
     * inventory getter
     * @return the inventory of the character
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * inventory setter
     * @param inventory
     */
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * currentRoom getter
     * @return The current room in which the character is
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * currentRoom setter
     * @param currentRoom
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * lowerDice getter
     * @return The lower bound of the dice of the character
     */
    public int getLowerDice() {
        return lowerDice;
    }

    /**
     * lowerDice setter
     * @param lowerDice
     */
    public void setLowerDice(int lowerDice) {
        this.lowerDice = lowerDice;
    }

    /**
     * upperDice getter
     * @return The upper bound of the dice of the character
     */
    public int getUpperDice() {
        return upperDice;
    }

    /**
     * upperDice setter
     * @param upperDice
     */
    public void setUpperDice(int upperDice) {
        this.upperDice = upperDice;
    }

    /**
     * nbDice getter
     * @return The number of dice owned by the character
     */
    public int getNbDice() {
        return nbDice;
    }

    /**
     * nbDice setter
     * @param nbDice
     */
    public void setNbDice(int nbDice) {
        this.nbDice = nbDice;
    }

    /**
     * Management of the inventory by the player
     */
    public void manageInventory(TextArea console) {
        boolean done = false;
        if (this.inventory.size() == 0) {
            console.appendText("\nVotre inventory est vide, récupérez des items !\n");
        } else {
            console.appendText("\nVoici les différents objets présents dans votre inventory :\n");
            for (Item item : this.inventory) {
                console.appendText("   " + item.getName() + "\n");
            }
            console.appendText("Vous pouvez :\n" +
                    "   Relâcher un item (release)\n" +
                    "   Activer un item (activate)\n" +
                    "   En savoir plus sur cet objet (know more)\n" +
                    "   Ne rien faire (nothing)\n");
            while (!done) {
                Scanner sc = new Scanner(System.in);
                String wordRead = sc.nextLine();
                switch (wordRead) {
                    case("release"):
                        this.releaseItem(console);
                        break;
                    case ("activate"):
                        console.appendText("\nEntrez le nom de l'objet concerné :\n");
                        String wordRead3 = sc.nextLine();
                        if (Item.containItem(wordRead3, this.inventory)) {
                            console.appendText("\nSi vous utilisez cet objet, vous le perdrez, en êtes vous sûr ? (yes)/(no)\n");
                            String wordRead4 = sc.nextLine();
                            if (wordRead4.equals("yes")) {
                                this.activateItem(Item.getItem(wordRead3, this.inventory), console);
                            } else {
                                console.appendText("\nAction annulée.\n");
                            }
                        } else {
                            console.appendText("\nVous ne possédez pas cet item.\n");
                        }
                        done = true;
                        break;
                    case ("know more"):
                        console.appendText("\nEntrez le name de l'objet concerné :\n");
                        String wordRead2 = sc.nextLine();
                        if (Item.containItem(wordRead2, this.inventory)) {
                            console.appendText(Item.getItem(wordRead2, this.inventory).getDescription());
                            done = true;
                        } else {
                            console.appendText("Vous ne possédez pas cet item.\n");
                        }
                        break;
                    case ("nothing"):
                        done = true;
                        break;
                    default:
                        console.appendText("\nCommande introuvable\n");
                }
            }
        }
    }

    /**
     * Classes.Item recovery action from the character
     * @param item The item taken
     */
    public void takeItem(Item item, TextArea console) {
        if (item == null) {
            console.appendText("\nClasses.Item null\n");
            return;
        }else {
            console.appendText("\nÊtes-vous sûr de vouloir récupérer cet item ? (yes) / (no)\n");
            Scanner sc = new Scanner(System.in);
            String wordRead = sc.nextLine();
            if (wordRead.equals("yes")) {
                this.inventory.add(item);
                this.currentRoom.getItems().remove(item);
                console.appendText("\nVous venez de récupérer un " + item.getName() + " !\n" +
                        "Cet item est désormais dans votre inventaire.\n");
            } else {
                console.appendText("\nAction annulée.\n");
            }
        }
    }

    @Override
    public String toString(){
        return (getName()+" is a player:"+isPlayer()+", is wicked:"+isWicked()+", his lowerDice is:"+getLowerDice()+
                ", his upperDice is:"+getUpperDice()+", his number of Dice is:"+getNbDice()+", his inventory is" +
                getInventory()+", and his current room is:"+getCurrentRoom());
    }
    /**
     *   private boolean player;
     *     private boolean wicked;
     *     private int lowerDice;
     *     private int upperDice;
     *     private int nbDice;
     *     private ArrayList<Classes.Item> inventory;
     *     private Classes.Room currentRoom;
     */

    /**
     * Activation of an item owned by the character
     * @param item the item activated
     */
    public void activateItem(Item item, TextArea console) {
        if (item.getBonus() != 0) {
            if (item.getBonus() > 0) {
                this.nbDice += item.getBonus();
                console.appendText("\nVotre nombre de dés vient d'augmenter de " + item.getBonus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".\n");
            } else {
                this.upperDice += (item.getBonus() * -1);
                console.appendText("\nLa borne maximale de vos dés vient d'augmenter de " + item.getBonus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.upperDice + ".\n");
            }
        }
        if (item.getMalus() != 0) {
            if (item.getMalus() > 0) {
                this.nbDice -= item.getMalus();
                console.appendText("\nVotre namebre de dés vient de diminuer de " + item.getMalus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".\n");
            } else {
                this.upperDice += (item.getMalus() * -1);
                console.appendText("\nLa borne maximale de vos dés vient de diminuer de " + item.getMalus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.upperDice + ".\n");
            }
        }
        this.inventory.remove(item);
    }

    /**
     * Manage a fight
     * @param opponent The opponent
     * @param nbRounds The number of rounds needed to win
     */
    public void fight(Character opponent, int nbRounds, TextArea console) {
        int ptsPlayer = 0, ptsIa = 0, rolledPlayer, rolledIA, currentRound = 1;
        boolean away = false;
        console.appendText("\nCombat entre " +
                this.getName() + " et " + opponent.getName() + " en " + nbRounds + " manches !\n");
        while (ptsPlayer < nbRounds && ptsIa < nbRounds && !away) {
            console.appendText("Manche " + currentRound + "\n");
            console.appendText(this.getName() + " : " + ptsPlayer + " - " + ptsIa + " : " + opponent.getName() + "\n");
            console.appendText("Que voulez vous faire ?\n" +
                    "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
            Scanner sc = new Scanner(System.in);
            String wordRead = sc.nextLine();
            switch (wordRead) {
                case ("Attack"):
                    rolledPlayer = Generator.generateScore(this.getLowerDice(), this.getUpperDice());
                    rolledIA = Generator.generateScore(opponent.getLowerDice(), opponent.getUpperDice());
                    console.appendText("Vous avez obtenu " + rolledPlayer + "\n");
                    console.appendText(opponent.getName() + " a obtenu " + rolledIA + "\n");
                    if (rolledPlayer < rolledIA) {
                        console.appendText(opponent.getName() + " gagne cette manche\n");
                        ptsIa++;
                        currentRound++;
                    } else if (rolledPlayer > rolledIA) {
                        console.appendText("Vous gagnez cette manche\n");
                        ptsPlayer++;
                        currentRound++;
                    } else {
                        console.appendText("Egalité, la défense gagne, " + opponent.getName() + " remporte la manche\n");
                        ptsIa++;
                        currentRound++;
                    }
                    break;
                case ("Use"):
                    if (this.getInventory().size() != 0) {
                        console.appendText("Quel item voulez vous activer ?\n");

                        for (Item items : this.getInventory()) {
                            console.appendText("   " + items + "\n");
                        }
                        String wordRead2 = sc.nextLine();
                        if (Item.containItem(wordRead2, this.getInventory())) {
                            this.activateItem(Item.getItem(wordRead2, this.getInventory()), console);
                        } else {
                            console.appendText("Vous ne possédez pas cet objet\n");
                        }
                    } else {
                        console.appendText("Vous n'avez aucun item.\n");
                    }
                    break;
                case ("Run Away"):
                    away = true;
                    break;
                default:
                    console.appendText("Mauvaise commande\n");
            }

        }
        if (!away) {
            if (ptsPlayer > nbRounds / 2) {
                console.appendText("Bravo " + this.getName() + ", vous avez gagné !\n");
                if(Room.containCharac(opponent.getName(), currentRoom.getCharacters())){
                    Room.removeCharac(opponent.getName(), this.getCurrentRoom().getLockedCharacters());
                    console.appendText("Bonne nouvelle ! Vous venez de battre un virus gardien de la salle!\n");
                }else{
                    console.appendText("Félicitation, mais " + opponent.getName() +
                    " n'était pas un gardien de la salle.\n");
                }
                if(currentRoom.getLockedCharacters().isEmpty() && currentRoom.getLockedItems().isEmpty()){
                    currentRoom.setUnlocked(true);
                }
            } else {
                console.appendText(opponent.getName() + " a gagné ! Vous avez perdu.\n");
            }
        } else {
            console.appendText("Vous avez fuit, combat terminé.\n");
        }
    }

    public void releaseItem(TextArea console){
        console.appendText("\nQuel item voulez-vous relâcher ?\n");
        for(Item item : this.inventory){
            console.appendText(item.getName());
        }
        Scanner sc = new Scanner(System.in);
        String wordRead = sc.nextLine();
        if(Item.containItem(wordRead, this.inventory)){
            console.appendText("Êtes-vous sûr de vouloir relâcher cet item dans cette salle ? (yes/no)\n");
            String wordRead2 = sc.nextLine();
            if(wordRead2.equals("yes")){
                this.inventory.remove(Item.getItem(wordRead, this.inventory));
                this.currentRoom.getItems().add(Item.getItem(wordRead, this.inventory));
                console.appendText("Vous venez de relâcher l'objet dans " + this.currentRoom.getName() + "\n");
            }else{
                console.appendText("Action annulée\n");
            }
        }else{
            console.appendText("Vous ne possédez pas cet item\n");
        }
    }
   public static Character getCharacter(String name, ArrayList<Character> characters) {
        for (Character myCharacter : characters) {
            if (name.equals(myCharacter.getName())) {
                return myCharacter;
            }
        }
        return null;
    }

    public String characterToCSV(){
        //Name;Wicked;Description;Player;LowerDice;UpperDice;nbDice;Items;
        StringBuilder line =  new StringBuilder();
        line.append(getName()+";"+ isWicked()+";"+getDescription()+";"+isPlayer()+";"+getLowerDice()+";"+getUpperDice()+";"+getNbDice()+";");
        if (this.inventory.isEmpty()){
            line.append("null;");
        }else{
            for (Item myItem: inventory) {
                if(inventory.get(inventory.size()-1)==myItem){
                    line.append(myItem.getName());
                }else{
                    line.append(myItem.getName()+"/");
                }
            }
            line.append(";");
        }
        return line.toString();
    }

    public static void CSVToCharacter(String line){
        //Name;Wicked;Description;Player;LowerDice;UpperDice;nbDice;Items;
        String[]values = line.split(";");
        ArrayList<Item> myInventory = new ArrayList<>();
        Boolean isWicked =false;
        Boolean isPlayer=false;
        if(values[1].equals("true")){
            isWicked = true;
        }
        if(values[3].equals("true")){
            isPlayer=true;
        }
        if (!values[7].equals("null")) {
            String[] OneItem = values[7].split("/");
            for (String myItem:OneItem) {
                myInventory.add(Item.getItem(myItem,Game.items));
            }
        }
        Game.characters.add(new Character(values[0],isWicked,values[2],isPlayer,Integer.parseInt(values[4]),Integer.parseInt(values[5]),Integer.parseInt(values[6]),myInventory));
    }
}
