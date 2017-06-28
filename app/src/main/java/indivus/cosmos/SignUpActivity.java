package indivus.cosmos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import indivus.cosmos.presenter.SignupPresenter;

import static indivus.cosmos.R.drawable.confirm;
import static indivus.cosmos.R.id.signup_email_confirm;

public class SignUpActivity extends AppCompatActivity {

    EditText signup_email_edit;
    EditText signup_password_edit;
    EditText signup_repw_edit;
    EditText signup_username_edit;

    Button signup_email_confirm_btn;
    Button signup_repw_confirm_btn;
    Button signup_username_confirm_btn;

    Button signup_next_toSelectCategory_btn;

    SignupPresenter signup_presenter


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_email_edit = (EditText)findViewById(R.id.signup_email_tx);
        signup_password_edit = (EditText)findViewById(R.id.signup_password_tx);
        signup_repw_edit = (EditText)findViewById(R.id.signup_repw_tx);
        signup_username_edit = (EditText)findViewById(R.id.signup_username_tx);

        signup_email_confirm_btn = (Button)findViewById(signup_email_confirm);
        signup_repw_confirm_btn = (Button)findViewById(R.id.signup_repw_confirm);
        signup_username_confirm_btn = (Button)findViewById(R.id.signup_username_confirm);

        signup_next_toSelectCategory_btn = (Button) findViewById(R.id.signup_next);

        signup_presenter = new SignupPresenter();

        final boolean result_email, result_pw, result_username;


        signup_email_confirm_btn.setOnClickListener(new View.OnClickListener(){

            String email = signup_email_edit.getText().toString();
            @Override
            public void onClick(View v) {
                result_email = signup_presenter.tryconfirmEmail(email);
                if(result_email){
                    //confirm아이콘 변화
                    signup_email_confirm_btn.setBackground(R.drawable.check);
                }
                else{
                    signup_email_confirm_btn.setBackgroundColor(R.drawable.confirm);
                }
            }
        });


    }
}
