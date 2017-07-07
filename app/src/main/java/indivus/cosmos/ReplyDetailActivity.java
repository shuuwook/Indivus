package indivus.cosmos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyResult;
import indivus.cosmos.presenter.ReplyAdapter;
import indivus.cosmos.presenter.ReplyCallBack;
import indivus.cosmos.presenter.ReplyDetailAdapter;
import indivus.cosmos.presenter.ReplyPresenter;

public class ReplyDetailActivity extends AppCompatActivity {

    RecyclerView reply_detail_recycler;
    ReplyDetailAdapter reply_detail_adapter;

    ReplyPresenter presenter;

    int comment_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_detail);

        comment_id = getIntent().getIntExtra("comment_id", 0);

        reply_detail_recycler = (RecyclerView)findViewById(R.id.reply_reply_recyclerview);

        presenter = new ReplyPresenter();

        presenter.getReplyDetail(comment_id, new ReplyCallBack() {
            @Override
            public void getReply(ReplyResult result) {
            }
            @Override
            public void sendReply() {
            }
            @Override
            public void getReplyDetail(ReplyDetailResult result) {
                reply_detail_adapter = new ReplyDetailAdapter(getApplicationContext(), result);
                reply_detail_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                reply_detail_recycler.setAdapter(reply_detail_adapter);
            }
            @Override
            public void sendReplyDetail() {
            }
        });
    }
}
