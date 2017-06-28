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

public class Card extends Fragment{
    String image;
    String name;
    String sub;
    String comment;

    public Card(String image, String name, String sub, String comment) {
        this.image = image;
        this.name = name;
        this.sub = sub;
        this.comment = comment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View card_view = inflater.inflate(R.layout.card_home, container, false);

        return card_view;
    }
}
