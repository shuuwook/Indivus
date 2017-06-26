package indivus.cosmos;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_nav = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottom_nav);

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected_fragment = null;
                switch (item.getItemId()){
                    case R.id.action_home_feed :
                        selected_fragment =  new HomeFeedPager();
                        break;
                    case R.id.action_explorer :
                        selected_fragment =  new ExplorePager();
                        break;
                    case R.id.action_notice :
                        selected_fragment =  new NoticePager();
                        break;
                    case R.id.action_my_space :
                        selected_fragment =  new MySpacePager();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, selected_fragment);
                transaction.commit();
                return true;
            }
        });

        //Default Page
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, new HomeFeedPager());
        transaction.commit();
    }
}
