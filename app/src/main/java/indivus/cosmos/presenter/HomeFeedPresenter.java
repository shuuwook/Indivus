package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.post.CardCountData;
import indivus.cosmos.model.server.post.CardCountResult;
import indivus.cosmos.model.server.post.HomeCardResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-02.
 */

public class HomeFeedPresenter {

    NetworkService service;

    public HomeFeedPresenter() {
        service = Indivus.getInstance().getNetworkService();
    }

    public void getCurationCard(final HomeFeedCallBack callBack){
        String token = Indivus.getInstance().getPreferences();
        Call<HomeCardResult> homeCardResultCall = service.getCurationCardResult(token);
        homeCardResultCall.enqueue(new Callback<HomeCardResult>() {
            @Override
            public void onResponse(Call<HomeCardResult> call, Response<HomeCardResult> response) {
                if(response.isSuccessful()){
                        callBack.getHomeCard(response.body());
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                    if(response.message().equals("access denied")){
                        //callBack.accessDenied();
                    }
                }
            }
            @Override
            public void onFailure(Call<HomeCardResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });

    }

    public void getFollowingCard(final HomeFeedCallBack callBack){
        String token = Indivus.getInstance().getPreferences();
        Call<HomeCardResult> homeCardResultCall = service.getFollowingCardResult(token);
        homeCardResultCall.enqueue(new Callback<HomeCardResult>() {
            @Override
            public void onResponse(Call<HomeCardResult> call, Response<HomeCardResult> response) {
                if(response.isSuccessful()){
                    callBack.getHomeCard(response.body());
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }
            @Override
            public void onFailure(Call<HomeCardResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public void clickAwesome(int post_id, final HomeFeedCallBack callBack){
        String token = Indivus.getInstance().getPreferences();
        CardCountData count_data = new CardCountData(post_id);
        Call<CardCountResult> cardCountResultCall = service.clickAwesome(token, count_data);
        cardCountResultCall.enqueue(new Callback<CardCountResult>() {
            @Override
            public void onResponse(Call<CardCountResult> call, Response<CardCountResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("success"))
                    callBack.clickAwesome(response.body().count);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<CardCountResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public void clickCollect(int post_id, final HomeFeedCallBack callBack){
        String token = Indivus.getInstance().getPreferences();
        CardCountData count_data = new CardCountData(post_id);
        Call<CardCountResult> cardCountResultCall = service.clickCollect(token, count_data);
        cardCountResultCall.enqueue(new Callback<CardCountResult>() {
            @Override
            public void onResponse(Call<CardCountResult> call, Response<CardCountResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("success"))
                    callBack.clickCollect(response.body().count);
                }
            }

            @Override
            public void onFailure(Call<CardCountResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

}
