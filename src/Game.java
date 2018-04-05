import java.util.Scanner;
import java.util.*;

public class Game {

    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        boolean monstreBattu = false;
        boolean end = false;

        ArrayList<Item> inventaireNul = new ArrayList<>();
        Personnage player = new Personnage(
                "AVAST", "C'est vous !", true, false, 1, 6, 1, inventaireNul);
        Personnage perso1 = new Personnage(
                "Abelson", "Abelson est un virus méchant", false, false, 1, 6, 1, inventaireNul);
        Personnage perso2 = new Personnage(
                "JigSaw", "JigSaw est un virus méchant", false, true, 1, 4, 1, inventaireNul);

        Item item1 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite " +
                "max des dès de 2 pour toujours !");
        Item item2 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite " +
                "max des dès de 2 pour toujours !");

        Salle salle1 = new Salle("Salle Initiale", "Description salle1");
        salle1.getItems().add(item1);
        salle1.getItems().add(item2);
        salle1.getPersonnages().add(perso1);
        salle1.getPersonnages().add(perso2);

        Salle salle2 = new Salle("Salle Numero 2", "Description salle2");

        player.setCurrentRoom(salle1);

        //Démarrage jeu
        System.out.println("*PREMIER MESSAGE*");

        //Début jeu
        while (!end) {
            System.out.println("Vous êtes dans la " + player.getCurrentRoom().getNom() + " !\n" +
                    "Essayez d'en sortir !");
            Salle.présenterSalle(salle1);
            System.out.println("Pour ramasser un objet, tapez \"take\".\n" +
                    "Pour parler à un personnage, tapez \"speak\".\n" +
                    "Pour connaître d'autres commandes, tapez \"help\".");
            while (!player.getCurrentRoom().isUnlocked()) {
                //Demande d'action du joueur
                System.out.println("Agissez !");

                Scanner sc = new Scanner(System.in);
                String motEntré = sc.nextLine();

                switch (motEntré) {
                    case ("help"):
                        help();
                        break;
                    case ("quit"):
                        quitter();
                        break;
                    case ("inventory"):
                        player.gérerInventaire();
                        break;
                    case ("speak"):
                        System.out.println("Avec qui voulez-vous parler ?");
                        String motEntré2 = sc.nextLine();
                        if (Personnage.contientPerso(motEntré2, salle1.getPersonnages())) {
                            player.fight(Personnage.obtenirPerso(motEntré2, salle1.getPersonnages()), 2);
                        } else {
                            System.out.println("Ce personnage n'est pas dans cette salle.");
                        }
                        break;
                    case ("take"):
                        System.out.println("Quel objet voulez-vous récupérer ?");
                        String motEntré3 = sc.nextLine();
                        if (Item.contientItem(motEntré3, salle1.getItems())) {
                            player.récupérerItem(Item.obtenirItem(motEntré3, salle1.getItems()), salle1);
                        } else {
                            System.out.println("Cet item n'est pas dans cette salle.");

                        }
                        break;
                    case ("next room"):
                        if (monstreBattu) {
                            player.setCurrentRoom(salle2);
                        }
                        break;
                    default:
                        System.out.println("Ce que vous avez demandé n'est pas valide, tapez help.");
                }
            }
        }
    }

    //Différentes fonctions

    static void help() {
        System.out.println("But : sortir de cette salle.\n" +
                "Actions possibles :\n" +
                "   \"quit\" : quitter le jeu sans sauvegarder\n" +
                "   \"inventory\" : accéder à l'inventaire\n" +
                "   \"take\" : prendre un objet présent dans la salle\n" +
                "   \"speak\" : parler à un personnage présent dans la salle\n" +
                "   \"next room\" : aller à la salle suivante");
    }

    static void quitter() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
