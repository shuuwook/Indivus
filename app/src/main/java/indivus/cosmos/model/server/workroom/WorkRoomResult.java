package indivus.cosmos.model.server.workroom;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-06.
 */

public class WorkRoomResult {
    public ArrayList<WorkRoomSeries> result;

    public class WorkRoomSeries{
        public int post_id;
        public int ID_creator;
        public String series_name;
        public String card_cover;
        public int series_counts;
    }
}
