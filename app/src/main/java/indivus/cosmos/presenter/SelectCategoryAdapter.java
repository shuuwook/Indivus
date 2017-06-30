package indivus.cosmos.presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.model.server.CategoryResult;

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
        SelectCategoryViewHolder view_holder = new SelectCategoryViewHolder(view);

        return view_holder;
    }

    @Override
    public void onBindViewHolder(final SelectCategoryViewHolder holder, final int position) {
        //override(width, height)로 pixel 조절 가능
        Glide.with(context).load(Uri.parse(categories.get(position).getCategoryImage())).thumbnail(0.1f).into(holder.select_button);
    }

    @Override
    public int getItemCount() {
        return categories != null ? categories.size() : 0;
    }

    public class SelectCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        boolean isSelected = false;
        ImageView select_button;
        Button sign_up_btn;


        public SelectCategoryViewHolder(View itemView) {
            super(itemView);

            select_button = (ImageView) itemView.findViewById(R.id.select_category_btn);
            sign_up_btn = (Button)itemView.findViewById(R.id.signup_btn);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //선택
            if(!isSelected) {
                category_position.add(getAdapterPosition()+1);
                select_button.setColorFilter(Color.rgb(123, 123, 123), PorterDuff.Mode.MULTIPLY);
                isSelected = true;
            }
            //선택 취소
            else{
                category_position.remove(getAdapterPosition()+1);
                select_button.clearColorFilter();
                isSelected = false;
            }
        }
    }
}
