public class Générateur {

    static int générerScore(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

    static void générerPersonnages() {
       /* Personnage perso = new Personnage("AVAST", "C'est vous !", true, 1, 6, 1);
        Personnage perso1 = new Personnage("Abelson", "Abelson est un virus gentil", false, 1, 4, 1);*/
    }

    static void générerItems() {
        Item item1 = new Item("Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite" +
                "max des dès de 2 pour toujours !");
        Item item2 = new Item("Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite" +
                "max des dès de 2 pour toujours !");
    }

    static void générerSalles() {
        Salle salle1 = new Salle("Salle Initiale", "Description salle1");
        salle1.setDescription("Description salle1");
        /*salle1.items.add(item1);
        salle1.items.add(item2);
        salle1.personnages.add(perso1);*/
    }
}
