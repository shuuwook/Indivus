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
import indivus.cosmos.model.server.collection.CollectionResult;

/**
 * Created by Administrator on 2017-07-07.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    Context context;
    ArrayList<CollectionResult.Collection> collection_list;

    public CollectionAdapter(Context context, ArrayList<CollectionResult.Collection> collection_list) {
        this.context = context;
        this.collection_list = collection_list;
    }

    public int getCollectionId(int position){ return collection_list.get(position).collection_id; }

    @Override
    public CollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View collection_view = LayoutInflater.from(context).inflate(R.layout.item_myspace_folder, parent, false);

        return new CollectionViewHolder(collection_view);
    }

    @Override
    public void onBindViewHolder(CollectionViewHolder holder, int position) {
        //추가
        if(position == collection_list.size()){
            holder.collection_img.setImageResource(R.drawable.folderplus);
            holder.collection_title.setText("");
            holder.collection_count.setText("");
        }
        //콜렉션
        else {
            if(collection_list.get(position).collection_cover != null) {
                Glide.with(context).load(Uri.parse(collection_list.get(position).collection_cover)).thumbnail(0.1f).into(holder.collection_img);
            }
            holder.collection_title.setText(collection_list.get(position).collection_name);
            holder.collection_count.setText(collection_list.get(position).collection_counts+"");
        }
    }

    @Override
    public int getItemCount() {
        return collection_list != null ? collection_list.size() + 1 : 0;
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {
        ImageView collection_img;
        TextView collection_title;
        TextView collection_count;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            collection_img = (ImageView)itemView.findViewById(R.id.item_myspace_scrollview_img);
            collection_title = (TextView)itemView.findViewById(R.id.item_myspace_scrollview_title);
            collection_count = (TextView)itemView.findViewById(R.id.item_myspace_scrollview_count);
        }
    }
}
