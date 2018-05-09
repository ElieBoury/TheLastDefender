import java.util.ArrayList;
import java.util.Scanner;

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
     * @param wicked Character is wicked or not
     * @param description Description of the character
     * @param player Character is the player or not
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
    public void manageInventory() {
        boolean done = false;
        if (this.inventory.size() == 0) {
            System.out.println("Votre inventory est vide, récupérez des items !");
        } else {
            System.out.println("Voici les différents objets présents dans votre inventory :");
            for (Item item : this.inventory) {
                System.out.println("   " + item.getName());
            }
            System.out.println("Vous pouvez :\n" +
                    "   Relâcher un item (release)\n" +
                    "   Activer un item (activate)\n" +
                    "   En savoir plus sur cet objet (know more)\n" +
                    "   Ne rien faire (nothing)");
            while (!done) {
                Scanner sc = new Scanner(System.in);
                String wordRead = sc.nextLine();
                switch (wordRead) {
                    case("release"):
                        this.releaseItem();
                        break;
                    case ("activate"):
                        System.out.println("Entrez le nom de l'objet concerné :");
                        String wordRead3 = sc.nextLine();
                        if (Item.containItem(wordRead3, this.inventory)) {
                            System.out.println("Si vous utilisez cet objet, vous le perdrez, en êtes vous sûr ? (yes)/(no)");
                            String wordRead4 = sc.nextLine();
                            if (wordRead4.equals("yes")) {
                                this.activateItem(Item.getItem(wordRead3, this.inventory));
                            } else {
                                System.out.println("Action annulée.");
                            }
                        } else {
                            System.out.println("Vous ne possédez pas cet item.");
                        }
                        done = true;
                        break;
                    case ("know more"):
                        System.out.println("Entrez le name de l'objet concerné :");
                        String wordRead2 = sc.nextLine();
                        if (Item.containItem(wordRead2, this.inventory)) {
                            System.out.println(Item.getItem(wordRead2, this.inventory).getDescription());
                            done = true;
                        } else {
                            System.out.println("Vous ne possédez pas cet item.");
                        }
                        break;
                    case ("nothing"):
                        done = true;
                        break;
                    default:
                        System.out.println("Commande introuvable");
                }
            }
        }
    }

    /**
     * Item recovery action from the character
     * @param item The item taken
     */
    public void takeItem(Item item) {
        if (item == null) {
            System.out.println("Item null");
            return;
        }
        System.out.println("Êtes-vous sûr de vouloir récupérer cet item ? (yes) / (no)");
        Scanner sc = new Scanner(System.in);
        String wordRead = sc.nextLine();
        if (wordRead.equals("yes")) {
            this.inventory.add(item);
            this.currentRoom.getItems().remove(item);
            System.out.println("Vous venez de récupérer un " + item.getName() + " !\n" +
                    "Cet item est désormais dans votre inventaire.");
        } else {
            System.out.println("Action annulée.");
        }
    }

    /**
     * Activation of an item owned by the character
     * @param item the item activated
     */
    public void activateItem(Item item) {
        if (item.getBonus() != 0) {
            if (item.getBonus() > 0) {
                this.nbDice += item.getBonus();
                System.out.println("Votre namebre de dés vient d'augmenter de " + item.getBonus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".");
            } else {
                this.upperDice += (item.getBonus() * -1);
                System.out.println("La borne maximale de vos dés vient d'augmenter de " + item.getBonus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.upperDice + ".");
            }
        }
        if (item.getMalus() != 0) {
            if (item.getMalus() > 0) {
                this.nbDice -= item.getMalus();
                System.out.println("Votre namebre de dés vient de diminuer de " + item.getMalus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".");
            } else {
                this.upperDice += (item.getMalus() * -1);
                System.out.println("La borne maximale de vos dés vient de diminuer de " + item.getMalus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.upperDice + ".");
            }
        }
        this.inventory.remove(item);
    }

    /**
     * Manage a fight
     * @param opponent The opponent
     * @param nbRounds The number of rounds needed to win
     */
    public void fight(Character opponent, int nbRounds) {
        int ptsPlayer = 0, ptsIa = 0, rolledPlayer, rolledIA, currentRound = 1;
        boolean away = false;
        System.out.println("Combat entre " +
                this.getName() + " et " + opponent.getName() + " en " + nbRounds + " manches !");
        while (ptsPlayer < nbRounds && ptsIa < nbRounds && !away) {
            System.out.println("Manche " + currentRound);
            System.out.println(this.getName() + " : " + ptsPlayer + " - " + ptsIa + " : " + opponent.getName());
            System.out.println("Que voulez vous faire ?\n" +
                    "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
            Scanner sc = new Scanner(System.in);
            String wordRead = sc.nextLine();
            switch (wordRead) {
                case ("Attack"):
                    rolledPlayer = Generator.generateScore(this.getLowerDice(), this.getUpperDice());
                    rolledIA = Generator.generateScore(opponent.getLowerDice(), opponent.getUpperDice());
                    System.out.println("Vous avez obtenu " + rolledPlayer);
                    System.out.println(opponent.getName() + " a obtenu " + rolledIA);
                    if (rolledPlayer < rolledIA) {
                        System.out.println(opponent.getName() + " gagne cette manche");
                        ptsIa++;
                        currentRound++;
                    } else if (rolledPlayer > rolledIA) {
                        System.out.println("Vous gagnez cette manche");
                        ptsPlayer++;
                        currentRound++;
                    } else {
                        System.out.println("Egalité, la défense gagne, " + opponent.getName() + " remporte la manche");
                        ptsIa++;
                        currentRound++;
                    }
                    break;
                case ("Use"):
                    if (this.getInventory().size() != 0) {
                        System.out.println("Quel item voulez vous activer ?");

                        for (Item items : this.getInventory()) {
                            System.out.println("   " + items);
                        }
                        String wordRead2 = sc.nextLine();
                        if (Item.containItem(wordRead2, this.getInventory())) {
                            this.activateItem(Item.getItem(wordRead2, this.getInventory()));
                        } else {
                            System.out.println("Vous ne possédez pas cet objet");
                        }
                    } else {
                        System.out.println("Vous n'avez aucun item.");
                    }
                    break;
                case ("Run Away"):
                    away = true;
                    break;
                default:
                    System.out.println("Mauvaise commande");
            }

        }
        if (!away) {
            if (ptsPlayer > nbRounds / 2) {
                System.out.println("Bravo " + this.getName() + ", vous avez gagné !");
                if(Room.containCharac(opponent.getName(), currentRoom.getCharacters())){
                    Room.removeCharac(opponent.getName(), this.getCurrentRoom().getLockedCharacters());
                    System.out.println("Bonne nouvelle ! Vous venez de battre un virus gardien de la salle!");
                }else{
                    System.out.println("Félicitation, mais " + opponent.getName() +
                    " n'était pas un gardien de la salle.");
                }
                if(currentRoom.getLockedCharacters().isEmpty() && currentRoom.getLockedItems().isEmpty()){
                    currentRoom.setUnlocked(true);
                }
            } else {
                System.out.println(opponent.getName() + " a gagné ! Vous avez perdu.");
            }
        } else {
            System.out.println("Vous avez fuit, combat terminé.");
        }
    }

    public void releaseItem(){
        System.out.println("Quel item voulez-vous relâcher ?");
        for(Item item : this.inventory){
            System.out.println(item.getName());
        }
        Scanner sc = new Scanner(System.in);
        String wordRead = sc.nextLine();
        if(Item.containItem(wordRead, this.inventory)){
            System.out.println("Êtes-vous sûr de vouloir relâcher cet item dans cette salle ? (yes/no)");
            String wordRead2 = sc.nextLine();
            if(wordRead2.equals("yes")){
                this.inventory.remove(Item.getItem(wordRead, this.inventory));
                this.currentRoom.getItems().add(Item.getItem(wordRead, this.inventory));
                System.out.println("Vous venez de relâcher l'objet dans " + this.currentRoom.getName());
            }else{
                System.out.println("Action annulée");
            }
        }else{
            System.out.println("Vous ne possédez pas cet item");
        }
    }

    public String characterToCSV(){
        StringBuilder line =  new StringBuilder();
        line.append(getName()+";"+ isWicked()+";"+getDescription()+";"+isPlayer()+";"+getLowerDice()+";"+getUpperDice()+";"+getNbDice()+";");
        if (inventory.isEmpty()){
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
        String[]values = line.split(";");
        System.out.println(values.length);
        if (values[7] == "null") {
            ArrayList<Item> inventoryNull = new ArrayList<>();
            //new Character(values[0],values[1],values[2],values[3],values[4],values[5],values[6],inventoryNull);
            //String name, boolean wicked, String description, boolean player, int lowerDice, int upperDice, int nbDice, ArrayList<Item> inventory
        }
    }
}
