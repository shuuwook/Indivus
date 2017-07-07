package indivus.cosmos.model.server.explorer;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-06.
 */

public class ExplorerCreatorResult {
    public ArrayList<ExplorerCreator> result;

    public class ExplorerCreator{
        public int ID;
        public String profile_photo;
        public String name;
        public String jobs;
        public String birth;
        public String tel;
        public String active_Area;
        public int like_counts;
        public String condition_message;
        public String awards;
        public int gender;
        public String page;
        public String background_Image;
        public String username;
        public int follower_counts;
    }
}
