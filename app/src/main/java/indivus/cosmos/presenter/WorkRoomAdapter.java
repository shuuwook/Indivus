package indivus.cosmos.presenter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.WorkRoomFragment;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.workroom.WorkRoomResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-06.
 */

public class WorkRoomAdapter extends RecyclerView.Adapter<WorkRoomAdapter.WorkRoomViewHolder> {

    Context context;
    ArrayList<WorkRoomResult.WorkRoomSeries> series_list;

    public WorkRoomAdapter(Context context, ArrayList<WorkRoomResult.WorkRoomSeries> series_list) {
        this.context = context;
        this.series_list = series_list;
    }

    public String getSeriesName(int position){
        return series_list.get(position).series_name;
    }

    @Override
    public WorkRoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_myspace_folder, parent, false);

        return new WorkRoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WorkRoomViewHolder holder, int position) {
        //추가
        if(position == series_list.size()){
            holder.work_room_img.setImageResource(R.drawable.folderplus);
            holder.work_room_title.setText("");
            holder.work_room_count.setText("");
        }
        //시리즈
        else{
            if(series_list.get(position).card_cover != null) {
                Glide.with(context).load(Uri.parse(series_list.get(position).card_cover)).thumbnail(0.1f).into(holder.work_room_img);
            }
            holder.work_room_title.setText(series_list.get(position).series_name);
            holder.work_room_count.setText(series_list.get(position).series_counts+"");
        }
    }

    @Override
    public int getItemCount() {
        return series_list != null ? series_list.size() + 1 : 0;
    }

    class WorkRoomViewHolder extends RecyclerView.ViewHolder{
        ImageView work_room_img;
        TextView work_room_title;
        TextView work_room_count;

        public WorkRoomViewHolder(View itemView) {

            super(itemView);
            work_room_img = (ImageView)itemView.findViewById(R.id.item_myspace_scrollview_img);
            work_room_title = (TextView)itemView.findViewById(R.id.item_myspace_scrollview_title);
            work_room_count = (TextView)itemView.findViewById(R.id.item_myspace_scrollview_count);
        }
    }
}
