package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.login.LoginData;
import indivus.cosmos.model.server.login.LoginResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-27.
 */

public class LoginPresenter {
    private final int success = 0;
    private final int fail = 1;

    NetworkService service;

    public LoginPresenter() {
        service = Indivus.getInstance().getNetworkService();
    }

    public void tryLogin(String email, String password, final ResponseCallBack callBack){

        LoginData login_data = new LoginData(email, password);

        Call<LoginResult> loginResultCall = service.tryLogin(login_data);
        loginResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()){
                    //login한 user의 정보를 받아온다
                    if(response.body().message.equals("login success")){
                        Indivus.getInstance().savePreferences(response.body().token);
                        callBack.onSuccess(success);
                    }
                    else{
                        callBack.onError(fail);
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
    }
}
