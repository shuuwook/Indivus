package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.MessageResult;
import indivus.cosmos.model.server.collection.CollectionData;
import indivus.cosmos.model.server.collection.CollectionNameData;
import indivus.cosmos.model.server.collection.CollectionResult;
import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.model.server.workroom.CreateWorkRoomSeriesData;
import indivus.cosmos.presenter.CollectionAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionActivity extends AppCompatActivity {

    RecyclerView recycler;
    CollectionAdapter adapter;

    NetworkService service;

    Activity activity;

    public CollectionActivity() {
        activity = this;
        service = Indivus.getInstance().getNetworkService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        recycler = (RecyclerView)findViewById(R.id.collection_recycler);

        String token = Indivus.getInstance().getPreferences();
        Call<CollectionResult> collectionResultCall = service.getCollection(token);
        collectionResultCall.enqueue(new Callback<CollectionResult>() {
            @Override
            public void onResponse(Call<CollectionResult> call, Response<CollectionResult> response) {
                if(response.isSuccessful()){
                    if(response.isSuccessful()){
                        adapter = new CollectionAdapter(getApplicationContext(), response.body().result);
                        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        recycler.setAdapter(adapter);
                    }
                    else{
                        int statusCode = response.code();
                        Log.i("status : ", statusCode + "");
                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionResult> call, Throwable t) {

            }
        });

        ItemClickSupport.addTo(recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                final String token = Indivus.getInstance().getPreferences();
                //추가
                if(position == adapter.getItemCount() - 1){
                    Dialog dialog = new Dialog(activity);
                    dialog.setDialog("폴더 추가", 1, new Dialog.DialogCallBack() {
                        @Override
                        public void finish(String edit) {
                            CollectionNameData data = new CollectionNameData(edit);
                            Call<MessageResult> createCollectionCall = service.createCollection(token, data);
                            createCollectionCall.enqueue(new Callback<MessageResult>() {
                                @Override
                                public void onResponse(Call<MessageResult> call, Response<MessageResult> response) {
                                    if(response.isSuccessful()){
                                        activity.finish();
                                        activity.startActivity(getIntent());
                                    }
                                }
                                @Override
                                public void onFailure(Call<MessageResult> call, Throwable t) {
                                    Toast.makeText(activity, "시리즈 생성을 실패하였습니다", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    dialog.show();
                }
                //collection
                else {
                    CollectionData data = new CollectionData(adapter.getCollectionId(position));
                    Call<SeriesResult> collectionDetailCall = service.getCollectionDetail(token, data);
                    collectionDetailCall.enqueue(new Callback<SeriesResult>() {
                        @Override
                        public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), SeriesActivity.class);
                                intent.putExtra("series", response.body().result);
                                startActivity(intent);
                            } else {
                                int statusCode = response.code();
                                Log.i("status : ", statusCode + "");
                            }
                        }

                        @Override
                        public void onFailure(Call<SeriesResult> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
