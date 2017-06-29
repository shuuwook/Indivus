package indivus.cosmos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-29.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<PostItems> item_list;
    View.OnClickListener click_listener;

    public PostRecyclerAdapter(ArrayList<PostItems> item_list, View.OnClickListener click_listener) {
        this.item_list = item_list;
        this.click_listener = click_listener;
    }

    @Override
    public PostRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recyclerview_item,parent,false);
        view.setOnClickListener(click_listener);
        PostRecyclerViewHolder view_holder = new PostRecyclerViewHolder(view);


        return view_holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PostRecyclerViewHolder mholder = (PostRecyclerViewHolder) holder;
        mholder.img.setImageResource(item_list.get(position).img);
        mholder.text.setText(item_list.get(position).text);

    }

    @Override
    public int getItemCount() {

        //아이템 카운터
        return item_list != null ? item_list.size() : 0;
    }
}