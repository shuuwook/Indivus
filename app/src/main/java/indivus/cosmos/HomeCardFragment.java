package indivus.cosmos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.Card;
import indivus.cosmos.model.server.post.HomeCardResult;
import indivus.cosmos.presenter.HomeFeedCallBack;
import indivus.cosmos.presenter.HomeFeedPresenter;

/**
 * Created by seowo on 2017-06-28.
 */

public class HomeCardFragment extends Fragment{

    Card card;

    int post_id;

    ImageView card_image;

    TextView card_title;
    TextView card_sub_title;
    TextView card_time;

    LinearLayout card_tag;

    TextView card_comment;

    ImageView creator_image;

    TextView creator_title;
    TextView creator_sub_title;

    ImageButton card_light_btn;
    ImageButton card_comment_btn;
    ImageButton card_collect_btn;

    TextView card_light_count;
    TextView card_comment_count;
    TextView card_collect_count;

    HomeFeedPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View card_view = inflater.inflate(R.layout.item_card_home, container, false);

        card = (Card)getArguments().get("card");

        post_id = card.post_id;

        card_image = (ImageView)card_view.findViewById(R.id.card_image);
        Glide.with(card_view.getContext()).load(Uri.parse(card.card_cover)).thumbnail(0.1f).into(card_image);
        GradientDrawable drawable = (GradientDrawable)card_view.getContext().getDrawable(R.drawable.card_home_img_background);

        card_image.setBackground(drawable);
        card_image.setClipToOutline(true);

        card_title = (TextView)card_view.findViewById(R.id.card_title);
        card_title.setText(card.title);

        card_sub_title = (TextView)card_view.findViewById(R.id.card_sub_title);
        card_sub_title.setText(card.sub_title);

        card_time = (TextView)card_view.findViewById(R.id.card_time);
        //하루 이틀...한시간...그저께
        card_time.setText(Indivus.getRelativeDate(card.posting_date));

        card_tag = (LinearLayout)card_view.findViewById(R.id.card_tag);
        card_tag.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams tag_params = new LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        //category tag 추가
        TextView category_tag = new TextView(card_view.getContext());
        category_tag.setLayoutParams(tag_params);
        category_tag.setText(card.category_name);
        category_tag.setTextSize(12);
        category_tag.setTextColor(Color.parseColor("#8F9294"));
        category_tag.setBackgroundResource(R.drawable.round_tag_background);
        category_tag.setPadding(30,9,30,9);
        card_tag.addView(category_tag);
        //key card tag 추가
        for(Card.Keycard tag : card.keycard){
            TextView keycard_tag = new TextView(card_view.getContext());
            keycard_tag.setLayoutParams(tag_params);
            keycard_tag.setText(tag.keycard_name);
            keycard_tag.setTextSize(12);
            keycard_tag.setTextColor(Color.parseColor("#8F9294"));
            keycard_tag.setBackgroundResource(R.drawable.round_tag_background);
            keycard_tag.setPadding(30,9,30,9);
            card_tag.addView(keycard_tag);
        }

        card_comment = (TextView)card_view.findViewById(R.id.card_comment);
        card_comment.setText(card.comment);

        creator_image = (ImageView)card_view.findViewById(R.id.card_creator_image);
        Glide.with(card_view.getContext()).load(Uri.parse(card.profile_photo)).thumbnail(0.1f).into(creator_image);

        creator_title = (TextView)card_view.findViewById(R.id.card_creator_title);
        creator_title.setText(card.username);
        creator_sub_title = (TextView)card_view.findViewById(R.id.card_creator_sub_title);
        creator_sub_title.setText(card.jobs);

        card_light_count = (TextView)card_view.findViewById(R.id.card_bulb_count);
        card_light_count.setText(card.like_counts+"");
        card_comment_count = (TextView)card_view.findViewById(R.id.card_reply_count);
        card_comment_count.setText(card.comment_counts+"");
        card_collect_count = (TextView)card_view.findViewById(R.id.card_collect_count);
        card_collect_count.setText(card.collection_counts+"");

        card_light_btn = (ImageButton)card_view.findViewById(R.id.card_bulb_btn);
        card_light_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickAwesome(post_id, new HomeFeedCallBack() {
                    @Override
                    public void getHomeCard(HomeCardResult result) {
                        //null
                    }
                    @Override
                    public void clickAwesome(int awesome_count) {
                        card_light_count.setText(awesome_count);
                    }
                    @Override
                    public void clickCollect(int collect_count) {
                        //null
                    }
                });
            }
        });
        card_comment_btn = (ImageButton)card_view.findViewById(R.id.card_reply_btn);
        card_comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //댓글 액티비티로 넘어감
            }
        });
        card_collect_btn = (ImageButton)card_view.findViewById(R.id.card_collect_btn);
        card_collect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clickCollect(post_id, new HomeFeedCallBack() {
                    @Override
                    public void getHomeCard(HomeCardResult result) {
                        //null
                    }
                    @Override
                    public void clickAwesome(int awesome_count) {
                        //null
                    }
                    @Override
                    public void clickCollect(int collect_count) {
                        card_collect_count.setText(card.collection_counts);
                    }
                });
            }
        });

        return card_view;
    }
}
