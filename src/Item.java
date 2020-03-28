import java.awt.*;

public class Item {
    private String name;
    private String description;
    private String image= "";

    void setName(String name) { this.name = name; }

    void setDescription(String description) { this.description = description; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

}
