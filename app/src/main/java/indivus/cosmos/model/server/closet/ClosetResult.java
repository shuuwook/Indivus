package indivus.cosmos.model.server.closet;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-07.
 */

public class ClosetResult {
    public ArrayList<Closet> result;

    public class Closet{
        public String type;
        public int card_id;
        public String card_name;
        public String image;
    }
}
