package indivus.cosmos.model.server;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-06-30.
 */

public class PostData {
    String title;
    String synopsis;

    ArrayList<PostContent> contents;

    String creator_image;
    String creator_title;
    String creator_job;

    String creator_comment;

    String light_count;
    String comment_count;
    String collect_count;


    class PostContent{
        boolean type;
        String src;
    }
}
