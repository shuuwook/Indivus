package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017-06-28.
 */

public class SignUpActivity extends Activity {

    EditText signup_email, signup_pw, signup_repw, signup_username;
    String sEmail, sPw, sRepw, sUsername;
    ImageView signup_email_confirm, signup_repw_confirm, signup_username_confirm;
    Button signup_next_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        final String email = "caying";
        final String username = "jiyong";

        signup_email = (EditText) findViewById(R.id.signup_email_tx);
        signup_pw = (EditText) findViewById(R.id.signup_pw_tx);
        signup_repw = (EditText) findViewById(R.id.signup_repw_tx);
        signup_username = (EditText) findViewById(R.id.signup_username_tx);

        sEmail = signup_email.toString();
        sPw = signup_pw.toString();
        sRepw = signup_repw.toString();
        sUsername = signup_username.toString();

        signup_email_confirm = (ImageView) findViewById(R.id.signup_email_confirm);
        signup_repw_confirm = (ImageView) findViewById(R.id.signup_repw_confirm);
        signup_username_confirm = (ImageView) findViewById(R.id.signup_username_confirm);

        signup_next_btn = (Button) findViewById(R.id.next);

        signup_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sEmail = signup_email.getText().toString();
                if (sEmail.equals(email)){
                    signup_email_confirm.setImageResource(R.drawable.confirm);
                }
                else if(sEmail.length()==0){
                    signup_email_confirm.setImageResource(R.drawable.confirm);
                }
                //else if(!sEmail.contains("@"))
                else {
                    signup_email_confirm.setImageResource(R.drawable.check);
                }
            }
        });
        signup_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sPw = signup_pw.getText().toString();

            }
        });
        signup_repw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sRepw = signup_repw.getText().toString();
                if (sRepw.equals(sPw)){
                    signup_repw_confirm.setImageResource(R.drawable.check);
                }
                //else if(sEmail.length()==0){
                //    signup_email_confirm.setImageResource(R.drawable.confirm);
                //}
                //else if(!sEmail.contains("@"))
                else {
                    signup_repw_confirm.setImageResource(R.drawable.confirm);
                }

            }
        });
        signup_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                sUsername = signup_username.getText().toString();
                if (sUsername.equals(username)){
                    signup_username_confirm.setImageResource(R.drawable.confirm);
                }
                else if(sEmail.length()==0){
                    signup_username_confirm.setImageResource(R.drawable.confirm);
                }
                //else if(!sEmail.contains("@"))
                else {
                    signup_username_confirm.setImageResource(R.drawable.check);
                }

            }
        });

        signup_next_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if (signup_email_confirm) 컨펌이 내려졌을때
                Intent intent = new Intent(getApplicationContext(), SelectMainKeycards.class);
                startActivity(intent);
                finish();
            }
        });





    }
}
