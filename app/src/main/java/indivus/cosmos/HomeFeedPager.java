package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View home_view = inflater.inflate(R.layout.pager_home_feed, container, false);

        tab = (TabLayout)home_view.findViewById(R.id.home_tab_layout);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            Fragment selected_fragment = null;

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case curation : selected_fragment = new CurationFragment();
                        break;
                    case following : selected_fragment = new FollowingFragment();
                        break;
                }

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.home_container, selected_fragment);
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

        //default fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, new CurationFragment());
        transaction.commit();

        return home_view;
    }
}
