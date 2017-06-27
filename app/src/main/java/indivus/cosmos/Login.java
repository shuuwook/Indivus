package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Administrator on 2017-06-27.
 */

public class Login extends Activity {

    EditText login_email;
    EditText login_pw;
    Button login_login_btn;
    String sEmail, sPw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_1);
        //startActivity(new Intent(this, Splash.class)); //splash

        //임시 유저 데이터
        final String email = "jiyong";
        final String pw = "1234";


        login_email = (EditText) findViewById(R.id.login_email_tx);
        login_pw = (EditText) findViewById(R.id.login_pw_tx);
        login_login_btn = (Button) findViewById(R.id.login_login_btn);


        //로그인 버튼
        login_login_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sEmail = login_email.getText().toString();
                sPw = login_pw.getText().toString();

                if (sEmail.equals(email) && sPw.equals(pw)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(Login.this, "이메일이나 비밀번호가 틀렸습니다", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Sign Up 버튼튼




   }

}
