package indivus.cosmos;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import indivus.cosmos.presenter.ResponseCallBack;
import indivus.cosmos.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity {
    private final int success = 0;
    private final int fail = 1;
    private final int email_already_exists = 2;
    private final int username_already_exists = 3;

    ConstraintLayout signup_email;
    ConstraintLayout signup_password;
    ConstraintLayout signup_username;

    EditText signup_email_edit;
    EditText signup_password_edit;
    EditText signup_password_edit_confirm;
    EditText signup_username_edit;

    ImageView signup_email_confirm;
    ImageView signup_username_confirm;

    Button signup_next_btn;

    boolean isEmailChecked = false;
    boolean isNameChecked = false;

    int result_code;

    SignUpPresenter signup_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_presenter = new SignUpPresenter();

        signup_email = (ConstraintLayout)findViewById(R.id.signup_email);
        signup_password = (ConstraintLayout)findViewById(R.id.signup_pw);
        signup_username = (ConstraintLayout)findViewById(R.id.signup_username);

        signup_email_edit = (EditText)findViewById(R.id.signup_email_tx);
        signup_password_edit = (EditText)findViewById(R.id.signup_pw_tx);
        signup_password_edit_confirm = (EditText)findViewById(R.id.signup_repw_tx);
        signup_username_edit = (EditText)findViewById(R.id.signup_username_tx);

        signup_email_confirm = (ImageView)findViewById(R.id.signup_email_confirm);
        signup_username_confirm = (ImageView)findViewById(R.id.signup_username_confirm);

        signup_next_btn = (Button)findViewById(R.id.next);

       signup_email_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               //when hasFocus == false and isEmailChecked == false
                if(hasFocus == false && isEmailChecked == false){
                    String email = signup_email_edit.getText().toString();
                    //email 형식 체크
                    if(!signup_presenter.isValidEmail(email)){
                        signup_email.setBackgroundResource(R.drawable.round_edit_text_exist_box);
                        signup_email_confirm.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(), "이메일 형식이 맞지 않습니다", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    signup_presenter.confirmEmail(email, new ResponseCallBack() {
                        @Override
                        public void onSuccess(int result) {
                            if(result == success){
                                signup_email.setBackgroundResource(R.drawable.round_edit_text_checked_box);
                                signup_email_confirm.setVisibility(View.VISIBLE);

                                isEmailChecked = true;
                            }
                        }
                        @Override
                        public void onError(int result) {
                            if(result == email_already_exists){
                                signup_email.setBackgroundResource(R.drawable.round_edit_text_exist_box);
                                signup_email_confirm.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
           }
       });

        signup_email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //null
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmailChecked = false;
                signup_email.setBackgroundResource(R.drawable.round_edit_text_box);
                signup_email_confirm.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                isEmailChecked = false;
            }
        });

        signup_username_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //when hasFocus == false and isEmailChecked == false
                if(hasFocus == false && isNameChecked == false){
                    signup_presenter.confirmUsername(signup_username_edit.getText().toString(), new ResponseCallBack() {
                        @Override
                        public void onSuccess(int result) {
                            if(result_code == success){
                                signup_username.setBackgroundResource(R.drawable.round_edit_text_checked_box);
                                signup_username_confirm.setVisibility(View.VISIBLE);
                                isNameChecked = true;
                            }
                        }

                        @Override
                        public void onError(int result) {
                            if(result_code == username_already_exists){
                                signup_username.setBackgroundResource(R.drawable.round_edit_text_exist_box);
                                signup_username_confirm.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

        signup_username_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //null
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isNameChecked = false;
                signup_username.setBackgroundResource(R.drawable.round_edit_text_box);
                signup_username_confirm.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                isNameChecked = false;
            }
        });

        signup_next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = signup_password_edit.getText().toString();
                String password_confirm = signup_password_edit_confirm.getText().toString();

                boolean isPasswordCorrect = signup_presenter.confirmPassword(password, password_confirm);
                if(!isPasswordCorrect){
                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 확인해주시기 바랍니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isEmailChecked && isNameChecked && isPasswordCorrect){
                    String email = signup_email_edit.getText().toString();
                    String username = signup_username_edit.getText().toString();
                    signup_presenter.signUpNext(email, password, username, new ResponseCallBack() {
                        @Override
                        public void onSuccess(int result) {
                            Intent intent = new Intent(getApplicationContext(), SignUpSelectCategoryActivity.class);
                            intent.putExtra("user_code", signup_presenter.getUserCode());
                            startActivity(intent); //main 화면
                            finish();
                        }

                        @Override
                        public void onError(int result) {
                            Toast.makeText(getApplicationContext(), "회원가입 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
