package indivus.cosmos.model.server.follow;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-08.
 */

public class FollowingResult {
    public ArrayList<Following> result;

    public class Following{
        public int ID;
        public int following_id;
        public String following_date;
        public int following_boolean;
        public String jobs;
        public String profile_photo;
        public String username;
    }
}
