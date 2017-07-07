package indivus.cosmos.model.server.category;

/**
 * Created by seowo on 2017-07-05.
 */

public class CreateKeyCardResult {
    public int keycard_id;
    public String keycard_name;
    public int like_counts;
    public String keycard_image;
    public String keycard_color;

    public int getKeycard_id() {
        return keycard_id;
    }

    public String getKeycard_name() {
        return keycard_name;
    }

    public int getLike_counts() {
        return like_counts;
    }

    public String getKeycard_image() {
        return keycard_image;
    }

    public String getKeycard_color(){ return keycard_color; }
}
