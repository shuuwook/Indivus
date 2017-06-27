package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.HomeAdapter;

/**
 * Created by seowo on 2017-06-26.
 */

public class CurationFragment extends Fragment {

    RecyclerView recycler_view;
    HomeAdapter adapter;
    RecyclerView.LayoutManager layout_manager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View curation_view = inflater.inflate(R.layout.fragement_curation, container, false);

        layout_manager = new LinearLayoutManager(curation_view.getContext());
        recycler_view = (RecyclerView)curation_view.findViewById(R.id.curation_recycler);
        adapter = new HomeAdapter();

        adapter.setCardData();
        recycler_view.setLayoutManager(layout_manager);
        recycler_view.setAdapter(adapter);

        return curation_view;
    }
}
