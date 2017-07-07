package indivus.cosmos.model.server.series;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-01.
 */

public class SeriesResult {
    public ArrayList<PostSeries> result;

    public class PostSeries implements Serializable{
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
    }
}
