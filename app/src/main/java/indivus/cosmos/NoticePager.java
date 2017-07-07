package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.NoticeAdapter;
import indivus.cosmos.presenter.ResponseCallBack;

/**
 * Created by seowo on 2017-06-25.
 */

public class NoticePager extends Fragment {

    RecyclerView recyclerView;
    NoticeAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pager_notice, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.notice_recycler);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                NoticeAdapter.NoticeViewHolder holder = (NoticeAdapter.NoticeViewHolder)viewHolder;
                adapter.removeNotice(holder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                Log.i("delete", adapter.notice_list.size()+"");
            }
        };

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

        adapter = new NoticeAdapter(getActivity(), new ResponseCallBack() {
            @Override
            public void onSuccess(int result) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
                itemTouchHelper.attachToRecyclerView(recyclerView);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int result) {

            }
        });

        return v;
    }
}
