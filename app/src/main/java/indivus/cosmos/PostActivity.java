package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-28.
 */

public class PostActivity extends Activity{
    ConstraintLayout root_view;
    InputMethodManager imm;

    Button post_back_btn;
    TextView post_title;
    TextView post_sub_title;
    TextView post_summary;
    RecyclerView post_recyclerView;

    ArrayList<PostItems> item_list;
    LinearLayoutManager linear_layout_manager;

    PostRecyclerAdapter adapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_back_btn = (Button)findViewById(R.id.post_back_btn);

        root_view = (ConstraintLayout)findViewById(R.id.post_root_view);
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);

        post_back_btn = (Button)findViewById(R.id.post_back_btn);
        post_title = (TextView)findViewById(R.id.post_title);
        post_sub_title = (TextView)findViewById(R.id.post_sub_title);
        post_summary = (TextView)findViewById(R.id.post_summary);
        post_recyclerView = (RecyclerView)findViewById(R.id.post_recyclerview);

        post_recyclerView.setHasFixedSize(true);
        post_recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));


        //타이틀, 서브타이틀, 서머리 얻어오기
        /*post_title.getResources().getText();
        post_sub_title.getResources().getText();
        post_summary.getResources().getText();
        */

        //리사이클러뷰
        item_list = new ArrayList<>();
        item_list.add(new PostItems(R.drawable.login_logo, "test1"));
        item_list.add(new PostItems("Hello Indivus"));
        item_list.add(new PostItems(R.drawable.mainkey_c));

        linear_layout_manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new PostRecyclerAdapter(item_list, click_listener);

        post_recyclerView.setLayoutManager(linear_layout_manager);
        post_recyclerView.setAdapter(adapter);

        root_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            }
        });

        //뒤로가기 버튼
        post_back_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    void selectPost(final int position){

    }

    public View.OnClickListener click_listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            final int position = post_recyclerView.getChildAdapterPosition(v);
            selectPost(position);
        }
    };

}
