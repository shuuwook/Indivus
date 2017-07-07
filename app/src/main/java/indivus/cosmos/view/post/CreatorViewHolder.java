package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-06-30.
 */

public class CreatorViewHolder extends RecyclerView.ViewHolder{
    public ImageView profile_img;
    public TextView profile_name;
    public TextView profile_position;

    public Button profile_btn;

    public TextView comment;

    public CreatorViewHolder(View itemView) {

        super(itemView);

        profile_img = (ImageView)itemView.findViewById(R.id.post_profile_img);
        profile_name = (TextView)itemView.findViewById(R.id.post_profile_name);
        profile_position = (TextView)itemView.findViewById(R.id.post_profile_position);

        profile_btn = (Button)itemView.findViewById(R.id.post_profile_btn);
        comment = (TextView)itemView.findViewById(R.id.post_comment);
    }
}
