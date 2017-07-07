package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.post.CardCountData;
import indivus.cosmos.model.server.post.CardCountResult;
import indivus.cosmos.model.server.post.PostData;
import indivus.cosmos.model.server.post.PostResult;
import indivus.cosmos.model.server.series.SeriesData;
import indivus.cosmos.model.server.series.SeriesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-01.
 */

public class PostContentPresenter {

    NetworkService service;

    public PostContentPresenter() {
        service = Indivus.getInstance().getNetworkService();
    }

    public void getContent(int post_id, final PostContentCallback callBack) {
        String token = Indivus.getInstance().getPreferences();
        PostData post_data = new PostData(post_id);
        Call<PostResult> postResultCall = service.getPostContent(token, post_data);
        postResultCall.enqueue(new Callback<PostResult>() {
            @Override
            public void onResponse(Call<PostResult> call, Response<PostResult> response) {
                if(response.isSuccessful()){
                    Log.i("aaa", "2");
                    Log.i("lll", response.body().card_cover);
                    callBack.getContent(response.body());
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<PostResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public void getSeries(int post_id, String title, final PostContentCallback callback){
        String token = Indivus.getInstance().getPreferences();
        SeriesData series_data = new SeriesData(title);
        Call<SeriesResult> postSeriesResultCall = service.getPostSeries(token, series_data);
        postSeriesResultCall.enqueue(new Callback<SeriesResult>() {
            @Override
            public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                if(response.isSuccessful()){
                    callback.getSeries(response.body().result);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }
            @Override
            public void onFailure(Call<SeriesResult> call, Throwable t) {
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
                    if(response.body().message.equals("like success"))
                    callBack.clickAwesome(response.body().count);
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
                    if(response.body().message.equals("collect success"))
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
