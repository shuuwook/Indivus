package indivus.cosmos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by seowo on 2017-06-25.
 */

public class HomeFeedPager extends Fragment {
    private final int curation = 0;
    private final int following = 1;

    TabLayout tab;

    FragmentTransaction transaction;
    FragmentManager fragment_manager;

    CurationFragment curation_fragment;
    FollowingFragment following_fragment;

    FloatingActionButton seed_btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(curation_fragment == null) curation_fragment = new CurationFragment();
        if(following_fragment == null) following_fragment = new FollowingFragment();

        View home_view = inflater.inflate(R.layout.pager_home_feed, container, false);

        seed_btn = (FloatingActionButton)home_view.findViewById(R.id.seed_floating_btn);

        fragment_manager = getFragmentManager();

        //default fragment
        transaction = getChildFragmentManager().beginTransaction();

        transaction.add(R.id.home_container, curation_fragment);
        transaction.add(R.id.home_container, following_fragment);

        transaction.hide(following_fragment);
        transaction.show(curation_fragment);

        transaction.commit();

        tab = (TabLayout)home_view.findViewById(R.id.home_tab_layout);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                transaction = getChildFragmentManager().beginTransaction();
                switch (tab.getPosition()){
                    case curation :
                        transaction.hide(following_fragment);
                        transaction.show(curation_fragment);
                        //transaction.replace(R.id.home_container, curation_fragment);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        break;
                    case following :
                        transaction.hide(curation_fragment);
                        transaction.show(following_fragment);
                        //transaction.replace(R.id.home_container, following_fragment);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        break;
                }
                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //null
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //null
            }
        });

        seed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        return home_view;
    }
}
