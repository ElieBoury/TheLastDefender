import javax.sound.midi.Soundbank;
import java.util.Objects;
import java.util.Scanner;
import java.util.*;

public class Game {

    public static void main(String[] args) {
        boolean monstreBattu = false;
        ArrayList<Item> inventaireNul = new ArrayList<>();
        /*Salle salleActuelle;
        salleActuelle=salle1;*/
        //Génération des objets
        /*Générateur.générerItems();
        Générateur.générerPersonnages();
        Générateur.générerSalles();*/
        Personnage joueur = new Personnage(
                "AVAST", "C'est vous !", true, 1, 6, 1, inventaireNul);
        Personnage perso1 = new Personnage(
                "Abelson", "Abelson est un virus méchant", false, 1, 6, 1, inventaireNul);
        Personnage perso2 = new Personnage(
                "JigSaw", "JigSaw est un virus méchant", false, 1, 4, 1, inventaireNul);

        Item item1 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite" +
                "max des dès de 2 pour toujours !");
        Item item2 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite" +
                "max des dès de 2 pour toujours !");

        Salle salle1 = new Salle("Salle Initiale", "Description salle1");
        salle1.setDescription("Description salle1");
        salle1.items.add(item1);
        salle1.items.add(item2);
        salle1.personnages.add(perso1);
        salle1.personnages.add(perso2);

        //Démarrage jeu
        System.out.println("*PREMIER MESSAGE*");

        //Début jeu
        System.out.println("Vous êtes dans la " + salle1.nom + " !\n" +
                "Essayez d'en sortir !");
        Salle.présenterSalle(salle1);
        System.out.println("Pour ramasser un objet ou parler avec un personnage, tapez leur nom.\n" +
                "Pour connaître d'autres commandes, tapez help.");
        while (!monstreBattu) {
            //Demande d'action du joueur
            System.out.println("Agissez !");
            Scanner sc = new Scanner(System.in);
            String motEntré = sc.nextLine();

            if (motEntré.equals("help")) {
                aide();
            } else if (motEntré.equals("quit")) {
                quitter();
            } else if (motEntré.equals("inventory")) {
                gérerInventaire(joueur);
            } else if (Personnage.contientPerso(motEntré, salle1.personnages)) {
                gérerCombat(joueur, Personnage.obtenirPerso(motEntré, salle1.personnages), 1);
            } else if (!Objects.equals(Item.obtenirItem(motEntré, salle1.items), null)) {
                gérerItem(joueur, Item.obtenirItem(motEntré, salle1.items));
            } else {
                System.out.println("Ce que vous avez demandé n'est pas valide, tapez help.");
            }
        }
    }

    //Différentes fonctions

    static void aide() {
        System.out.println("HELP");
    }

    static void quitter() {
        System.out.println("Au revoir !");
        System.exit(0);
    }

    static void gérerCombat(Personnage joueur, Personnage ia, int nbManches) {
        int ptsJoueur = 0, ptsIa = 0, lancéJoueur, lancéIa, mancheActuelle = 1;
        boolean away = false;
        System.out.println("Combat entre " +
                joueur.nom + " et " + ia.nom + " en " + nbManches + " manches !");
        while (ptsJoueur < nbManches && ptsIa < nbManches && !away) {
            System.out.println("Manche " + mancheActuelle);
            System.out.println(joueur.nom + " : " + ptsJoueur + " - " + ptsIa + " : " + ia.nom);
            System.out.println("Que voulez vous faire ?\n" +
                    "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
            Scanner sc = new Scanner(System.in);
            String motEntré = sc.nextLine();
            switch (motEntré) {
                case ("Attack"):
                    lancéJoueur = Générateur.générerScore(joueur.debutDes, joueur.finDes);
                    lancéIa = Générateur.générerScore(ia.debutDes, ia.finDes);
                    System.out.println("Vous avez obtenu " + lancéJoueur);
                    System.out.println(ia.nom + "a obtenu " + lancéIa);
                    if (lancéJoueur < lancéIa) {
                        System.out.println(ia.nom + " gagne cette manche");
                        ptsIa++;
                        mancheActuelle++;
                    } else if (lancéJoueur > lancéIa) {
                        System.out.println("Vous gagnez cette manche");
                        ptsJoueur++;
                        mancheActuelle++;
                    } else {
                        System.out.println("Egalité, la défense gagne, " + ia.nom + "remporte la manche");
                        ptsIa++;
                        mancheActuelle++;
                    }
                    break;
                case ("Use"):
                    System.out.println("Quel item voulez vous activer ?");
                    for (Item items : joueur.inventaire) {
                        System.out.println("   " + items);
                    }
                    String motEntré2 = sc.nextLine();
                    if (Item.contientItem(motEntré2, joueur.inventaire)) {
                        Item.activerItem(Item.obtenirItem(motEntré2, joueur.inventaire), joueur);
                    } else {
                        System.out.println("Vous ne possédez pas cet objet");
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
            if (ptsJoueur > nbManches) {
                System.out.println("Bravo " + joueur.nom + ", vous avez gagné !");
            } else {
                System.out.println(ia.nom + " a gagné ! Vous avez perdu.");
            }
        } else {
            System.out.println("Vous avez fuit, combat terminé.");
        }
    }

    static void gérerItem(Personnage joueur, Item item) {
        System.out.println("Gestion de la récupération de l'item");
    }

    static void gérerInventaire(Personnage perso) {
        System.out.println("Gestion de l'inventaire");
    }
}
