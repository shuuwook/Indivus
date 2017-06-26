package indivus.cosmos.presenter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import indivus.cosmos.HomeViewHolder;
import indivus.cosmos.R;
import indivus.cosmos.model.data.Card;

/**
 * Created by seowo on 2017-06-26.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private ArrayList<Card> card_list;

    //server로부터 받은 데이터로 만든 arraylist를 setting
    public void setCardData(){
        //test용
        card_list = new ArrayList<Card>();
        card_list.add(new Card("test", "test", "test", "testest"));
        card_list.add(new Card("test", "test", "test", "testest"));
        card_list.add(new Card("test", "test", "test", "testest"));
        card_list.add(new Card("test", "test", "test", "testest"));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_home, parent, false);

        HomeViewHolder holder = new HomeViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeViewHolder view_holer = (HomeViewHolder)holder;

        //view_holer.profile_image.setImageURI(Uri.parse(card_list.get(position).getImage()));
        view_holer.profile_name.setText(card_list.get(position).getName());
        view_holer.profile_sub.setText(card_list.get(position).getSub());
        view_holer.profile_comment.setText(card_list.get(position).getComment());

    }

    @Override
    public int getItemCount() {
        return card_list.isEmpty() ? 0 : card_list.size();
    }
}
