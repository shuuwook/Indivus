package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.model.data.Card;

/**
 * Created by seowo on 2017-06-28.
 */

public class HomeCardFragment extends Fragment{
    String image;
    String name;
    String sub;
    String comment;

    public HomeCardFragment(Card card) {
        this.image = card.getImage();
        this.name = card.getName();
        this.sub = card.getSub();
        this.comment = card.getComment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View card_view = inflater.inflate(R.layout.card_home, container, false);

        return card_view;
    }
}
