package indivus.cosmos.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.server.series.SeriesResult;

/**
 * Created by seowo on 2017-07-04.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder> {

    Context context;
    ArrayList<SeriesResult.PostSeries> series_list;

    public SeriesAdapter(Context context, ArrayList<SeriesResult.PostSeries> series_list) {
        this.context = context;
        this.series_list = series_list;
    }

    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_post_series, parent, false);
        return new SeriesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SeriesViewHolder holder, int position) {

        ImageView series_cover = holder.series_cover;

        TextView series_title = holder.series_title;
        TextView series_sub_title = holder.series_sub_title;

        TextView series_date = holder.series_date;
        TextView series_view = holder.series_view;

        Glide.with(context).load(series_list.get(position).card_cover).thumbnail(0.1f).into(series_cover);

        series_title.setText(series_list.get(position).title);
        series_sub_title.setText(series_list.get(position).sub_title);

        series_date.setText(Indivus.getRelativeDate(series_list.get(position).posting_date));
        series_view.setText(series_list.get(position).view_counts+"");

    }

    @Override
    public int getItemCount() {
        return series_list != null ? series_list.size() : 0;
    }

    class SeriesViewHolder extends RecyclerView.ViewHolder{
        ImageView series_cover;

        TextView series_title;
        TextView series_sub_title;

        TextView series_date;
        TextView series_view;

        public SeriesViewHolder(View itemView) {
            super(itemView);

            series_cover = (ImageView)itemView.findViewById(R.id.item_series_image);
            series_title = (TextView)itemView.findViewById(R.id.item_series_title);
            series_sub_title = (TextView)itemView.findViewById(R.id.item_series_sub_title);

            series_date = (TextView)itemView.findViewById(R.id.item_series_date);
            series_view = (TextView)itemView.findViewById(R.id.item_series_view);
        }
    }
}
