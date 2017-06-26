package indivus.cosmos.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by seowo on 2017-06-25.
 */

public class HomeAdapter extends FragmentStatePagerAdapter {
    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
        }
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
