package indivus.cosmos.model.server.post;

import java.lang.reflect.Array;
import java.util.ArrayList;

import indivus.cosmos.model.data.PostContent;
import indivus.cosmos.model.data.PostRecommend;

/**
 * Created by seowo on 2017-06-30.
 */

public class PostResult {
    public int ID_creator;
    public int post_id;
    public String title;
    public String sub_title;

    public String explain;

    public String comment;

    public String card_cover;

    //단편, 시리즈
    public int content_type;

    public String posting_date;
    public int like_counts;
    public int category_id;
    public int collection_counts;

    public String username;
    public String jobs;

    public String profile_photo;

    public int comment_counts;

    public ArrayList<PostRecommend> recommends;

    public ArrayList<PostContent> contents;

    public PostResult(int ID_creator, int post_id, String title, String sub_title
            , String explain, ArrayList<PostContent> contents, String comment
            , String card_cover, int content_type, String posting_date, int like_counts
            , int category_id, int collection_counts, String username, String jobs
            , String profile_photo, int comment_counts, ArrayList<PostRecommend> recommends) {
        this.ID_creator = ID_creator;
        this.post_id = post_id;
        this.title = title;
        this.sub_title = sub_title;
        this.explain = explain;
        this.contents = contents;
        this.comment = comment;
        this.card_cover = card_cover;
        this.content_type = content_type;
        this.posting_date = posting_date;
        this.like_counts = like_counts;
        this.category_id = category_id;
        this.collection_counts = collection_counts;
        this.username = username;
        this.jobs = jobs;
        this.profile_photo = profile_photo;
        this.comment_counts = comment_counts;
        this.recommends = recommends;
    }
}
