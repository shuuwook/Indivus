package indivus.cosmos.presenter;

import java.util.ArrayList;

import indivus.cosmos.model.server.signup.CategoryResult;

/**
 * Created by seowo on 2017-06-30.
 */

public interface CategoriesCallBack {
    void getCategories(ArrayList<CategoryResult.Category> categories);
}
