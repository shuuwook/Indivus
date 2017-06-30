package indivus.cosmos.model.data;

import java.util.ArrayList;

/**
 * Created by seowo on 2017-06-26.
 */

public class Card {
    public String card_Image;

    public String card_title;
    public String card_sub_title;

    public String card_time;

    public ArrayList<String> card_tag;

    public String card_comment;

    public String card_creator_image;
    public String card_creator_sub_title;
    public String card_creator_title;

    public int light_count;
    public int comment_count;
    public int collect_cont;

    public Card(String card_Image, String card_title
            , String card_sub_title, String card_time
            , ArrayList<String> card_tag, String card_comment
            , String card_creator_image, String card_creator_sub_title
            , String card_creator_title
            , int light_count, int comment_count, int collect_cont) {

        this.card_Image = card_Image;
        this.card_title = card_title;
        this.card_sub_title = card_sub_title;
        this.card_time = card_time;
        this.card_tag = card_tag;
        this.card_comment = card_comment;
        this.card_creator_image = card_creator_image;
        this.card_creator_sub_title = card_creator_sub_title;
        this.card_creator_title = card_creator_title;
        this.light_count = light_count;
        this.comment_count = comment_count;
        this.collect_cont = collect_cont;
    }
}
