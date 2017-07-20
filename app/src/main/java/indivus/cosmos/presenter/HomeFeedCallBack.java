package indivus.cosmos.presenter;

import indivus.cosmos.model.server.post.HomeCardResult;

/**
 * Created by seowo on 2017-07-02.
 */

public interface HomeFeedCallBack {

    void getHomeCard(HomeCardResult result);

    void clickAwesome(int awesome_count, boolean like);
    void clickCollect(int collect_count);

    //public void accessDenied();
}
