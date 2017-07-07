package indivus.cosmos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.app.SearchManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.explorer.ExplorerContentResult;
import indivus.cosmos.model.server.explorer.ExplorerCreatorResult;
import indivus.cosmos.model.server.explorer.ExplorerData;
import indivus.cosmos.presenter.ExplorerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowok on 2017-06-25.
 */

public class ExplorePager extends Fragment {
    private final int CONTENTS = 0;
    private final int CREATOR = 1;

    ImageButton search_btn;
    EditText search_edit;
    TabLayout explorer_tab_layout;
    RecyclerView explorer_recycler;
    ExplorerAdapter adapter;

    NetworkService service;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = Indivus.getInstance().getNetworkService();
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pager_explore, container, false);

        search_edit = (EditText)v.findViewById(R.id.explorer_search_edit);
        search_btn = (ImageButton)v.findViewById(R.id.explorer_search_icon);
        explorer_tab_layout = (TabLayout)v.findViewById(R.id.explorer_tab_layout);
        explorer_recycler = (RecyclerView)v.findViewById(R.id.explorer_recyclerview);
        adapter = new ExplorerAdapter(getContext());

        LinearLayoutManager layout_manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        explorer_recycler.setLayoutManager(layout_manager);
        explorer_recycler.setAdapter(adapter);

        //초기 상태 툴바 숨김
        explorer_tab_layout.setVisibility(View.GONE);

        search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL){
                    search_btn.callOnClick();
                }
                return false;
            }
        });

        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                explorer_tab_layout.setVisibility(View.GONE);
                adapter.setCreator(null);
                adapter.setContent(null);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword;
                if((keyword = search_edit.getText().toString()).length() > 0){
                    String token = Indivus.getInstance().getPreferences();
                    ExplorerData data = new ExplorerData(keyword);
                    Call<ExplorerContentResult> contentResultCall = service.searchExplorerContent(token, data);
                    contentResultCall.enqueue(new Callback<ExplorerContentResult>() {
                        @Override
                        public void onResponse(Call<ExplorerContentResult> call, Response<ExplorerContentResult> response) {
                            if(response.isSuccessful()){
                                adapter.setContent(response.body().result);
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                int statusCode = response.code();
                                Log.i("status : ", statusCode + "");
                            }
                        }
                        @Override
                        public void onFailure(Call<ExplorerContentResult> call, Throwable t) {
                        }
                    });
                    Call<ExplorerCreatorResult> creatorResultCall = service.searchExplorerCreator(token, data);
                    creatorResultCall.enqueue(new Callback<ExplorerCreatorResult>() {
                        @Override
                        public void onResponse(Call<ExplorerCreatorResult> call, Response<ExplorerCreatorResult> response) {
                            if(response.isSuccessful()){
                                adapter.setCreator(response.body().result);
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                int statusCode = response.code();
                                Log.i("status : ", statusCode + "");
                            }
                        }
                        @Override
                        public void onFailure(Call<ExplorerCreatorResult> call, Throwable t) {
                        }
                    });

                    explorer_tab_layout.setVisibility(View.VISIBLE);
                }
            }
        });

        explorer_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case CONTENTS :
                        adapter.setTabPostition(CONTENTS);
                        adapter.notifyDataSetChanged();
                        break;
                    case CREATOR :
                        adapter.setTabPostition(CREATOR);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;

    }
}
