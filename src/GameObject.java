public abstract class GameObject {
    private String name;
    private String description;

    /**
     * Constructor
     * @param name the name of the object
     * @param description the description of the object
     */
    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * name getter
     * @return the name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * description getter
     * @return the description of the object
     */
    public String getDescription() {
        return description;
    }

    /**
     * description setter
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
