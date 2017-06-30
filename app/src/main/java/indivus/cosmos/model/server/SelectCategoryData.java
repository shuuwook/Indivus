package indivus.cosmos.model.server;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-06-30.
 */

public class SelectCategoryData {
    public int id;
    public ArrayList<Integer> categories;

    public SelectCategoryData(int id, ArrayList<Integer> categories) {
        this.id = id;
        this.categories = categories;
    }
}
