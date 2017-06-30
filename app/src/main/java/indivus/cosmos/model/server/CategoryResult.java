package indivus.cosmos.model.server;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-06-29.
 */

public class CategoryResult {
    public ArrayList<Category> result;

    public class Category{
        public int category_id;
        public String category_name;
        public int category_like;
        public String category_image;

        public String getCategoryImage() {
            return category_image;
        }
    }
}
