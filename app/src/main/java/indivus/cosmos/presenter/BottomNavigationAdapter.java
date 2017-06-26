package indivus.cosmos.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import indivus.cosmos.ExplorePager;
import indivus.cosmos.HomeFeedPager;
import indivus.cosmos.MySpacePager;
import indivus.cosmos.NoticePager;

/**
 * Created by seowo on 2017-06-25.
 */

public class BottomNavigationAdapter extends FragmentStatePagerAdapter {
    private final int home_feed = 0;
    private final int explore = 1;
    private final int notice = 2;
    private final int my_space = 3;

    public BottomNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case home_feed : return new HomeFeedPager();
            case explore : return new ExplorePager();
            case notice : return new NoticePager();
            case my_space : return new MySpacePager();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
