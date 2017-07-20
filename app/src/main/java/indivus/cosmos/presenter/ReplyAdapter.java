package indivus.cosmos.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import indivus.cosmos.model.server.reply.ReplyResult;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {

    Context context;
    ArrayList<ReplyResult.Reply> reply_result;

    public ReplyAdapter(Context context, ReplyResult result) {
        this.context = context;
        reply_result = result.result;
        Log.i("reply", reply_result.size()+"");
    }

    @Override
    public ReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_reply_recyclerview, parent, false);
        return new ReplyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ReplyViewHolder holder, int position) {
        ReplyResult.Reply result = reply_result.get(position);
        Log.i("reply", result.contents);
        ImageView profile_img = holder.profile_img;
        Glide.with(context).load(Uri.parse(result.getProfilePhoto())).thumbnail(0.1f).into(profile_img);

        TextView profile_name = holder.profile_name;
        profile_name.setText(result.getUsername());

        TextView reply_content = holder.reply_content;
        reply_content.setText(result.getContents());

        TextView reply_time = holder.reply_time;
        reply_time.setText(Indivus.getRelativeDate(result.getCommentDate()));

        TextView reply_reply_count = holder.reply_reply_count;
        reply_reply_count.setText(result.getCommentdetail_counts()+"");

        TextView reply_bulb_count = holder.reply_bulb_count;
        reply_bulb_count.setText(result.getLikeCounts()+"");

        Button reply_bulb_btn = holder.reply_bulb_btn;
        reply_bulb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button reply_reply_btn = holder.reply_reply_btn;
        reply_reply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return reply_result != null ? reply_result.size() : 0;
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_img;
        TextView profile_name;
        TextView reply_content;
        TextView reply_time;

        Button reply_reply_btn;
        TextView reply_reply_count;

        Button reply_bulb_btn;
        TextView reply_bulb_count;

        public ReplyViewHolder(View itemView) {
            super(itemView);

            profile_img = (ImageView)itemView.findViewById(R.id.reply_recyclerview_profile_img);
            profile_name = (TextView)itemView.findViewById(R.id.reply_recyclerview_profile_name);
            reply_content = (TextView)itemView.findViewById(R.id.reply_recyclerview_reply);
            reply_time = (TextView)itemView.findViewById(R.id.reply_recyclerview_reply_time);

            reply_reply_btn = (Button)itemView.findViewById(R.id.reply_reply_btn);
            reply_bulb_btn = (Button)itemView.findViewById(R.id.reply_bulb_btn);

            reply_reply_count = (TextView)itemView.findViewById(R.id.reply_reply_count);
            reply_bulb_count = (TextView)itemView.findViewById(R.id.reply_bulb_count);
        }
    }
}
