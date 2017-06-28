package indivus.cosmos.model.data;

import android.util.Log;

import java.util.ArrayList;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.CardResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-27.
 */

public class CardList {

    private ArrayList<Card> card_list;
    private int card_num;
    NetworkService service;

    public CardList() {
        card_list = new ArrayList<Card>();
        card_num = 0;

        loadData(card_num);
        //Network service 초기화
        service = Indivus.getInstance().getNetworkService();
    }

    public ArrayList<Card> getCardList(){
        return card_list;
    }

    public void loadData(int position){

        Call<CardResult> cardResultCall = service.getCardResult(card_num);
        cardResultCall.enqueue(new Callback<CardResult>() {
            @Override
            public void onResponse(Call<CardResult> call, Response<CardResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("success")){
                        //test용임. 실제로는 server에서 받은 값으로 생성
                        Card card = new Card("", "test", "test", "test");
                        card_list.add(card);
                        card_list.add(card);
                        card_list.add(card);
                        card_list.add(card);

                        //ArrayList size에 따른 추가와 삭제 처리
                    }
                }
            }

            @Override
            public void onFailure(Call<CardResult> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }
}
