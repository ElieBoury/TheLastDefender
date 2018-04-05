import java.util.ArrayList;
import java.util.Scanner;

public class Character extends GameObject {
    private boolean player;
    private boolean keyCharac; //Vrai si c'est un perso nécessaire à battre pour passer à la salle suivante.
    private int earlyDice;
    private int endDice;
    private int nbDice;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    public Character(String nom, String description, boolean player, boolean keyCharac, int earlyDice, int endDice, int nbDice, ArrayList<Item> inventory) {
        super(nom, description);
        this.player = player;
        this.earlyDice = earlyDice;
        this.endDice = endDice;
        this.nbDice = nbDice;
        this.inventory = inventory;
    }


    public Boolean getKeyCharac() {
        return keyCharac;
    }

    public void setKeyCharac(Boolean keyCharac) {
        this.keyCharac = keyCharac;
    }

    public void setPlayer(Boolean player) {
        this.player = player;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Boolean getPlayer() {
        return player;
    }

    public int getEarlyDice() {
        return earlyDice;
    }

    public int getEndDice() {
        return endDice;
    }

    public int getNbDice() {
        return nbDice;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setEarlyDice(int earlyDice) {
        this.earlyDice = earlyDice;
    }

    public void setEndDice(int endDice) {
        this.endDice = endDice;
    }

    public void setNbDice(int nbDice) {
        this.nbDice = nbDice;
    }

    public void changeOfRoom(Room room) {

    }

    public static boolean containCharac(String nom, ArrayList<Character> persos) {
        for (Character perso : persos) {
            if (nom.equals(perso.getName())) {
                return true;
            }
        }
        return false;
    }

    public static Character getCharac(String nom, ArrayList<Character> persos) {
        for (Character perso : persos) {
            if (nom.equals(perso.getName())) {
                return perso;
            }
        }
        return null;
    }

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
                    "   Activer un item (activate)\n" +
                    "   En savoir plus sur cet objet (know more)\n" +
                    "   Ne rien faire (nothing)");
            while (!done) {
                Scanner sc = new Scanner(System.in);
                String motEntré = sc.nextLine();
                switch (motEntré) {
                    case ("activate"):
                        System.out.println("Entrez le nom de l'objet concerné :");
                        String motEntré3 = sc.nextLine();
                        if (Item.containItem(motEntré3, this.inventory)) {
                            System.out.println("Si vous utilisez cet objet, vous le perdrez, en êtes vous sûr ? (yes)/(no)");
                            String motEntré4 = sc.nextLine();
                            if (motEntré4.equals("yes")) {
                                this.activateItem(Item.getItem(motEntré3, this.inventory));
                            } else {
                                System.out.println("Action annulée.");
                            }
                        } else {
                            System.out.println("Vous ne possédez pas cet item.");
                        }
                        done = true;
                        break;
                    case ("know more"):
                        System.out.println("Entrez le nom de l'objet concerné :");
                        String motEntré2 = sc.nextLine();
                        if (Item.containItem(motEntré2, this.inventory)) {
                            System.out.println(Item.getItem(motEntré2, this.inventory).getDescription());
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


    public void manageItem(Item item) {
        System.out.println("Gestion de la récupération de l'item");
        if (this.inventory.size() == 0) {
            System.out.println("Votre inventory est vide.");
        } else {

        }
    }

    public void takeItem(Item item, Room room) {
        if (item == null) {
            System.out.println("Item null");
            return;
        }
        System.out.println("Êtes-vous sûr de vouloir récupérer cet item ? (yes) / (no)");
        Scanner sc = new Scanner(System.in);
        String motEntré = sc.nextLine();
        if (motEntré.equals("yes")) {
            this.inventory.add(item);
            room.getItems().remove(item);
            System.out.println("Vous venez de récupérer un " + item.getName() + " !\n" +
                    "Cet item est désormais dans votre inventory.");
        } else {
            System.out.println("Action annulée.");
        }
    }

    public void activateItem(Item item) {
        if (item.getBonus() != 0) {
            if (item.getBonus() > 0) {
                this.nbDice += item.getBonus();
                System.out.println("Votre nombre de dés vient d'augmenter de " + item.getBonus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".");
            } else {
                this.endDice += (item.getBonus() * -1);
                System.out.println("La borne maximale de vos dés vient d'augmenter de " + item.getBonus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.endDice + ".");
            }
        }
        if (item.getMalus() != 0) {
            if (item.getMalus() > 0) {
                this.nbDice -= item.getMalus();
                System.out.println("Votre nombre de dés vient de diminuer de " + item.getMalus() + " !\n" +
                        "Il est maintenant de " + this.nbDice + ".");
            } else {
                this.endDice += (item.getMalus() * -1);
                System.out.println("La borne maximale de vos dés vient de diminuer de " + item.getMalus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.endDice + ".");
            }
        }
        this.inventory.remove(item);
    }

    public void fight(Character adversaire, int nbManches) {
        int ptsplayer = 0, ptsIa = 0, lancéplayer, lancéIa, mancheActuelle = 1;
        boolean away = false;
        System.out.println("Combat entre " +
                this.getName() + " et " + adversaire.getName() + " en " + nbManches + " manches !");
        while (ptsplayer < nbManches && ptsIa < nbManches && !away) {
            System.out.println("Manche " + mancheActuelle);
            System.out.println(this.getName() + " : " + ptsplayer + " - " + ptsIa + " : " + adversaire.getName());
            System.out.println("Que voulez vous faire ?\n" +
                    "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
            Scanner sc = new Scanner(System.in);
            String motEntré = sc.nextLine();
            switch (motEntré) {
                case ("Attack"):
                    lancéplayer = Générateur.generateScore(this.getEarlyDice(), this.getEndDice());
                    lancéIa = Générateur.generateScore(adversaire.getEarlyDice(), adversaire.getEndDice());
                    System.out.println("Vous avez obtenu " + lancéplayer);
                    System.out.println(adversaire.getName() + " a obtenu " + lancéIa);
                    if (lancéplayer < lancéIa) {
                        System.out.println(adversaire.getName() + " gagne cette manche");
                        ptsIa++;
                        mancheActuelle++;
                    } else if (lancéplayer > lancéIa) {
                        System.out.println("Vous gagnez cette manche");
                        ptsplayer++;
                        mancheActuelle++;
                    } else {
                        System.out.println("Egalité, la défense gagne, " + adversaire.getName() + " remporte la manche");
                        ptsIa++;
                        mancheActuelle++;
                    }
                    break;
                case ("Use"):
                    if (this.getInventory().size() != 0) {
                        System.out.println("Quel item voulez vous activer ?");

                        for (Item items : this.getInventory()) {
                            System.out.println("   " + items);
                        }
                        String motEntré2 = sc.nextLine();
                        if (Item.containItem(motEntré2, this.getInventory())) {
                            this.activateItem(Item.getItem(motEntré2, this.getInventory()));
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
            if (ptsplayer > nbManches / 2) {
                System.out.println("Bravo " + this.getName() + ", vous avez gagné !");
                if(adversaire.getKeyCharac()){
                    this.getCurrentRoom().setUnlocked(true);
                    System.out.println("Bonne nouvelle ! Vous venez de battre le virus gardien de la salle,\n" +
                            "vous pouvez à présent accéder à la salle suivante !");
                }
            } else {
                System.out.println(adversaire.getName() + " a gagné ! Vous avez perdu.");
            }
        } else {
            System.out.println("Vous avez fuit, combat terminé.");
        }
    }

}
