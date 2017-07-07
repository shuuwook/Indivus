package indivus.cosmos.model.data;

import java.io.Serializable;

/**
 * Created by seowo on 2017-07-05.
 */

public class Content implements Serializable{
    public int type;
    public String src;

    public Content(int type, String src) {
        this.type = type;
        this.src = src;
    }
}
