package indivus.cosmos.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.model.server.closet.ClosetResult;

/**
 * Created by seowo on 2017-07-07.
 */

public class ClosetAdapter extends RecyclerView.Adapter<ClosetAdapter.ClosetViewHolder>{

    Context context;
    ArrayList<ClosetResult.Closet> closet_list;

    public ClosetAdapter(Context context, ArrayList<ClosetResult.Closet> closet_list) {
        this.context = context;
        this.closet_list = closet_list;
    }

    @Override
    public ClosetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View closet_view = LayoutInflater.from(context).inflate(R.layout.item_myspace_closet, parent, false);

        return new ClosetViewHolder(closet_view);
    }

    @Override
    public void onBindViewHolder(ClosetViewHolder holder, int position) {
        Glide.with(context).load(Uri.parse(closet_list.get(position).image)).thumbnail(0.1f).into(holder.closet_img);
        holder.closet_text.setText(closet_list.get(position).card_name);
    }

    @Override
    public int getItemCount() {
        return closet_list != null ? closet_list.size() : 0;
    }

    class ClosetViewHolder extends RecyclerView.ViewHolder{

        ImageView closet_img;
        TextView closet_text;

        public ClosetViewHolder(View itemView) {
            super(itemView);
            closet_img = (ImageView)itemView.findViewById(R.id.item_myspace_closet_img);
            closet_text = (TextView)itemView.findViewById(R.id.item_myspace_closet_text);
        }
    }
}
