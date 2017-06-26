package indivus.cosmos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by seowo on 2017-06-26.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {

    public ImageView profile_image;
    public TextView profile_name;
    public TextView profile_sub;
    public TextView profile_comment;

    public HomeViewHolder(View itemView) {
        super(itemView);

        profile_image = (ImageView)itemView.findViewById(R.id.home_card_profile_image);
        profile_name = (TextView)itemView.findViewById(R.id.home_card_profile_name);
        profile_sub = (TextView)itemView.findViewById(R.id.home_card_profile_sub);
        profile_comment = (TextView)itemView.findViewById(R.id.home_card_profile_comment);
    }
}
