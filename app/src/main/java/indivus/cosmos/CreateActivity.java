package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.Content;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.category.CreateCategoryResult;
import indivus.cosmos.model.server.category.CreateKeyCardResult;
import indivus.cosmos.model.server.create.CreateResult;
import indivus.cosmos.model.server.post.PostCategoryResult;
import indivus.cosmos.model.server.signup.CategoryResult;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    final int SELECT_COVER_IMAGE_CODE = 100;
    final int CREATE_CONTENT_CODE = 300;
    final private int TEXT = 0;
    final private int IMAGE = 1;

    static String category_id;

    ArrayList<CategoryResult.Category> categories;
    ArrayList<String> keycard_list;
    ArrayList<Content> modules;
    String each_content_data;

    Uri card_file;
    List<MultipartBody.Part> content_file;
    Uri data;

    TabLayout create_tab;

    LinearLayout create_category;
    LinearLayout create_keycard;

    EditText keycard_edit;
    EditText title_edit;
    EditText sub_title_edit;

    EditText explain_edit;

    ImageView create_content_image;
    ConstraintLayout content_layout;
    LinearLayout create_content_layout;

    EditText comment_edit;

    ImageButton card_cover_btn;

    CheckBox create_check_box;

    Button create_blossom_btn;

    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        service = Indivus.getInstance().getNetworkService();

        category_id = "0";
        keycard_list = new ArrayList<>();
        modules = new ArrayList<>();
        content_file = new ArrayList<MultipartBody.Part>();

        create_tab = (TabLayout)findViewById(R.id.create_tab_layout);

        create_category = (LinearLayout)findViewById(R.id.create_category_result);
        create_category.setOrientation(LinearLayout.HORIZONTAL);

        create_keycard = (LinearLayout)findViewById(R.id.create_keycard_result);
        create_keycard.setOrientation(LinearLayout.HORIZONTAL);

        keycard_edit = (EditText)findViewById(R.id.create_keycard_edittext);
        title_edit = (EditText)findViewById(R.id.create_title_edittext);
        sub_title_edit = (EditText)findViewById(R.id.create_sub_title_edittext);

        explain_edit = (EditText)findViewById(R.id.create_explain_edittext);

        create_content_image = (ImageView)findViewById(R.id.create_content_image);
        create_content_layout = (LinearLayout)findViewById(R.id.create_content_layout);
        content_layout = (ConstraintLayout) findViewById(R.id.create_content);

        comment_edit = (EditText)findViewById(R.id.create_comment_edittext);

        card_cover_btn = (ImageButton)findViewById(R.id.create_cardcover_imgbtn);

        create_check_box = (CheckBox)findViewById(R.id.create_agree_checkbox_btn);

        create_blossom_btn = (Button)findViewById(R.id.create_blossom_btn);

        //category, keycard Tag 추가
        String token = Indivus.getInstance().getPreferences();
        Call<PostCategoryResult> categoryResultCall = service.getCategories(token);
        categoryResultCall.enqueue(new Callback<PostCategoryResult>() {

            @Override
            public void onResponse(Call<PostCategoryResult> call, Response<PostCategoryResult> response) {
                if(response.isSuccessful()){
                    for(final CreateCategoryResult category : response.body().category_result) {
                        final Button category_btn = new Button(getApplicationContext());
                        category_btn.setText(category.category_name);
                        category_btn.setTextSize(10);
                        category_btn.setTextColor(Color.parseColor("#95989A"));
                        category_btn.setBackgroundResource(R.drawable.round_category_tag_background);
                        category_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                category_id = category.category_id + "";
                                category_btn.setTextColor(Color.parseColor("#" + category.category_color));
                            }
                        });

                        create_category.addView(category_btn);
                    }

                    for(final CreateKeyCardResult keycard : response.body().keycard_result) {
                        final Button keycard_btn = new Button(getApplicationContext());
                        keycard_btn.setText(keycard.keycard_name);
                        keycard_btn.setTextSize(10);
                        keycard_btn.setTextColor(Color.parseColor("#95989A"));
                        keycard_btn.setBackgroundResource(R.drawable.round_category_tag_background);
                        keycard_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(keycard_list.contains(keycard.keycard_name)){
                                    keycard_btn.setTextColor(Color.parseColor("#95989A"));
                                    keycard_list.remove(keycard.getKeycard_name());
                                }
                                else {
                                    keycard_btn.setTextColor(Color.parseColor("#" + keycard.keycard_color));
                                    keycard_list.add(keycard.getKeycard_name());
                                }
                            }
                        });
                        create_keycard.addView(keycard_btn);
                    }
                }
            }

            @Override
            public void onFailure(Call<PostCategoryResult> call, Throwable t) {

            }
        });

        card_cover_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_COVER_IMAGE_CODE);
            }
        });

        content_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent content_intent = new Intent(getApplicationContext(), CreateContentActivity.class);
                startActivityForResult(content_intent, CREATE_CONTENT_CODE);
            }
        });

        create_blossom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(create_check_box.isChecked()){
                    List<RequestBody> keycards = new ArrayList<RequestBody>();
                    List<RequestBody> contents = new ArrayList<RequestBody>();

                    RequestBody title = RequestBody.create(MediaType.parse("multipart/form_data"), title_edit.getText().toString());
                    RequestBody sub_title = RequestBody.create(MediaType.parse("multipart/form_data"), sub_title_edit.getText().toString());
                    RequestBody explain = RequestBody.create(MediaType.parse("multipart/form_data"), explain_edit.getText().toString());

                    String id = category_id;
                    RequestBody category_id = RequestBody.create(MediaType.parse("multipart/form_data"), id);

                    for(String str : keycard_list){
                        RequestBody keycard = RequestBody.create(MediaType.parse("multipart/form_data"), str);
                        keycards.add(keycard);
                    }

                    RequestBody comment = RequestBody.create(MediaType.parse("multipart/form_data"), comment_edit.getText().toString());
                    RequestBody content_type = RequestBody.create(MediaType.parse("multipart/form_data"), create_tab.getSelectedTabPosition()+"");

                    //card cover
                    //partName, file.getName, requestFile
                    MultipartBody.Part card_cover = MultipartBody.Part.createFormData("card_cover", getImageNameToUri(card_file), getImage(card_file));

                    for(Content module : modules) {
                        RequestBody content;
                        //text
                        if (module.type == TEXT) {
                            content = RequestBody.create(MediaType.parse("multipart/form_data"), module.src);
                            contents.add(content);
                        }
                        //image
                        else {
                            if(module.src != null) {
                                MultipartBody.Part file =  MultipartBody.Part.createFormData("contents", getImageNameToUri(Uri.parse(module.src)), getImage(Uri.parse(module.src)));

                                content_file.add(file);
                            }
                        }
                    }

                    RequestBody each_content_type = RequestBody.create(MediaType.parse("multipart/form_data"), each_content_data.toString());
                    String token = Indivus.getInstance().getPreferences();
                    Call<CreateResult> createResultCall = service.createPost(token, title, sub_title, explain
                            , category_id, keycards, comment, content_type, card_cover, contents, content_file, each_content_type);

                    createResultCall.enqueue(new Callback<CreateResult>() {
                        @Override
                        public void onResponse(Call<CreateResult> call, Response<CreateResult> response) {
                            if(response.isSuccessful()){
                                if(response.body().message.equals("create success")){
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateResult> call, Throwable t) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "약관 동의가 필요합니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    View.OnClickListener keycardListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button tag_btn = (Button)v;
            String tag_str = tag_btn.getText().toString();
            if(keycard_list.contains(tag_str)) {
                keycard_list.remove(tag_str);
                tag_btn.setTextColor(Color.parseColor("#95989A"));
            }
            else{
                if(keycard_list.size() == 4){
                    Toast.makeText(getApplicationContext(), "키카드는 4개까지만 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                }
                tag_btn.setTextColor(Color.parseColor("#FFDC52"));
                Log.i("keycard", tag_str);
                keycard_list.add(tag_str);
            }
        }
    };

    // 선택된 이미지 파일명 가져오기
    public String getImageNameToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

        return imgName;
    }

    // 선택된 이미지 가져오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_COVER_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    card_file = data.getData();

                    if(card_file != null) {
                        card_cover_btn.setImageURI(card_file);
                        card_cover_btn.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        if(requestCode == CREATE_CONTENT_CODE){
            if(resultCode == Activity.RESULT_OK){
                try {
                    this.modules = (ArrayList<Content>) data.getSerializableExtra("contents");
                    this.each_content_data = data.getStringExtra("each_content_type");
                    LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    for(Content content : modules) {
                        if (content.type == TEXT) {
                            TextView content_text = new TextView(getApplicationContext());
                            content_text.setLayoutParams(content_params);
                            content_text.setText(content.src);
                            content_text.setTextSize(14);
                            content_text.setTextColor(Color.parseColor("#95989A"));
                            create_content_layout.addView(content_text);
                        } else if (content.type == IMAGE) {
                            ImageView content_image = new ImageView(getApplicationContext());
                            content_image.setLayoutParams(content_params);
                            content_image.setImageURI(Uri.parse(content.src));
                            content_image.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            create_content_layout.addView(content_image);
                        } else {
                            create_content_image.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(modules != null){
                    create_content_image.setVisibility(View.INVISIBLE);

                }
            }
            return;
        }
    }

    protected RequestBody getImage(Uri data){
        RequestBody content;
        BitmapFactory.Options options = new BitmapFactory.Options();
//                       options.inSampleSize = 4; //얼마나 줄일지 설정하는 옵션 4--> 1/4로 줄이겠다

        InputStream in = null;
        try {
            in = getContentResolver().openInputStream(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
                        /*inputstream 형태로 받은 이미지로 부터 비트맵을 만들어 바이트 단위로 압축
                        그이우 스트림 배열에 담아서 전송합니다.
                         */

        Bitmap bitmap = BitmapFactory.decodeStream(in, null, options); // InputStream 으로부터 Bitmap 을 만들어 준다.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        // 압축 옵션( JPEG, PNG ) , 품질 설정 ( 0 - 100까지의 int형 ), 압축된 바이트 배열을 담을 스트림
        content = RequestBody.create(MediaType.parse("image/*"), baos.toByteArray());

        return content;
    }

}
