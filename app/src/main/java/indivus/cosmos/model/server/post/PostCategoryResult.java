package indivus.cosmos.model.server.post;


import java.util.ArrayList;

import indivus.cosmos.model.server.category.CreateCategoryResult;
import indivus.cosmos.model.server.category.CreateKeyCardResult;

/**
 * Created by seowo on 2017-07-05.
 */

public class PostCategoryResult {
    public ArrayList<CreateCategoryResult> category_result;
    public ArrayList<CreateKeyCardResult> keycard_result;
}
