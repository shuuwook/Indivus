package indivus.cosmos.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import indivus.cosmos.HomeCardFragment;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.Card;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.post.CardCountData;
import indivus.cosmos.model.server.post.CardCountResult;
import indivus.cosmos.model.server.post.HomeCardResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-26.
 */

public class HomeFeedAdapter extends FragmentPagerAdapter {

    ArrayList<Card> card_list;

    HomeFeedPresenter presenter;

    public HomeFeedAdapter(FragmentManager fm, int tab) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment card_fragment;

        Bundle bundle = new Bundle();
        bundle.putSerializable("card", card_list.get(position));
        card_fragment = new HomeCardFragment();

        card_fragment.setArguments(bundle);

        return card_fragment;
    }

    @Override
    public int getCount() {
        return card_list != null ? card_list.size() : 0;
    }

    public void updateCardList(ArrayList<Card> card_list){
       this.card_list = card_list;
    }
}
