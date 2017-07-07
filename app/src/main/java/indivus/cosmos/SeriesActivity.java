package indivus.cosmos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.presenter.SeriesAdapter;

public class SeriesActivity extends AppCompatActivity {

    RecyclerView series_recycler;
    SeriesAdapter adapter;
    RecyclerView.LayoutManager layout_manager;
    ArrayList<SeriesResult.PostSeries> series_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        series_list = (ArrayList<SeriesResult.PostSeries>)getIntent().getSerializableExtra("series");

        layout_manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new SeriesAdapter(getApplicationContext(), series_list);
        series_recycler = (RecyclerView)findViewById(R.id.series_recycler);

        series_recycler.setLayoutManager(layout_manager);
        series_recycler.setAdapter(adapter);

        ItemClickSupport.addTo(series_recycler).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra("post_id", series_list.get(position).post_id);

                startActivity(intent);
            }
        });
    }
}
