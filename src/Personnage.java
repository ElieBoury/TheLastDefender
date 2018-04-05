import java.util.ArrayList;
import java.util.Scanner;

public class Personnage extends Description {
    private boolean joueur;
    private boolean persoClé; //Vrai si c'est un perso nécessaire à battre pour passer à la salle suivante.
    private int debutDes;
    private int finDes;
    private int nbDes;
    private ArrayList<Item> inventaire;
    private Salle currentRoom;

    public Personnage(String nom, String description, boolean joueur, boolean persoClé, int debutDes, int finDes, int nbDes, ArrayList<Item> inventaire) {
        super(nom, description);
        this.joueur = joueur;
        this.debutDes = debutDes;
        this.finDes = finDes;
        this.nbDes = nbDes;
        this.inventaire = inventaire;
    }


    public Boolean getPersoClé() {
        return persoClé;
    }

    public void setPersoClé(Boolean persoClé) {
        this.persoClé = persoClé;
    }

    public void setJoueur(Boolean joueur) {
        this.joueur = joueur;
    }

    public void setInventaire(ArrayList<Item> inventaire) {
        this.inventaire = inventaire;
    }

    public Salle getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Salle currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Boolean getJoueur() {
        return joueur;
    }

    public int getDebutDes() {
        return debutDes;
    }

    public int getFinDes() {
        return finDes;
    }

    public int getNbDes() {
        return nbDes;
    }

    public ArrayList<Item> getInventaire() {
        return inventaire;
    }

    public void setDebutDes(int debutDes) {
        this.debutDes = debutDes;
    }

    public void setFinDes(int finDes) {
        this.finDes = finDes;
    }

    public void setNbDes(int nbDes) {
        this.nbDes = nbDes;
    }

    public void changeDeSalle(Salle salle) {

    }

    public static boolean contientPerso(String nom, ArrayList<Personnage> persos) {
        for (Personnage perso : persos) {
            if (nom.equals(perso.getNom())) {
                return true;
            }
        }
        return false;
    }

    public static Personnage obtenirPerso(String nom, ArrayList<Personnage> persos) {
        for (Personnage perso : persos) {
            if (nom.equals(perso.getNom())) {
                return perso;
            }
        }
        return null;
    }

    public void gérerInventaire() {
        boolean done = false;
        if (this.inventaire.size() == 0) {
            System.out.println("Votre inventaire est vide, récupérez des items !");
        } else {
            System.out.println("Voici les différents objets présents dans votre inventaire :");
            for (Item item : this.inventaire) {
                System.out.println("   " + item.getNom());
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
                        String motEntré3 = sc.nextLine();
                        if (Item.contientItem(motEntré3, this.inventaire)) {
                            System.out.println("Si vous utilisez cet objet, vous le perdrez, en êtes vous sûr ? (yes)/(no)");
                            String motEntré4 = sc.nextLine();
                            if (motEntré4.equals("yes")) {
                                this.activerItem(Item.obtenirItem(motEntré3, this.inventaire));
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
                        if (Item.contientItem(motEntré2, this.inventaire)) {
                            System.out.println(Item.obtenirItem(motEntré2, this.inventaire).getDescription());
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


    public void gérerItem(Item item) {
        System.out.println("Gestion de la récupération de l'item");
        if (this.inventaire.size() == 0) {
            System.out.println("Votre inventaire est vide.");
        } else {

        }
    }

    public void récupérerItem(Item item, Salle salle) {
        if (item == null) {
            System.out.println("Item null");
            return;
        }
        System.out.println("Êtes-vous sûr de vouloir récupérer cet item ? (yes) / (no)");
        Scanner sc = new Scanner(System.in);
        String motEntré = sc.nextLine();
        if (motEntré.equals("yes")) {
            this.inventaire.add(item);
            salle.getItems().remove(item);
            System.out.println("Vous venez de récupérer un " + item.getNom() + " !\n" +
                    "Cet item est désormais dans votre inventaire.");
        } else {
            System.out.println("Action annulée.");
        }
    }

    public void activerItem(Item item) {
        if (item.getBonus() != 0) {
            if (item.getBonus() > 0) {
                this.nbDes += item.getBonus();
                System.out.println("Votre nombre de dés vient d'augmenter de " + item.getBonus() + " !\n" +
                        "Il est maintenant de " + this.nbDes + ".");
            } else {
                this.finDes += (item.getBonus() * -1);
                System.out.println("La borne maximale de vos dés vient d'augmenter de " + item.getBonus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.finDes + ".");
            }
        }
        if (item.getMalus() != 0) {
            if (item.getMalus() > 0) {
                this.nbDes -= item.getMalus();
                System.out.println("Votre nombre de dés vient de diminuer de " + item.getMalus() + " !\n" +
                        "Il est maintenant de " + this.nbDes + ".");
            } else {
                this.finDes += (item.getMalus() * -1);
                System.out.println("La borne maximale de vos dés vient de diminuer de " + item.getMalus() * -1 + " !\n" +
                        "Elle est maintenant de " + this.finDes + ".");
            }
        }
        this.inventaire.remove(item);
    }

    public void fight(Personnage adversaire, int nbManches) {
        int ptsJoueur = 0, ptsIa = 0, lancéJoueur, lancéIa, mancheActuelle = 1;
        boolean away = false;
        System.out.println("Combat entre " +
                this.getNom() + " et " + adversaire.getNom() + " en " + nbManches + " manches !");
        while (ptsJoueur < nbManches && ptsIa < nbManches && !away) {
            System.out.println("Manche " + mancheActuelle);
            System.out.println(this.getNom() + " : " + ptsJoueur + " - " + ptsIa + " : " + adversaire.getNom());
            System.out.println("Que voulez vous faire ?\n" +
                    "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
            Scanner sc = new Scanner(System.in);
            String motEntré = sc.nextLine();
            switch (motEntré) {
                case ("Attack"):
                    lancéJoueur = Générateur.générerScore(this.getDebutDes(), this.getFinDes());
                    lancéIa = Générateur.générerScore(adversaire.getDebutDes(), adversaire.getFinDes());
                    System.out.println("Vous avez obtenu " + lancéJoueur);
                    System.out.println(adversaire.getNom() + " a obtenu " + lancéIa);
                    if (lancéJoueur < lancéIa) {
                        System.out.println(adversaire.getNom() + " gagne cette manche");
                        ptsIa++;
                        mancheActuelle++;
                    } else if (lancéJoueur > lancéIa) {
                        System.out.println("Vous gagnez cette manche");
                        ptsJoueur++;
                        mancheActuelle++;
                    } else {
                        System.out.println("Egalité, la défense gagne, " + adversaire.getNom() + " remporte la manche");
                        ptsIa++;
                        mancheActuelle++;
                    }
                    break;
                case ("Use"):
                    if (this.getInventaire().size() != 0) {
                        System.out.println("Quel item voulez vous activer ?");

                        for (Item items : this.getInventaire()) {
                            System.out.println("   " + items);
                        }
                        String motEntré2 = sc.nextLine();
                        if (Item.contientItem(motEntré2, this.getInventaire())) {
                            this.activerItem(Item.obtenirItem(motEntré2, this.getInventaire()));
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
            if (ptsJoueur > nbManches / 2) {
                System.out.println("Bravo " + this.getNom() + ", vous avez gagné !");
                if(adversaire.getPersoClé()){
                    this.getCurrentRoom().setUnlocked(true);
                    System.out.println("Bonne nouvelle ! Vous venez de battre le virus gardien de la salle,\n" +
                            "vous pouvez à présent accéder à la salle suivante !");
                }
            } else {
                System.out.println(adversaire.getNom() + " a gagné ! Vous avez perdu.");
            }
        } else {
            System.out.println("Vous avez fuit, combat terminé.");
        }
    }

}
