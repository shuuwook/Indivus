package indivus.cosmos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.server.post.PostResult;
import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.presenter.PostAdapter;
import indivus.cosmos.presenter.PostContentCallback;
import indivus.cosmos.presenter.PostContentPresenter;

public class PostActivity extends AppCompatActivity {

    RecyclerView post_content_recycler;
    PostAdapter adapter;
    RecyclerView.LayoutManager layout_manager;

    PostContentPresenter post_presenter;

    TextView post_title;
    TextView post_sub_title;
    TextView post_explain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        int post_id = getIntent().getIntExtra("post_id", 168);

        post_title = (TextView)findViewById(R.id.post_title);
        post_sub_title = (TextView)findViewById(R.id.post_sub_title);
        post_explain = (TextView)findViewById(R.id.post_summary_content);

        post_presenter = new PostContentPresenter();

        post_content_recycler = (RecyclerView)findViewById(R.id.post_recyclerview);

        post_presenter.getContent(post_id, new PostContentCallback() {
            @Override
            public void getContent(PostResult result) {
                post_title.setText(result.title);
                post_sub_title.setText(result.sub_title);
                post_explain.setText(result.explain);

                layout_manager = new LinearLayoutManager(PostActivity.this);
                adapter = new PostAdapter(PostActivity.this, result);
                post_content_recycler.setLayoutManager(layout_manager);
                post_content_recycler.setAdapter(adapter);
            }

            @Override
            public void getSeries(ArrayList<SeriesResult.PostSeries> result) {
            }

            @Override
            public void clickCreator(int ID_creator) {
            }
            @Override
            public void clickPrevious(int post_id) {
            }
            @Override
            public void clickNext(int post_id) {
            }
            @Override
            public void clickSeries(int post_id) {
            }
            @Override
            public void clickAwesome(int awesome_count) {
            }
            @Override
            public void clickReply(int post_id) {
            }
            @Override
            public void clickCollect(int collect_count) {
            }
            @Override
            public void clickRecommend(int post_id) {
            }
        });
    }
}
