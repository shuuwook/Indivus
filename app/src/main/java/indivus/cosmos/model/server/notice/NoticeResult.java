package indivus.cosmos.model.server.notice;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-06.
 */

public class NoticeResult {
    public ArrayList<Notice> result;

    public class Notice{
        public int notice_id;
        public int opponent_ID;
        public String date;
        public String username;
        public String profile_photo;
        public String to_type;
        public String to_id;
        public String from_type;
        public String from_id;
        public String text;
    }
}
