package indivus.cosmos.model.server.collection;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-07-07.
 */

public class CollectionResult {
    public ArrayList<Collection> result;

    public class Collection{
        public int collection_id;
        public String collection_cover;
        public String collection_name;
        public String collection_date;
        public int ID;
        public int collection_counts;
    }
}
