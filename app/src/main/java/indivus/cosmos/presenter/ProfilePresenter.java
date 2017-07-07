package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.profile.ProfileResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-02.
 */

public class ProfilePresenter {

    NetworkService service;

    public ProfilePresenter() {
        service = Indivus.getInstance().getNetworkService();
    }

    public void getProfile(final ProfileCallBack callBack){
        String token = Indivus.getInstance().getPreferences();

        Call<ProfileResult> profileResultCall = service.getProfile(token);
        profileResultCall.enqueue(new Callback<ProfileResult>() {
            @Override
            public void onResponse(Call<ProfileResult> call, Response<ProfileResult> response) {
                if(response.isSuccessful()){
                    callBack.getProfile(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProfileResult> call, Throwable t) {

            }
        });
    }
}
