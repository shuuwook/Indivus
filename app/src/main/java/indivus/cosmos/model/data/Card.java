package indivus.cosmos.model.data;

/**
 * Created by seowo on 2017-06-26.
 */

public class Card {
    String image;
    String name;
    String sub;
    String comment;

    public Card(String image, String name, String sub, String comment) {
        this.image = image;
        this.name = name;
        this.sub = sub;
        this.comment = comment;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSub() {
        return sub;
    }

    public String getComment() {
        return comment;
    }
}
