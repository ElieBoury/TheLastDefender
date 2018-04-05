import java.util.Scanner;
import java.util.*;

public class Game {

    public static void main(String[] args) {
        launch();
    }

    public static void launch() {
        boolean monsterBeaten = false;
        boolean end = false;

        ArrayList<Item> inventoryNull= new ArrayList<>();
        Character player = new Character(
                "AVAST", false, "C'est vous !", true, false, 1, 6, 1, inventoryNull);
        Character perso1 = new Character(
                "Abelson", true, "Abelson est un virus méchant", false, false, 1, 6, 1, inventoryNull);
        Character perso2 = new Character(
                "JigSaw", true,"JigSaw est un virus méchant", false, true, 1, 4, 1, inventoryNull);

        Item item1 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite " +
                "max des dès de 2 pour toujours !");
        Item item2 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite " +
                "max des dès de 2 pour toujours !");

        Room room1 = new Room("Room Initiale", "Description room1");
        room1.getItems().add(item1);
        room1.getItems().add(item2);
        room1.getCharacters().add(perso1);
        room1.getCharacters().add(perso2);

        Room room2 = new Room("Room Numero 2", "Description room2");

        player.setCurrentRoom(room1);

        //Démarrage jeu
        System.out.println("ERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------");

        //Début jeu
        while (!end) {
            System.out.println("Vous êtes dans la " + player.getCurrentRoom().getName() + ".\n" +
                    "Essayez d'en sortir !");
            player.getCurrentRoom().presentRoom();
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
                        quit();
                        break;
                    case ("inventory"):
                        player.manageInventory();
                        break;
                    case ("speak"):
                        System.out.println("Avec qui voulez-vous parler ?");
                        String motEntré2 = sc.nextLine();
                        if (Character.containCharac(motEntré2, room1.getCharacters())) {
                            if(Character.getCharac(motEntré2, room1.getCharacters()).isWicked()){
                                System.out.println(Character.getCharac(motEntré2, room1.getCharacters()).getName() +
                                        " n'aime pas quand on lui parle..\n" +
                                        "Cela va se régler en combat !");
                                player.fight(Character.getCharac(motEntré2, room1.getCharacters()), 2);
                            }else{
                                System.out.println(Character.getCharac(motEntré2, room1.getCharacters()).getName() +
                                " veut vous aider ! Ne le combattez pas !");
                            }
                        } else {
                            System.out.println("Ce personnage n'est pas dans cette salle.");
                        }
                        break;
                    case ("take"):
                        System.out.println("Quel objet voulez-vous récupérer ?");
                        String motEntré3 = sc.nextLine();
                        if (Item.containItem(motEntré3, room1.getItems())) {
                            player.takeItem(Item.getItem(motEntré3, room1.getItems()), room1);
                        } else {
                            System.out.println("Cet item n'est pas dans cette salle.");

                        }
                        break;
                    case ("look room"):
                        player.getCurrentRoom().presentRoom();
                        break;
                    case ("next room"):
                        if (monsterBeaten) {
                            player.setCurrentRoom(room2);
                            System.out.println("Vous venez de changer de salle, observez la bien ...");
                        }else{
                            System.out.println("Un ou plusieurs virus bloquent l'accès à la salle suivante,\n" +
                                    "faites preuve d'assurance !");
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
                "   \"quit\" : quit le jeu sans sauvegarder\n" +
                "   \"inventory\" : accéder à l'inventaire\n" +
                "   \"take\" : prendre un objet présent dans la salle\n" +
                "   \"speak\" : parler à un personnage présent dans la salle\n" +
                "   \"look room\" : regarder ce qu'il y a dans la salle\n" +
                "   \"next room\" : aller à la salle suivante");
    }

    static void quit() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
