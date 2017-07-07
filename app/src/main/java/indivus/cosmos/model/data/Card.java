package indivus.cosmos.model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by seowo on 2017-06-26.
 */

public class Card implements Serializable{
    public int ID;
    public int category_id;
    public int ID_creator;
    public int post_id;

    public String title;
    public String sub_title;

    public String explain;
    public String content;
    public String comment;

    public String card_cover;

    public int content_type;

    public String posting_date;

    public int like_counts;
    public int collection_counts;

    public String profile_photo;
    public int comment_counts;

    public String username;
    public String jobs;

    public String category_name;
    public ArrayList<Keycard> keycard;

    public Card(int ID, int category_id, int ID_creator
            , int post_id, String title, String sub_title
            , String explain, String content, String comment
            , String card_cover, int content_type, String posting_date
            , int like_counts, int collection_counts, String profile_photo
            , int comment_counts, String username, String jobs
            , String category_name, ArrayList<Keycard> keycard) {

        this.ID = ID;
        this.category_id = category_id;
        this.ID_creator = ID_creator;
        this.post_id = post_id;
        this.title = title;
        this.sub_title = sub_title;
        this.explain = explain;
        this.content = content;
        this.comment = comment;
        this.card_cover = card_cover;
        this.content_type = content_type;
        this.posting_date = posting_date;
        this.like_counts = like_counts;
        this.collection_counts = collection_counts;
        this.profile_photo = profile_photo;
        this.comment_counts = comment_counts;
        this.username = username;
        this.jobs = jobs;
        this.category_name = category_name;
        this.keycard = keycard;
    }

    public class Keycard implements Serializable {
        public int keycard_id;
        public String keycard_name;
    }
}
