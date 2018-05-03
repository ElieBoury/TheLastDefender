import java.util.Scanner;
import java.util.*;

public class Game {

    /**
     * Main loop
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Loop of the game
     */
    public static void launch() {
        boolean endGame = false;
        boolean endRoom;

        ArrayList<Item> inventoryNull = new ArrayList<>();

        Character player = new Character(
                "AVAST", false, "C'est vous !", true, 1, 6, 1, inventoryNull);
        Character perso1 = new Character(
                "Antivira", false, "Antivira est un anti virus", false, 1, 6, 1, inventoryNull);
        Character perso2 = new Character(
                "JigSaw", true, "JigSaw est un virus méchant", false, 1, 4, 1, inventoryNull);

        Item item1 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite " +
                "max des dès de 2 pour toujours !");
        Item item2 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite " +
                "max des dès de 2 pour toujours !");

        ArrayList<Room> rooms = new ArrayList<>();
        Room room1 = new Room(0, "Salle initiale", "Salle de préparation");
        room1.getItems().add(item1);
        room1.getItems().add(item2);
        room1.getCharacters().add(perso1);
        room1.getCharacters().add(perso2);
        room1.getLockedCharacters().add(perso2);
        rooms.add(room1);

        Room room2 = new Room(1, "Salle infernale", "Description salle infernale");
        rooms.add(room2);

        player.setCurrentRoom(rooms.get(0));

        //Start of the game
        System.out.println("ERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------");

        //Start of the game loop
        while (!endGame) {
            System.out.println("Vous êtes dans la " + player.getCurrentRoom().getName() + ".\n" +
                    "Essayez d'en sortir !\n");
            System.out.println("Pour ramasser un objet, tapez \"take\".\n" +
                    "Pour parler à un personnage, tapez \"speak\".\n" +
                    "Pour connaître d'autres commandes, tapez \"help\".");
            endRoom = false;
            while (!endRoom) {
                //Ask of action from the player
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
                        if (Room.containCharac(motEntré2, room1.getCharacters())) {
                            if (Room.getCharac(motEntré2, room1.getCharacters()).isWicked()) {
                                System.out.println(Room.getCharac(motEntré2, room1.getCharacters()).getName() +
                                        " n'aime pas quand on lui parle..\n" +
                                        "Cela va se régler en combat !");
                                player.fight(Room.getCharac(motEntré2, room1.getCharacters()), 2);
                            } else {
                                System.out.println(Room.getCharac(motEntré2, room1.getCharacters()).getName() +
                                        " veut vous aider mais ne sait toujours pas comment, revenez plus tard !");
                            }
                        } else {
                            System.out.println("Ce personnage n'est pas dans cette salle.");
                        }
                        break;
                    case ("take"):
                        System.out.println("Quel objet voulez-vous récupérer ?");
                        String motEntré3 = sc.nextLine();
                        if (Item.containItem(motEntré3, room1.getItems())) {
                            player.takeItem(Item.getItem(motEntré3, room1.getItems()));
                        } else {
                            System.out.println("Cet item n'est pas dans cette salle.");

                        }
                        break;
                    case ("look room"):
                        player.getCurrentRoom().presentRoom();
                        break;
                    case ("previous room"):
                        if (rooms.indexOf(player.getCurrentRoom()) != 0) {
                            player.setCurrentRoom(rooms.get(rooms.indexOf(player.getCurrentRoom()) - 1));
                            endRoom = true;
                            System.out.println("Vous venez de changer de salle, observez la bien ...");
                        } else {
                            System.out.println("Vous êtes dans la première salle, il n'y en a pas de précédente !");
                        }
                        break;
                    case ("next room"):
                        if (player.getCurrentRoom().isUnlocked()) {
                            if (rooms.indexOf(player.getCurrentRoom()) == rooms.size()) {
                                player.setCurrentRoom(rooms.get(rooms.indexOf(player.getCurrentRoom()) + 1));
                                endRoom = true;
                                System.out.println("Vous venez de changer de salle, observez la bien ...");
                            } else {
                                System.out.println("Vous êtes dans la dernière salle, il n'y en a pas de suivante !");
                            }
                        } else {
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

    /**
     * Manage an ask of help
     */
    static void help() {
        System.out.println("But : sortir de cette salle.\n" +
                "Actions possibles :\n" +
                "   \"quit\" : quit le jeu sans sauvegarder\n" +
                "   \"inventory\" : accéder à l'inventaire\n" +
                "   \"take\" : prendre un objet présent dans la salle\n" +
                "   \"speak\" : parler à un personnage présent dans la salle\n" +
                "   \"look room\" : regarder ce qu'il y a dans la salle\n" +
                "   \"previous room\" : aller à la salle précédente\n" +
                "   \"next room\" : aller à la salle suivante");
    }

    /**
     * Quit the game
     */
    static void quit() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
