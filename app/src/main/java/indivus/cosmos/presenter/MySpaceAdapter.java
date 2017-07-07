package indivus.cosmos.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import indivus.cosmos.ManageFragment;
import indivus.cosmos.ProfileFragment;
import indivus.cosmos.WorkRoomFragment;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;

/**
 * Created by seowo on 2017-07-06.
 */

public class MySpaceAdapter extends FragmentStatePagerAdapter{
    final private int WORKROOM = 0;
    final private int PROFILE = 1;
    final private int MANAGE = 2;

    String[] fragment_list = new String[]{"Workroom", "Profile", "Manage"};

    NetworkService service;

    public MySpaceAdapter(FragmentManager fm) {
        super(fm);
        service = Indivus.getInstance().getNetworkService();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case WORKROOM :
                return new WorkRoomFragment();
            case PROFILE :
                return new ProfileFragment();
            case MANAGE :
                return new ManageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragment_list[position];
    }
}
