package indivus.cosmos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyResult;
import indivus.cosmos.presenter.ReplyAdapter;
import indivus.cosmos.presenter.ReplyCallBack;
import indivus.cosmos.presenter.ReplyPresenter;

public class ReplyActivity extends AppCompatActivity {

    RecyclerView reply_recycler;
    ReplyAdapter reply_adapter;
    RecyclerView.LayoutManager reply_layout_manager;

    EditText reply_edit;
    Button reply_btn;
    Button reply_back_btn;

    ReplyPresenter presenter;

    int post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

       post_id = getIntent().getIntExtra("post_id", 0);

        reply_layout_manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        presenter = new ReplyPresenter();
        presenter.getReply(post_id, new ReplyCallBack() {
            @Override
            public void getReply(ReplyResult result) {
                reply_adapter = new ReplyAdapter(getApplicationContext(), result);
                reply_recycler.setAdapter(reply_adapter);
                reply_recycler.setLayoutManager(reply_layout_manager);
            }
            @Override
            public void sendReply() {
            }

            @Override
            public void getReplyDetail(ReplyDetailResult result) {

            }

            @Override
            public void sendReplyDetail() {

            }
        });

        reply_edit = (EditText)findViewById(R.id.reply_input_reply);
        reply_btn = (Button)findViewById(R.id.reply_input_btn);

        reply_back_btn = (Button)findViewById(R.id.reply_back_btn);

        reply_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendReply(post_id, reply_edit.getText().toString(), new ReplyCallBack() {
                    @Override
                    public void getReply(ReplyResult result) {
                    }
                    @Override
                    public void sendReply() {
                        Toast.makeText(getApplicationContext(), "댓글이 등록되었습니다", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getReplyDetail(ReplyDetailResult result) {

                    }

                    @Override
                    public void sendReplyDetail() {

                    }
                });
            }
        });
    }
}
