package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.jar.Manifest;

import indivus.cosmos.model.data.Content;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class CreateContentActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 209;
    final int SELECT_CONTENT_IMAGE_CODE = 200;
    final int CREATE_CONTENT_CODE = 300;
    final private int TEXT = 0;
    final private int IMAGE = 1;

    Button text_module_btn;
    Button image_module_btn;

    LinearLayout module_content_layout;

    Button back_btn;
    Button complete_btn;

    Uri image_file;

    ImageView guide_image;
    ArrayList<Content> contents;
    ArrayList<EditText> text_modules;
    ArrayList<String> image_modules;

    ArrayList<Integer> each_content;
    int module_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_content);

        if (checkSelfPermission(READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
        contents = new ArrayList<>();
        text_modules = new ArrayList<>();
        image_modules = new ArrayList<>();
        each_content = new ArrayList<>();
        module_index = 0;

        guide_image = (ImageView)findViewById(R.id.create_guide_image);
        text_module_btn = (Button)findViewById(R.id.text_module_btn);
        image_module_btn = (Button)findViewById(R.id.image_module_btn);

        module_content_layout = (LinearLayout)findViewById(R.id.content_module_layout);
        final LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        content_params.setMargins(15, 24, 15, 24);

        back_btn = (Button)findViewById(R.id.create_back_btn);
        complete_btn = (Button)findViewById(R.id.create_complete_btn);

        text_module_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text_module = new EditText(getApplicationContext());
                text_module.setLayoutParams(content_params);
                text_module.setMinLines(1);
                text_module.setTextColor(Color.parseColor("#95989A"));
                text_module.setBackgroundResource(R.drawable.content_module_background);
                module_content_layout.addView(text_module);

                text_modules.add(text_module);

                each_content.add(module_index, new Integer(TEXT));
                module_index++;

                guide_image.setVisibility(View.INVISIBLE);
            }
        });

        image_module_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECT_CONTENT_IMAGE_CODE);

                guide_image.setVisibility(View.INVISIBLE);
            }
        });

        complete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int text_index = 0;
                int image_index = 0;

                for(int type : each_content){
                    if(type == TEXT){
                        Content content = new Content(TEXT, text_modules.get(text_index).getText().toString());
                        text_index++;
                        contents.add(content);
                    }
                    else if(type == IMAGE){
                        Content content = new Content(IMAGE, image_modules.get(image_index));
                        image_index++;
                        contents.add(content);
                    }
                    else{
                        //null
                    }
                }

                Intent create_intent = getIntent();
                create_intent.putExtra("contents", contents);
                create_intent.putExtra("each_content_type", each_content.toString());
                setResult(RESULT_OK, create_intent);
                finish();
            }
        });
    }
    // 선택된 이미지 가져오기
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LinearLayout.LayoutParams content_params = new LinearLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        content_params.setMargins(15, 24, 15, 24);

        if (requestCode == SELECT_CONTENT_IMAGE_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    image_file = data.getData();
                    if(image_file != null) {
                        ImageView image_module = new ImageView(getApplicationContext());
                        image_module.setLayoutParams(content_params);
                        image_module.setImageURI(image_file);
                        module_content_layout.addView(image_module);

                        image_modules.add(image_file.toString());
                        image_file = null;

                        each_content.add(module_index, new Integer(IMAGE));
                        module_index++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
