package indivus.cosmos.model.server.explorer;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-06.
 */

public class ExplorerContentResult {

    public ArrayList<ExplorerContent> result;

    public class ExplorerContent{
        public int ID_creator;
        public int post_id;
        public String title;
        public String sub_title;
        public String explain;
        public String comment;
        public String card_cover;
        public int content_type;
        public String posting_date;
        public int like_counts;
        public int category_id;
        public int collection_counts;
        public int view_counts;
        public String jobs;
        public String profile_photo;
        public String username;
        public int comment_counts;
    }
}
