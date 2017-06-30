package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import indivus.cosmos.model.server.CategoryResult;
import indivus.cosmos.presenter.CategoriesCallBack;
import indivus.cosmos.presenter.ResponseCallBack;
import indivus.cosmos.presenter.SelectCategoryAdapter;
import indivus.cosmos.presenter.SignUpPresenter;

/**
 * Created by Administrator on 2017-06-28.
 */

public class SignUpSelectCategoryActivity extends Activity {

    SignUpPresenter signup_presenter;

    private SelectCategoryAdapter adapter;
    private GridLayoutManager grid_layout_manager;
    private RecyclerView recycler_view;

    Button sign_up_btn;

    int user_code;
    ArrayList<Integer> categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_select_category);

        recycler_view = (RecyclerView)findViewById(R.id.select_category_recycler);
        sign_up_btn = (Button)findViewById(R.id.signup_btn);

        user_code = getIntent().getIntExtra("user_code", 0);
        categories = new ArrayList<Integer>();

        signup_presenter = new SignUpPresenter();

        signup_presenter.getCategoryList(new CategoriesCallBack() {
            @Override
            public void getCategories(ArrayList<CategoryResult.Category> categories) {
                grid_layout_manager = new GridLayoutManager(SignUpSelectCategoryActivity.this, 2);
                adapter = new SelectCategoryAdapter(SignUpSelectCategoryActivity.this, categories);

                recycler_view.setLayoutManager(grid_layout_manager);
                recycler_view.setAdapter(adapter);

                //recycler view loading 체감을 없애기 위해 캐쉬 확장
                recycler_view.setHasFixedSize(true);
                recycler_view.setItemViewCacheSize(20);
                recycler_view.setDrawingCacheEnabled(true);
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categories = adapter.getCategory_position();
                for(int position : categories){
                    Log.i("num", position+"");
                }
                signup_presenter.SignUpCategories(user_code, categories, new ResponseCallBack() {
                    @Override
                    public void onSuccess(int result) {
                        finish();
                    }

                    @Override
                    public void onError(int result) {
                        Toast.makeText(getApplicationContext(), "회원가입 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
