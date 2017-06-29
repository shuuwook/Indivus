package indivus.cosmos;

/**
 * Created by Administrator on 2017-06-29.
 */

public class PostItems {
    int img;
    String text;

    public PostItems(){}

    public PostItems (String text){
        this.text = text;
    }
    public PostItems (int img){
        this.img = img;
    }
    public PostItems (int img, String text){
        this.img = img;
        this.text = text;
    }

    public int getImg() { return img; }
    public String getText(){
        return text;
    }



}
