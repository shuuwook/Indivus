package indivus.cosmos.model.data;

/**
 * Created by seowo on 2017-07-01.
 */

public class PostButton {
    public int bulb_count;
    public int reply_count;
    public int collect_count;

    public PostButton(int bulb_count, int reply_count, int collect_count) {
        this.bulb_count = bulb_count;
        this.reply_count = reply_count;
        this.collect_count = collect_count;
    }

    public int getBulb_count() {
        return bulb_count;
    }

    public int getReply_count() {
        return reply_count;
    }

    public int getCollect_count() {
        return collect_count;
    }
}
