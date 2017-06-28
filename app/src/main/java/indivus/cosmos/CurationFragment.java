package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.HomeAdapter;

/**
 * Created by seowo on 2017-06-26.
 */

public class CurationFragment extends Fragment {

    VerticalViewPager view_pager;
    HomeAdapter adapter;
    FragmentManager fragment_manager;

    CardList card_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View curation_view = inflater.inflate(R.layout.fragement_curation, container, false);

        card_list = new CardList();

        fragment_manager = getFragmentManager();

        adapter = new HomeAdapter(fragment_manager, card_list);

        view_pager = (VerticalViewPager) curation_view.findViewById(R.id.curation_pager);
        view_pager.setAdapter(adapter);

        return curation_view;
    }
}
