package indivus.cosmos.model.server.reply;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyDetailResult implements Serializable {
    public ArrayList<ReplyDetail> result;

    public class ReplyDetail implements Serializable{
        public int comment_detail_id;
        public int comment_id;
        public int ID;
        public String contents;
        public String comment_date;
        public int like_counts;
        public String jobs;
        public String profile_photo;
        public String username;

        public int getComment_detail_id() {
            return comment_detail_id;
        }

        public int getComment_id() {
            return comment_id;
        }

        public int getID() {
            return ID;
        }

        public String getContents() {
            return contents;
        }

        public String getComment_date() {
            return comment_date;
        }

        public int getLike_counts() {
            return like_counts;
        }

        public String getJobs() {
            return jobs;
        }

        public String getProfile_photo() {
            return profile_photo;
        }

        public String getUsername() {
            return username;
        }
    }
}
