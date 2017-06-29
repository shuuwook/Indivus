package indivus.cosmos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-29.
 */

public class PostRecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView img;
    public TextView text;

    public PostRecyclerViewHolder(View itemView) {
        super(itemView);

        img = (ImageView)itemView.findViewById(R.id.post_recyclerview_item_imageview);
        text = (TextView) itemView.findViewById(R.id.post_recyclerview_item_textview);
    }
}
