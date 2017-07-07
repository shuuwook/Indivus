package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-30.
 */

public class ButtonViewHolder extends RecyclerView.ViewHolder {
    public Button bulb_btn;
    public Button reply_btn;
    public Button collect_btn;

    public TextView bulb_count;
    public TextView reply_count;
    public TextView collect_count;

    public ButtonViewHolder(View itemView) {
        super(itemView);

        bulb_btn = (Button)itemView.findViewById(R.id.post_bulb_btn);
        reply_btn = (Button)itemView.findViewById(R.id.post_reply_btn);
        collect_btn = (Button)itemView.findViewById(R.id.post_collect_btn);

        bulb_count = (TextView)itemView.findViewById(R.id.post_bulb_count);
        reply_count = (TextView)itemView.findViewById(R.id.post_reply_count);
        collect_count = (TextView)itemView.findViewById(R.id.post_collect_count);
    }
}
