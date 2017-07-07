package indivus.cosmos.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.model.data.PostRecommend;
import indivus.cosmos.view.post.RecommendItemHolder;

/**
 * Created by seowo on 2017-07-01.
 */

public class PostRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<PostRecommend> recommend_list;

    public PostRecommendAdapter(Context context, ArrayList<PostRecommend> recommend_list) {
        this.context = context;
        this.recommend_list = recommend_list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post_recommend, parent, false);
        return new RecommendItemHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommendItemHolder item_holder = (RecommendItemHolder) holder;
        Button item_btn = item_holder.recommend_card_btn;
        ImageView item_image = item_holder.recommend_card_image;
        TextView item_title = item_holder.recommend_card_title;
        TextView item_sub_title = item_holder.recommend_card_sub_title;

        PostRecommend recommend_item = recommend_list.get(position);

        //item_btn.setOnClickListener();

        Glide.with(context).load(recommend_item.getCardCover()).thumbnail(0.1f).into(item_image);

        item_title.setText(recommend_item.getTitle());
        item_sub_title.setText(recommend_item.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return recommend_list != null ? recommend_list.size() : 0;
    }
}
