package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.data.UserInfo;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.LoginData;
import indivus.cosmos.model.server.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-27.
 */

public class LoginPresenter {
    private final int success = 0;
    private final int fail = 1;
    private final int email_already_exists = 2;
    private final int username_already_exists = 3;

    boolean result;
    NetworkService service;

    public boolean tryLogin(String email, String password, final ResponseCallBack callBack){
        service = Indivus.getInstance().getNetworkService();

        LoginData login_data = new LoginData(email, password);

        Call<LoginResult> loginResultCall = service.tryLogin(login_data);
        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()){
                    //login한 user의 정보를 받아온다
                    if(response.body().message.equals("login success")){
                        UserInfo.user_token = response.body().token;
                        callBack.onSuccess(success);
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
}
