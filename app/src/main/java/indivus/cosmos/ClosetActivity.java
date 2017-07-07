package indivus.cosmos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.closet.ClosetResult;
import indivus.cosmos.model.server.collection.CollectionData;

import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.presenter.ClosetAdapter;
import indivus.cosmos.presenter.CollectionAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClosetActivity extends AppCompatActivity {

    RecyclerView recycler;
    ClosetAdapter adapter;

    NetworkService service;

    public ClosetActivity() {
        service = Indivus.getInstance().getNetworkService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        recycler = (RecyclerView)findViewById(R.id.closet_recycler);

        String token = Indivus.getInstance().getPreferences();
        Call<ClosetResult> collectionResultCall = service.getCloset(token);
        collectionResultCall.enqueue(new Callback<ClosetResult>() {
            @Override
            public void onResponse(Call<ClosetResult> call, Response<ClosetResult> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        adapter = new ClosetAdapter(getApplicationContext(), response.body().result);
                        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        recycler.setAdapter(adapter);
                    }
                    else{
                        int statusCode = response.code();
                        Log.i("status : ", statusCode + "");
                    }
                }
            }

            @Override
            public void onFailure(Call<ClosetResult> call, Throwable t) {

            }
        });
    }
}
