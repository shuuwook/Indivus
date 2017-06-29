package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017-06-28.
 */

public class SignUpSelectCategoryActivity extends Activity {

    Button select_category_a_btn;
    Button select_category_b_btn;
    Button select_category_c_btn;
    Button select_category_d_btn;
    Button select_category_e_btn;
    Button select_category_f_btn;
    Button select_category_signup_btn;
    boolean[] select_categories;


    Drawable select_category_a_btn_background;
    Drawable select_category_b_btn_background;
    Drawable select_category_c_btn_background;
    Drawable select_category_d_btn_background;
    Drawable select_category_e_btn_background;
    Drawable select_category_f_btn_background;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_select_category);
        select_category_a_btn = (Button) findViewById(R.id.select_category_a);
        select_category_b_btn = (Button) findViewById(R.id.select_category_b);
        select_category_c_btn = (Button) findViewById(R.id.select_category_c);
        select_category_d_btn = (Button) findViewById(R.id.select_category_d);
        select_category_e_btn = (Button) findViewById(R.id.select_category_e);
        select_category_f_btn = (Button) findViewById(R.id.select_category_f);
        select_category_signup_btn = (Button) findViewById(R.id.select_category_signup_btn);


        select_category_a_btn_background = select_category_a_btn.getBackground();
        select_category_b_btn_background = select_category_b_btn.getBackground();
        select_category_c_btn_background = select_category_c_btn.getBackground();
        select_category_d_btn_background = select_category_d_btn.getBackground();
        select_category_e_btn_background = select_category_e_btn.getBackground();
        select_category_f_btn_background = select_category_f_btn.getBackground();

        /*버튼 이미지, 텍스트 받아오기
        select_category_a_btn.getResources().getDrawable();
        select_category_b_btn.getResources().getDrawable();
        select_category_c_btn.getResources().getDrawable();
        select_category_d_btn.getResources().getDrawable();
        select_category_e_btn.getResources().getDrawable();
        select_category_f_btn.getResources().getDrawable();

        select_category_a_btn.getResources().getText();
        select_category_b_btn.getResources().getText();
        select_category_c_btn.getResources().getText();
        select_category_d_btn.getResources().getText();
        select_category_e_btn.getResources().getText();
        select_category_f_btn.getResources().getText();
        */
        select_category_a_btn_background.setAlpha(50);
        select_category_b_btn_background.setAlpha(50);
        select_category_c_btn_background.setAlpha(50);
        select_category_d_btn_background.setAlpha(50);
        select_category_e_btn_background.setAlpha(50);
        select_category_f_btn_background.setAlpha(50);

        select_categories = new boolean[] {false, false, false, false, false, false};

        select_category_a_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[0]) {
                    select_categories[0] = true;
                    select_category_a_btn_background.setAlpha(255);
                }
                else {
                    select_categories[0] = false;
                    select_category_a_btn_background.setAlpha(50);
                }
            }
        });
        select_category_b_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[1]) {
                    select_categories[1] = true;
                    select_category_b_btn_background.setAlpha(255);
                }
                else {
                    select_categories[1] = false;
                    select_category_b_btn_background.setAlpha(50);
                }
            }
        });
        select_category_c_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[2]) {
                    select_categories[2] = true;
                    select_category_c_btn_background.setAlpha(255);
                }
                else {
                    select_categories[2] = false;
                    select_category_c_btn_background.setAlpha(50);
                }
            }
        });
        select_category_d_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[3]) {
                    select_categories[3] = true;
                    select_category_d_btn_background.setAlpha(255);
                }
                else {
                    select_categories[3] = false;
                    select_category_d_btn_background.setAlpha(50);
                }
            }
        });
        select_category_e_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[4]) {
                    select_categories[4] = true;
                    select_category_e_btn_background.setAlpha(255);
                }
                else {
                    select_categories[4] = false;
                    select_category_e_btn_background.setAlpha(50);
                }
            }
        });
        select_category_f_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!select_categories[5]) {
                    select_categories[5] = true;
                    select_category_f_btn_background.setAlpha(255);
                }
                else {
                    select_categories[5] = false;
                    select_category_f_btn_background.setAlpha(50);
                }
            }
        });

        select_category_signup_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean result = false;
                int count=0;
                for (int i = 0; i< select_categories.length;i++){
                    if (select_categories[i]){
                        count++;
                    }
                }
                if (count<5 && count!= 0 ){
                    result = true;
                }
                if (result) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });


    }
}
