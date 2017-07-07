package indivus.cosmos.presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.widget.CardView;
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
import indivus.cosmos.model.server.signup.CategoryResult;

/**
 * Created by seowo on 2017-06-29.
 */

public class SelectCategoryAdapter extends RecyclerView.Adapter<SelectCategoryAdapter.SelectCategoryViewHolder>{

    Context context;
    ArrayList<CategoryResult.Category> categories;
    ArrayList<Integer> category_position;

    public ArrayList<Integer> getCategory_position() {
        return category_position;
    }

    public SelectCategoryAdapter(Context context, ArrayList<CategoryResult.Category> categories) {
        this.context = context;
        this.categories = categories;
        category_position = new ArrayList<Integer>();
    }

    @Override
    public SelectCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_sign_up, parent, false);

        return new SelectCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SelectCategoryViewHolder holder, final int position) {
        //override(width, height)로 pixel 조절 가능
        Glide.with(context).load(Uri.parse(categories.get(position).getCategoryImage())).thumbnail(0.1f).into(holder.select_category_img);
        holder.select_category_text.setText(categories.get(position).category_name);
        holder.select_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택
                if(category_position.contains(position + 1) == false) {
                    category_position.add(position + 1);
                    holder.category_card.setAlpha(1);
                }
                //선택 취소
                else{
                    category_position.remove(new Integer(position + 1));
                    holder.category_card.setAlpha((float)0.5);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public class SelectCategoryViewHolder extends RecyclerView.ViewHolder{

        CardView category_card;
        ImageView select_category_img;
        TextView select_category_text;

        Button select_category_btn;

        public SelectCategoryViewHolder(View itemView) {
            super(itemView);
            category_card = (CardView)itemView.findViewById(R.id.item_category_sign_up_card);
            select_category_img = (ImageView)itemView.findViewById(R.id.item_category_sign_up_img);
            select_category_text = (TextView)itemView.findViewById(R.id.item_category_sign_up_text);
            select_category_btn = (Button)itemView.findViewById(R.id.item_category_sign_up_btn);
        }
    }
}
