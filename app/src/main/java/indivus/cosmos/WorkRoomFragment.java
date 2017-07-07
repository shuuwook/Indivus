package indivus.cosmos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.MessageResult;
import indivus.cosmos.model.server.series.SeriesData;
import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.model.server.workroom.CreateWorkRoomSeriesData;
import indivus.cosmos.model.server.workroom.WorkRoomResult;
import indivus.cosmos.presenter.WorkRoomAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-06.
 */

public class WorkRoomFragment extends Fragment{

    RecyclerView recycler;
    WorkRoomAdapter adapter;

    NetworkService service;

    Fragment workroom_fragment;
    public WorkRoomFragment() {
        service = Indivus.getInstance().getNetworkService();
        workroom_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.fragment_myspace_workroom, container, false);

        recycler = (RecyclerView)root_view.findViewById(R.id.myspace_workroom_recycler);
        String token = Indivus.getInstance().getPreferences();
        Call<WorkRoomResult> workRoomResultCall = service.getMyWorkRoom(token);
        workRoomResultCall.enqueue(new Callback<WorkRoomResult>() {
            @Override
            public void onResponse(Call<WorkRoomResult> call, Response<WorkRoomResult> response) {
                if(response.isSuccessful()){
                    adapter = new WorkRoomAdapter(getActivity(), response.body().result);
                    recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    recycler.setAdapter(adapter);
                }
                else{
                    int statusCode = response.code();
                    Log.i("status : ", statusCode + "");
                }
            }
            @Override
            public void onFailure(Call<WorkRoomResult> call, Throwable t) {
            }
        });


        ItemClickSupport.addTo(recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                final String token = Indivus.getInstance().getPreferences();
                //단편
                if(position == adapter.getItemCount() - 2){
                    Call<SeriesResult> singleResultCall = service.getPostSingle(token);
                    singleResultCall.enqueue(new Callback<SeriesResult>() {
                        @Override
                        public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(getContext(), SeriesActivity.class);
                                intent.putExtra("title", "단편");
                                intent.putExtra("series", response.body().result);
                                startActivity(intent);
                            }
                            else{
                                int statusCode = response.code();
                                Log.i("status : ", statusCode+"");
                            }
                        }

                        @Override
                        public void onFailure(Call<SeriesResult> call, Throwable t) {

                        }
                    });
                }
                //추가
                else if(position == adapter.getItemCount() - 1){
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setDialog("폴더 추가", 1, new Dialog.DialogCallBack() {
                        @Override
                        public void finish(String edit) {
                            CreateWorkRoomSeriesData data = new CreateWorkRoomSeriesData(edit);
                            Call<MessageResult> createSeriesCall = service.createWorkRoomSeries(token, data);
                            createSeriesCall.enqueue(new Callback<MessageResult>() {
                                @Override
                                public void onResponse(Call<MessageResult> call, Response<MessageResult> response) {
                                    getFragmentManager()
                                            .beginTransaction()
                                            .detach(workroom_fragment)
                                            .attach(workroom_fragment)
                                            .commit();
                                }
                                @Override
                                public void onFailure(Call<MessageResult> call, Throwable t) {
                                    Toast.makeText(getActivity(), "시리즈 생성을 실패하였습니다", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    dialog.show();
                }
                //시리즈
                else{
                    SeriesData data = new SeriesData(adapter.getSeriesName(position));
                    Call<SeriesResult> postSeriesResultCall = service.getPostSeries(token, data);
                    postSeriesResultCall.enqueue(new Callback<SeriesResult>() {
                        @Override
                        public void onResponse(Call<SeriesResult> call, Response<SeriesResult> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(getContext(), SeriesActivity.class);
                                intent.putExtra("title", adapter.getSeriesName(position));
                                intent.putExtra("series", response.body().result);
                                startActivity(intent);
                            }
                            else{
                                int statusCode = response.code();
                                Log.i("status : ", statusCode+"");
                            }
                        }

                        @Override
                        public void onFailure(Call<SeriesResult> call, Throwable t) {

                        }
                    });
                }
            }
        });

        return root_view;
    }
}
