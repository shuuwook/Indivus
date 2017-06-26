package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.HomeAdapter;

/**
 * Created by seowo on 2017-06-25.
 */

public class HomeFeedPager extends Fragment {

    FragmentManager fragment_manager;
    HomeAdapter home_adapter;
    ViewPager view_pager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View home_view = inflater.inflate(R.layout.pager_home_feed, container, false);

        fragment_manager = getFragmentManager();
        home_adapter = new HomeAdapter(fragment_manager);
        view_pager = (ViewPager)home_view.findViewById(R.id.home_pager);
        view_pager.setAdapter(home_adapter);

        return home_view;
    }
}
