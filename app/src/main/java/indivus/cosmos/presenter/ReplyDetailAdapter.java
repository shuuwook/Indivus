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
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.server.reply.ReplyDetailResult;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    ArrayList<ReplyDetailResult.ReplyDetail> reply_detail_result;
    public ReplyDetailAdapter(Context context, ReplyDetailResult result) {
        this.context = context;
        reply_detail_result = result.result;
    }

    @Override
    public ReplyDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_reply_recyclerview, parent, false);
        return new ReplyDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 1){
            ReplyAdapter.ReplyViewHolder reply_holder = (ReplyAdapter.ReplyViewHolder)holder;


        }
        else{
            ReplyDetailResult.ReplyDetail result = reply_detail_result.get(position);

            ReplyDetailViewHolder detail_holder = (ReplyDetailViewHolder)holder;

            ImageView profile_img = detail_holder.profile_img;
            TextView profile_name = detail_holder.profile_name;
            TextView reply_content = detail_holder.reply_content;
            TextView reply_time = detail_holder.reply_time;

            Button reply_bulb_btn = detail_holder.reply_bulb_btn;
            TextView reply_bulb_count = detail_holder.reply_bulb_count;

            Glide.with(context).load(result.getProfile_photo()).thumbnail(0.1f).into(profile_img);
            profile_name.setText(result.getUsername());
            reply_content.setText(result.getContents());
            reply_time.setText(Indivus.getRelativeDate(result.getComment_date()));

            reply_bulb_count.setText(result.getLike_counts());

            reply_bulb_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return reply_detail_result != null ? reply_detail_result.size() : 0;
    }

    class ReplyDetailViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_img;
        TextView profile_name;
        TextView reply_content;
        TextView reply_time;

        Button reply_bulb_btn;
        TextView reply_bulb_count;

        public ReplyDetailViewHolder(View itemView) {
            super(itemView);

            profile_img = (ImageView)itemView.findViewById(R.id.reply_reply_recyclerview_profile_img);
            profile_name = (TextView)itemView.findViewById(R.id.reply_recyclerview_profile_name);
            reply_content = (TextView)itemView.findViewById(R.id.reply_reply_recyclerview_content);
            reply_time = (TextView)itemView.findViewById(R.id.reply_reply_recyclerview_time);

            reply_bulb_btn = (Button)itemView.findViewById(R.id.reply_reply_bulb_btn);

            reply_bulb_count = (TextView)itemView.findViewById(R.id.reply_reply_bulb_count);
        }
    }
}
