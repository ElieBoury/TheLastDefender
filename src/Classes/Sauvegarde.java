package Classes;

import java.io.*;

public class Sauvegarde {

    final static String DefaultPathCharacter = "src/Sauvegarde/Default/Classes.Character.csv";
    final static String DefaultPathItem = "src/Sauvegarde/Default/Classes.Item.csv";
    final static String DefaultPathRoom = "src/Sauvegarde/Default/Classes.Room.csv";

    static void gestionSauvegarde(){
        importItem();
        importCharacter();
        importRoom();
        sauvegardeItems();
        sauvegardeCharacter();
        sauvegardeRoom();
    }
    static void sauvegardeCharacter() {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(DefaultPathCharacter)));
            myFile.write("Name;Wicked;Description;Player;LowerDice;UpperDice;nbDice;Items;");
            myFile.newLine();
            for (Character myCharacter: Game.characters) {
                myFile.write(myCharacter.characterToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sauvegardeItems() {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(DefaultPathItem)));

            myFile.write("Name;Bonus;Malus;toActivate;Taken;Description;");
            myFile.newLine();
            for (Item myItem: Game.items) {
                myFile.write(myItem.itemToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void sauvegardeRoom() {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(DefaultPathRoom)));
            myFile.write("ID;Name; Description;IsUnlocked; Items; Characters; LockedCharacters; lockedItems;");
            myFile.newLine();
            for (Room myRoom: Game.rooms) {
                myFile.write(myRoom.roomToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void importCharacter() {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(DefaultPathCharacter));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Character.CSVToCharacter(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void importItem() {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(DefaultPathItem));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Item.CSVToItem(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void importRoom() {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(DefaultPathRoom));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Room.CSVToRoom(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
