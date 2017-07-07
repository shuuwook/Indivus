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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

import indivus.cosmos.R;
import indivus.cosmos.model.server.explorer.ExplorerContentResult;
import indivus.cosmos.model.server.explorer.ExplorerCreatorResult;

/**
 * Created by seowo on 2017-07-06.
 */

public class ExplorerAdapter extends RecyclerView.Adapter{
    private final int CONTENTS = 0;
    private final int CREATOR = 1;

    Context context;

    ArrayList<ExplorerContentResult.ExplorerContent> content_result;
    ArrayList<ExplorerCreatorResult.ExplorerCreator> creator_result;

    int tab_position;

    public ExplorerAdapter(Context context) {
        this.context = context;
    }

    public void setTabPostition(int tab_position){
        this.tab_position = tab_position;
    }

    public void setContent(ArrayList<ExplorerContentResult.ExplorerContent> content_result){
        this.content_result = content_result;
    }

    public void setCreator(ArrayList<ExplorerCreatorResult.ExplorerCreator> creator_result){
        this.creator_result = creator_result;
    }

    @Override
    public int getItemViewType(int position) {
        if(tab_position == CONTENTS) return CONTENTS;
        else if(tab_position == CREATOR) return CREATOR;
        else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == CONTENTS) {
            View content_view = LayoutInflater.from(context).inflate(R.layout.item_explorer_contents, parent, false);
            return new ExplorerContentViewHolder(content_view);
        }
        //tab_position == CREATOR
        else {
            View creator_view = LayoutInflater.from(context).inflate(R.layout.item_explorer_creator, parent, false);
            return new ExplorerCreatorViewHolder(creator_view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ExplorerContentViewHolder){
            ExplorerContentResult.ExplorerContent result = content_result.get(position);
            ExplorerContentViewHolder content_holder = (ExplorerContentViewHolder)holder;

            Glide.with(context).load(Uri.parse(result.card_cover)).thumbnail(0.1f).into(content_holder.content_img);
            content_holder.content_title.setText(result.title);
            content_holder.content_name.setText(result.username);
            content_holder.bulb_count.setText(result.like_counts+"");
            //like event
            //content_holder.bulb_btn.setOnClickListener();
        }
        else if(holder instanceof ExplorerCreatorViewHolder){
            ExplorerCreatorResult.ExplorerCreator result = creator_result.get(position);
            ExplorerCreatorViewHolder creator_holder = (ExplorerCreatorViewHolder)holder;

            Log.i("explorerAdapter", result.profile_photo);
            Glide.with(context).load(Uri.parse(result.profile_photo)).thumbnail(0.1f).into(creator_holder.creator_img);
            creator_holder.creator_name.setText(result.name);
            creator_holder.creator_job.setText(result.jobs);
            creator_holder.creator_follower_count.setText(result.follower_counts+"");
        }
        else{
            //null
        }
    }

    @Override
    public int getItemCount() {
        if(tab_position == CONTENTS) return content_result != null ? content_result.size() : 0;
        else if(tab_position == CREATOR) return  creator_result != null ? creator_result.size() : 0;
        else return 0;
    }

    class ExplorerContentViewHolder extends RecyclerView.ViewHolder{
        public ImageView content_img;
        public TextView content_title;
        public TextView content_name;

        public Button bulb_btn;
        public TextView bulb_count;

        public ExplorerContentViewHolder(View itemView) {
            super(itemView);

            content_img = (ImageView)itemView.findViewById(R.id.item_explorer_contents_img);
            content_title = (TextView)itemView.findViewById(R.id.item_explorer_contents_title);
            content_name = (TextView)itemView.findViewById(R.id.item_explorer_contents_name);

            bulb_btn = (Button)itemView.findViewById(R.id.item_explorer_contents_bulb);
            bulb_count = (TextView) itemView.findViewById(R.id.item_explorer_contents_bulb_count);
        }
    }

    class ExplorerCreatorViewHolder extends RecyclerView.ViewHolder{
        ImageView creator_img;
        TextView creator_name;
        TextView creator_job;
        TextView creator_follower_count;

        public ExplorerCreatorViewHolder(View itemView) {
            super(itemView);
            creator_img = (ImageView)itemView.findViewById(R.id.item_explorer_creator_img);
            creator_name = (TextView)itemView.findViewById(R.id.item_explorer_creator_name);
            creator_job = (TextView)itemView.findViewById(R.id.item_explorer_creator_job);
            creator_follower_count = (TextView)itemView.findViewById(R.id.item_explorer_creator_follower_count);
        }
    }
}
