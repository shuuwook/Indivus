package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.UserInfo;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-27.
 */

public class LoginPresenter {

    boolean result;
    private UserInfo user_info;
    NetworkService service;

    public boolean tryLogin(String email, String password){
        service = Indivus.getInstance().getNetworkService();

        Call<LoginResult> loginResultCall = service.tryLogin(email, password);
        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()){
                    //login한 user의 정보를 받아온다
                    if(response.body().message.equals("login success")){
                        result = true;
                    }
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
        return result;
    }

    public UserInfo getUserInfo(){
        return user_info;
    }

}
