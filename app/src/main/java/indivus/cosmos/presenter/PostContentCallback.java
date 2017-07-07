package indivus.cosmos.presenter;

import java.util.ArrayList;

import indivus.cosmos.model.server.post.PostResult;
import indivus.cosmos.model.server.series.SeriesResult;

/**
 * Created by seowo on 2017-07-02.
 */

public interface PostContentCallback {
    void getContent(PostResult result);
    void getSeries(ArrayList<SeriesResult.PostSeries> result);

    void clickCreator(int ID_creator);

    void clickPrevious(int post_id);
    void clickNext(int post_id);
    void clickSeries(int post_id);

    void clickAwesome(int awesome_count);
    void clickReply(int post_id);
    void clickCollect(int collect_count);

    void clickRecommend(int post_id);
}
