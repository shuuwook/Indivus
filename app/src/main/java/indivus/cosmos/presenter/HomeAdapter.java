package indivus.cosmos.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import indivus.cosmos.HomeCardFragment;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.Card;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.CardResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-26.
 */

public class HomeAdapter extends FragmentPagerAdapter {

    ArrayList<Card> card_list;
    int list_size;

    NetworkService service;

    public HomeAdapter(FragmentManager fm, ArrayList<Card> card_list) {
        super(fm);
        this.card_list = card_list;
        list_size = card_list.size();

        //initialize network
        service = Indivus.getInstance().getNetworkService();
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment card_fragment;

        if(position == list_size - 2) {
            Call<CardResult> cardResultCall = service.getCardResult(card_list.get(position).getPostId());
            cardResultCall.enqueue(new Callback<CardResult>() {
                @Override
                public void onResponse(Call<CardResult> call, Response<CardResult> response) {
                    if(response.isSuccessful()){
                        if(response.body().message.equals("success")){
                            card_list.addAll(response.body().result);
                        }
                    }
                    else{
                        int statusCode = response.code();
                        Log.i("server status", "CODE : " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<CardResult> call, Throwable t) {
                    Log.i("network error", t.getMessage());
                }
            });
        }

        card_fragment = new HomeCardFragment(card_list.get(position));

        return card_fragment;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
