package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-30.
 */

public class RecommendViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recommend_recycler;

    public RecommendViewHolder(View itemView) {
        super(itemView);

        recommend_recycler = (RecyclerView)itemView.findViewById(R.id.post_recommend_recycler);
    }
}
