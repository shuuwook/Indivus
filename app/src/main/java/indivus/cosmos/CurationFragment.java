package indivus.cosmos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indivus.cosmos.model.server.post.HomeCardResult;
import indivus.cosmos.presenter.HomeFeedAdapter;
import indivus.cosmos.presenter.HomeFeedCallBack;
import indivus.cosmos.presenter.HomeFeedPresenter;

/**
 * Created by seowo on 2017-06-26.
 */

public class CurationFragment extends Fragment {
    private final int CURATION_TAB = 0;

    VerticalViewPager view_pager;
    HomeFeedAdapter adapter;
    FragmentManager fragment_manager;

    HomeFeedPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new HomeFeedPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View curation_view = inflater.inflate(R.layout.fragement_curation, container, false);

        fragment_manager = getFragmentManager();

        adapter = new HomeFeedAdapter(fragment_manager, CURATION_TAB);

        presenter.getCurationCard(new HomeFeedCallBack() {
            @Override
            public void getHomeCard(HomeCardResult result) {
                adapter.updateCardList(result.result);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void clickAwesome(int awesome_count) {
                //null
            }
            @Override
            public void clickCollect(int collect_count) {
                //null
            }
        });

        view_pager = (VerticalViewPager) curation_view.findViewById(R.id.curation_pager);
        view_pager.setAdapter(adapter);
        view_pager.setClipToPadding(false);
        view_pager.setPadding(90, 162, 90, 96);
        view_pager.setPageMargin(54);

        return curation_view;
    }
}
