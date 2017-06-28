package indivus.cosmos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017-06-28.
 */

public class PostActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        //그리드뷰 정의
        RecyclerView view = (RecyclerView)findViewById(R.id.post_recyclerview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,3);
        PostRecyclerAdapter postRecyclerAdapter = new PostRecyclerAdapter();

        //어댑터 연결
        view.setAdapter(postRecyclerAdapter);
        view.setLayoutManager(layoutManager);
    }
}
