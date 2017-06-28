package indivus.cosmos.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import indivus.cosmos.model.data.Card;
import indivus.cosmos.model.data.CardList;

/**
 * Created by seowo on 2017-06-26.
 */

public class HomeAdapter extends FragmentPagerAdapter {

    CardList card_list;
    ArrayList<Card> card_list_data;

    public HomeAdapter(FragmentManager fm, CardList card_list) {
        super(fm);
        setCardList(card_list);
    }

    public void setCardList(CardList card_list){
        this.card_list = card_list;
        this.card_list_data = card_list.getCardList();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        if(position < 2){
            card_list.loadData(position);
            setCardList(card_list);
            position = 5;
        }
        else if(position > 8){
            card_list.loadData(position);
            setCardList(card_list);
            position = 5;
        }

        fragment = card_list_data.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return card_list_data.size() < 10 ? card_list_data.size() : 10;
    }
}
