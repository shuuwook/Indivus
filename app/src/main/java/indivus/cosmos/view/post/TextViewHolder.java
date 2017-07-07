package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-30.
 */

public class TextViewHolder extends RecyclerView.ViewHolder {
    public TextView post_content_text;

    public TextViewHolder(View itemView) {
        super(itemView);

        post_content_text = (TextView)itemView.findViewById(R.id.post_content_text);
    }
}
