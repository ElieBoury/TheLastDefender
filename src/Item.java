public class Item {

    int bonus;
    int malus;
    boolean activable;
    boolean estRecupéré;
    String description;

    private Item(int bonus, int malus, boolean activable, boolean estRecupéré, String description) {
        this.bonus = bonus;
        this.malus = malus;
        this.activable = activable;
        this.estRecupéré = estRecupéré;
        this.description = description;
    }

    public int getBonus() {

        return bonus;
    }

    public int getMalus() {
        return malus;
    }

    public boolean isActivable() {
        return activable;
    }

    public boolean isEstRecupéré() {
        return estRecupéré;
    }

    public String getDescription() {
        return description;
    }

    public void setEstRecupéré(boolean estRecupéré) {
        this.estRecupéré = estRecupéré;
    }
}
