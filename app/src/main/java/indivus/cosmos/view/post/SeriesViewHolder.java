package indivus.cosmos.view.post;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import indivus.cosmos.R;

/**
 * Created by seowo on 2017-07-01.
 */

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public Button series_previous_btn;
    public Button series_next_btn;
    public Button series_view_btn;
    public SeriesViewHolder(View itemView) {
        super(itemView);

        series_previous_btn = (Button) itemView.findViewById(R.id.series_previous_btn);
        series_next_btn = (Button) itemView.findViewById(R.id.series_next_btn);
        series_view_btn = (Button) itemView.findViewById(R.id.series_view_btn);
    }
}
