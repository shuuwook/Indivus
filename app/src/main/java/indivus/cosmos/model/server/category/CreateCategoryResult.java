package indivus.cosmos.model.server.category;

/**
 * Created by seowo on 2017-07-05.
 */

public class CreateCategoryResult {
    public int category_id;
    public String category_name;
    public int category_like;
    public String category_image;
    public String category_color;

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public int getCategory_like() {
        return category_like;
    }

    public String getCategory_image() {
        return category_image;
    }

    public String getCategory_color(){ return category_color; }
}
