package indivus.cosmos.model.server.reply;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyWrite {
    public int post_id;
    public String contents;

    public ReplyWrite(int post_id, String contents) {
        this.post_id = post_id;
        this.contents = contents;
    }
}
