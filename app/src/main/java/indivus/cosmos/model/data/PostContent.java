package indivus.cosmos.model.data;

/**
 * Created by seowo on 2017-07-01.
 */

public class PostContent {
    public String type;
    public String contents;

    public PostContent(String type, String contents) {
        this.type = type;
        this.contents = contents;
    }

    public String getType(){
        return type;
    }

    public String getContent() {
        return contents;
    }
}
