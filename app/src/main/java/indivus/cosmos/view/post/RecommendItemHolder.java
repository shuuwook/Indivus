package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-07-01.
 */

public class RecommendItemHolder extends RecyclerView.ViewHolder{
    public Button recommend_card_btn;
    public ImageView recommend_card_image;
    public TextView recommend_card_title;
    public TextView recommend_card_sub_title;

    public RecommendItemHolder(View itemView) {
        super(itemView);

        recommend_card_btn = (Button)itemView.findViewById(R.id.post_recommend_card_button);
        recommend_card_image = (ImageView)itemView.findViewById(R.id.post_recommend_card_image);
        recommend_card_title = (TextView)itemView.findViewById(R.id.post_recommend_card_title);
        recommend_card_sub_title = (TextView)itemView.findViewById(R.id.post_recommend_card_sub_title);
    }
}
