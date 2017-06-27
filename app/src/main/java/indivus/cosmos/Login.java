package indivus.cosmos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-06-27.
 */

public class Login extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_1);
        startActivity(new Intent(this, Splash.class)); //splash
    }


    public class user {
        String email;
        String password;
        user(){};
        user(String email, String password){
            this.email = email;
            this.password = password;
        }
    }
    ArrayList<user> userlist = new ArrayList<user>();
    user user1 = new user("jiyong@naver.com", "1234");
    userlist.add(user);


    public boolean confirmLogin(String email, String password){


    }


}
