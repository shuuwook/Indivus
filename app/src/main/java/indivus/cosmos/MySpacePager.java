package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.presenter.MySpaceAdapter;

/**
 * Created by seowo on 2017-06-25.
 */

public class MySpacePager extends Fragment {
    TabLayout tab_layout;
    ViewPager view_pager;
    FragmentManager fragment_manager;
    MySpaceAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pager_my_space, container, false);

        tab_layout = (TabLayout)v.findViewById(R.id.workroom_tab);
        view_pager = (ViewPager)v.findViewById(R.id.workroom_pager);
        fragment_manager = getFragmentManager();

        adapter = new MySpaceAdapter(fragment_manager);
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);

        view_pager.setClipToPadding(false);
        view_pager.setPadding(90,96,90,162);
        view_pager.setPageMargin(60);

        return v;
    }
}
