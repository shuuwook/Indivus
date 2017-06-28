package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.ExploreAdapter;

/**
 * Created by seowo on 2017-06-27.
 */

public class ContentsFragment extends Fragment {
    RecyclerView recycler_view;
    ExploreAdapter adapter;
    RecyclerView.LayoutManager layout_manager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View contents_view = inflater.inflate(R.layout.fragment_contents, container, false);

        layout_manager = new GridLayoutManager(contents_view.getContext(), 2);
        //recycler_view = (RecyclerView)contents_view.findViewById(R.id.contents_recycler);
        adapter = new ExploreAdapter();

        recycler_view.setLayoutManager(layout_manager);
        recycler_view.setAdapter(adapter);

        return contents_view;
    }
}
