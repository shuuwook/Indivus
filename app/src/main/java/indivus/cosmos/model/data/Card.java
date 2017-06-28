package indivus.cosmos.model.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-26.
 */

public class Card {
    public int post_id;
    public String image;
    public String name;
    public String sub;
    public String comment;

    public int getPostId(){
        return post_id;
    }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSub() {
        return sub;
    }

    public String getComment() {
        return comment;
    }
}
