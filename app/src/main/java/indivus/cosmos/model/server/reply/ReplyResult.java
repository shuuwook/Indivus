package indivus.cosmos.model.server.reply;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyResult implements Serializable{
    public ArrayList<Reply> result;

    public class Reply implements Serializable{
        public int comment_id;
        public int post_id;
        public int ID;
        public String contents;
        public String comment_date;
        public int like_counts;
        public String jobs;
        public String profile_photo;
        public String username;
        public int commentdetail_counts;

        public int getCommentId() {
            return comment_id;
        }

        public int getPostId() {
            return post_id;
        }

        public String getContents() {
            return contents;
        }

        public String getCommentDate() {
            return comment_date;
        }

        public int getLikeCounts() { return like_counts; }

        public String getProfilePhoto() { return profile_photo; }

        public String getUsername(){ return username; }

        public int getCommentdetail_counts() { return commentdetail_counts; }
    }
}
