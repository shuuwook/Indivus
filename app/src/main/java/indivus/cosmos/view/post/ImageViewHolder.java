package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-30.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder {
    public ImageView post_content_image;

    public ImageViewHolder(View itemView) {
        super(itemView);

        post_content_image = (ImageView)itemView.findViewById(R.id.post_content_image);
    }
}
