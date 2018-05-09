import java.util.Scanner;
import java.util.*;
import javax.swing.JFrame;

public class Game {
    //BITE
    /**
     * Main loop
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setTitle("Save your Computer");
        window.setSize(1000, 400);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        launch();
    }

    /**
     * Loop of the game
     */
    static void launch() {

        boolean endGame = false;
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        initialize(rooms, characters, items);

        //Start of the game
        System.out.println("ERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------");
        System.out.println("Vous êtes dans la " + characters.get(0).getCurrentRoom().getName() + ".\n" +
                "Essayez d'en sortir !\n");
        System.out.println("Pour ramasser un objet, tapez \"take\".\n" +
                "Pour parler à un personnage, tapez \"speak\".\n" +
                "Pour connaître d'autres commandes, tapez \"help\".");

        //Start of the game loop
        while (!endGame) {
                mainAct(rooms, characters, items);
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

    static void initialize(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items) {
        ArrayList<Item> inventoryNull = new ArrayList<>();
        Character player = new Character(
                "AVAST", false, "C'est vous !", true, 1, 6, 1, inventoryNull);
        characters.add(player);
        Character perso1 = new Character(
                "Antivira", false, "Antivira est un anti virus", false, 1, 6, 1, inventoryNull);
        characters.add(perso1);
        Character perso2 = new Character(
                "JigSaw", true, "JigSaw est un virus méchant", false, 1, 4, 1, inventoryNull);
        characters.add(perso2);
        Item item0 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite " +
                "max des dès de 2 pour toujours !");
        items.add(item0);
        Item item1 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite " +
                "max des dès de 2 pour toujours !");
        items.add(item1);


        Room room0 = new Room(0, "Salle initiale", "Salle de préparation");
        room0.getItems().add(item0);
        room0.getItems().add(item1);
        room0.getCharacters().add(perso1);
        room0.getCharacters().add(perso2);
        room0.getLockedCharacters().add(perso2);
        rooms.add(room0);

        Room room1 = new Room(1, "Salle infernale", "Description salle infernale");
        rooms.add(room1);

        player.setCurrentRoom(room0);
    }

    static void mainAct(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items) {
        //Ask of action from the player
        System.out.println("Agissez !");

        Scanner sc = new Scanner(System.in);
        String wordRead = sc.nextLine();

        switch (wordRead) {
            case ("help"):
                help();
                break;
            case ("quit"):
                quit();
                break;
            case ("inventory"):
                characters.get(0).manageInventory();
                break;
            case ("speak"):
                manageSpeak(rooms, characters, items);
                break;
            case ("take"):
                manageTake(rooms, characters, items);
                break;
            case ("look room"):
                characters.get(0).getCurrentRoom().presentRoom();
                break;
            case ("previous room"):
                managePreviousRoom(rooms, characters, items);
                break;
            case ("next room"):
                manageNextRoom(rooms, characters, items);
                break;
            default:
                System.out.println("Ce que vous avez demandé n'est pas valide, tapez help.");
        }
    }

    static void manageSpeak(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        Scanner sc = new Scanner(System.in);
        System.out.println("Avec qui voulez-vous parler ?");
        String wordRead = sc.nextLine();
        if (Room.containCharac(wordRead, rooms.get(0).getCharacters())) {
            if (Room.getCharac(wordRead, rooms.get(0).getCharacters()).isWicked()) {
                System.out.println(Room.getCharac(wordRead, rooms.get(0).getCharacters()).getName() +
                        " n'aime pas quand on lui parle..\n" +
                        "Cela va se régler en combat !");
                characters.get(0).fight(Room.getCharac(wordRead, rooms.get(0).getCharacters()), 2);
            } else {
                System.out.println(Room.getCharac(wordRead, rooms.get(0).getCharacters()).getName() +
                        " veut vous aider mais ne sait toujours pas comment, revenez plus tard !");
            }
        } else {
            System.out.println("Ce personnage n'est pas dans cette salle.");
        }
    }

    static void manageTake(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel objet voulez-vous récupérer ?");
        String wordRead = sc.nextLine();
        if (Item.containItem(wordRead, rooms.get(0).getItems())) {
            characters.get(0).takeItem(Item.getItem(wordRead, rooms.get(0).getItems()));
        } else {
            System.out.println("Cet item n'est pas dans cette salle.");

        }
    }

    static void managePreviousRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (rooms.indexOf(characters.get(0).getCurrentRoom()) != 0) {
            characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) - 1));
            System.out.println("Vous venez de changer de salle, observez la bien ...");
        } else {
            System.out.println("Vous êtes dans la première salle, il n'y en a pas de précédente !");
        }
    }

    static void manageNextRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (characters.get(0).getCurrentRoom().isUnlocked()) {
            if (rooms.indexOf(characters.get(0).getCurrentRoom()) == rooms.size()) {
                characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) + 1));
                System.out.println("Vous venez de changer de salle, observez la bien ...");
            } else {
                System.out.println("Vous êtes dans la dernière salle, il n'y en a pas de suivante !");
            }
        } else {
            System.out.println("Un ou plusieurs virus bloquent l'accès à la salle suivante,\n" +
                    "faites preuve d'assurance !");
        }
    }
}
