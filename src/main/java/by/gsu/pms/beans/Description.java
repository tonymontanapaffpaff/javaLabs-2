package by.gsu.pms.beans;

public class Description {
    private final String id;
    private final String main;
    private final String description;
    private final String icon;

    public Description(String id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
