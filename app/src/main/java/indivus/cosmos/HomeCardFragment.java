package indivus.cosmos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import indivus.cosmos.model.data.Card;

/**
 * Created by seowo on 2017-06-28.
 */

public class HomeCardFragment extends Fragment{

    Card card;

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

    public HomeCardFragment(Card card) {
        this.card = card;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View card_view = inflater.inflate(R.layout.item_card_home, container, false);

        card_image = (ImageView)card_view.findViewById(R.id.card_image);
        try {
            URL image_url = new URL(card.card_Image);
            Bitmap bitmap = BitmapFactory.decodeStream(image_url.openStream());
            card_image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        card_title = (TextView)card_view.findViewById(R.id.card_title);
        card_title.setText(card.card_title);

        card_sub_title = (TextView)card_view.findViewById(R.id.card_sub_title);
        card_sub_title.setText(card.card_sub_title);

        card_time = (TextView)card_view.findViewById(R.id.card_time);
        //하루 이틀...한시간...그저께
        card_time.setText("수정해야대~");

        card_tag = (LinearLayout)card_view.findViewById(R.id.card_tag);
        for(String tag : card.card_tag){
            LinearLayout.LayoutParams tag_params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tag_view = new TextView(card_view.getContext());
            tag_view.setText(tag);
            tag_view.setBackgroundResource(R.drawable.round_tag_background);
            tag_view.setPadding(10,0,10,0);
            card_tag.addView(tag_view);
        }

        card_comment = (TextView)card_view.findViewById(R.id.card_comment);
        card_comment.setText(card.card_comment);

        creator_image = (ImageView)card_view.findViewById(R.id.card_creator_image);
        try {
            URL image_url = new URL(card.card_creator_image);
            Bitmap bitmap = BitmapFactory.decodeStream(image_url.openStream());
            creator_image.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        creator_title = (TextView)card_view.findViewById(R.id.card_creator_title);
        creator_title.setText(card.card_title);
        creator_sub_title = (TextView)card_view.findViewById(R.id.card_creator_sub_title);
        creator_sub_title.setText(card.card_sub_title);

        card_light_btn = (ImageButton)card_view.findViewById(R.id.card_light_btn);
        card_light_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_comment_btn = (ImageButton)card_view.findViewById(R.id.card_comment_btn);
        card_comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_collect_btn = (ImageButton)card_view.findViewById(R.id.card_collect_btn);
        card_collect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        card_light_count = (TextView)card_view.findViewById(R.id.card_light_count);
        card_light_count.setText(card.light_count);
        card_comment_count = (TextView)card_view.findViewById(R.id.card_comment_count);
        card_comment_count.setText(card.comment_count);
        card_collect_count = (TextView)card_view.findViewById(R.id.card_collect_count);
        card_collect_count.setText(card.collect_cont);

        return card_view;
    }
}
