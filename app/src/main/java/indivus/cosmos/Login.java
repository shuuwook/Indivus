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

import indivus.cosmos.presenter.LoginPresenter;

/**
 * Created by Administrator on 2017-06-27.
 */

public class Login extends Activity {

    EditText email_edit;
    EditText password_edit;

    Button login_btn;
    Button login_facebook_btn;
    Button sign_up_btn;

    LoginPresenter login_presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        startActivity(new Intent(this, Splash.class)); //splash

        email_edit = (EditText)findViewById(R.id.login_email_edit);
        password_edit = (EditText)findViewById(R.id.login_password_edit);

        login_btn = (Button)findViewById(R.id.login_btn);
        login_facebook_btn = (Button)findViewById(R.id.login_facebook_btn);
        sign_up_btn = (Button)findViewById(R.id.signup_btn);

        login_presenter = new LoginPresenter();

        login_btn.setOnClickListener(new View.OnClickListener() {
            boolean result;
            String email = email_edit.getText().toString();
            String password = password_edit.getText().toString();

            @Override
            public void onClick(View v) {
                result = login_presenter.tryLogin(email, password);
                //login success
                if(result){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class)); //main 화면
                    finish();
                }
                //login fail
                else{
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 잘못 되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

    }

}
