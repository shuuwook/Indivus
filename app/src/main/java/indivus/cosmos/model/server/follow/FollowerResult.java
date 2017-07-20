package indivus.cosmos.model.server.follow;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-08.
 */

public class FollowerResult {
    public ArrayList<Follower> result;

    public class Follower{
        public int ID;
        public int follower_id;
        public String follower_date;
        public int following_boolean;
        public String jobs;
        public String profile_photo;
        public String username;
    }
}
