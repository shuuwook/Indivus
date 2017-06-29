package indivus.cosmos.presenter;

import indivus.cosmos.model.data.UserInfo;
import indivus.cosmos.model.network.NetworkService;

/**
 * Created by Administrator on 2017-06-28.
 */

public class SignupPresenter {

    boolean confirmEmail, confirmRepw, confirmUsername;
    boolean result;
    private UserInfo user_info;
    NetworkService service;

    public boolean tryconfirmEmail(String email){

        //if (이메일 중복x) -> true

        return confirmEmail;
    }
    public boolean tryconfirmRepw(String repw){

        //if (비밀번호 일치) -> true

        return confirmRepw;
    }
    public boolean tryconfirmUsername(String username){

        //if (유저네임 중복x) -> true

        return confirmUsername;
    }
    public boolean trySignup(){
        //컨펌이 세개 모두 true면
        if (confirmEmail && confirmRepw && confirmUsername){
            result = true;
        }
        return result;
    }
    /*
    public boolean trySignup(String email, String password) {
        service = Indivus.getInstance().getNetworkService();

        Call<LoginResult> signupResultCall = service.tryLogin(email, password);
        signupResultCall.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    //login한 user의 정보를 받아온다
                    if (response.body().message.equals("login success")) {
                        result = true;
                    }
                } else {
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
    }*/
}