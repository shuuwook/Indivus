package indivus.cosmos;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017-06-29.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int image[] = {R.drawable.mainkey_a, R.drawable.mainkey_b, R.drawable.mainkey_c}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int width = parent.getResources().getDisplayMetrics().widthPixels/3

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recyclerview_item,parent,false);
        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(width,width);
        //xml 디자인 적용


        return new RowCell;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //xml 디자인 내용물 변경
        ((RowCell)holder).imageView.setImageResource(image[position]);

    }

    @Override
    public int getItemCount() {

        //아이템 카운터터
        return image.length;
    }

    private static class RowCell extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public RowCell(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.post_recyclerview_item_imageview)
        }
    }
}