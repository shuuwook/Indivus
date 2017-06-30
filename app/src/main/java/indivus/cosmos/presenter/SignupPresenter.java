package indivus.cosmos.presenter;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import java.util.ArrayList;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.CategoryResult;
import indivus.cosmos.model.server.CheckResult;
import indivus.cosmos.model.server.EmailCheckData;
import indivus.cosmos.model.server.NameCheckData;
import indivus.cosmos.model.server.SelectCategoryData;
import indivus.cosmos.model.server.SelectCategoryResult;
import indivus.cosmos.model.server.SignUpData;
import indivus.cosmos.model.server.SignUpResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-06-28.
 */

public class SignUpPresenter {
    private final int success = 0;
    private final int fail = 1;
    private final int email_already_exists = 2;
    private final int username_already_exists = 3;

    private int user_code;

    ArrayList<CategoryResult.Category> categories;

    NetworkService service;

    public int getUserCode() { return user_code; }

    public ArrayList<CategoryResult.Category> getCategories() { return categories; }

    public SignUpPresenter() {
        //Initialize Network
        service = Indivus.getInstance().getNetworkService();
    }

    public boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void confirmEmail(String email, final ResponseCallBack callBack){
        EmailCheckData email_check = new EmailCheckData(email);
        Call<CheckResult> checkResultCall = service.checkEmail(email_check);
        checkResultCall.enqueue(new Callback<CheckResult>() {
            @Override
            public void onResponse(Call<CheckResult> call, Response<CheckResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("email check success"))
                            callBack.onSuccess(success);
                    else callBack.onError(email_already_exists);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }
            @Override
            public void onFailure(Call<CheckResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public boolean confirmPassword(String password, String password_confirm){
        if(password.compareTo(password_confirm) == 0) return true;
        else return false;
    }

    public void confirmUsername(String username, final ResponseCallBack callBack){
        NameCheckData username_check = new NameCheckData(username);
        Call<CheckResult> stringCall = service.checkName(username_check);
        stringCall.enqueue(new Callback<CheckResult>() {
            @Override
            public void onResponse(Call<CheckResult> call, Response<CheckResult> response) {
                if(response.isSuccessful()){
                        if(response.body().message.equals("username check success"))
                            callBack.onSuccess(success);
                        else callBack.onError(username_already_exists);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }
            @Override
            public void onFailure(Call<CheckResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public void signUpNext(String email, String password, String username, final ResponseCallBack callBack){
        SignUpData data = new SignUpData(email, password, username);

        Call<SignUpResult> signUpResultCall = service.signUpNext(data);
        signUpResultCall.enqueue(new Callback<SignUpResult>() {
            @Override
            public void onResponse(Call<SignUpResult> call, Response<SignUpResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("success")){
                        user_code = response.body().id;
                        callBack.onSuccess(success);
                    }
                    else callBack.onError(fail);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SignUpResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }

    public void getCategoryList(final CategoriesCallBack callBack){

        Call<CategoryResult> categoryResultCall = service.getCategory();
        categoryResultCall.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if(response.isSuccessful()) {
                    categories = response.body().result;
                    callBack.getCategories(categories);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }
    public void SignUpCategories(int user_code, ArrayList<Integer> category_selection, final ResponseCallBack callBack){
        SelectCategoryData category_data = new SelectCategoryData(user_code, category_selection);
        Call<SelectCategoryResult> signUpResultCall = service.signUp(category_data);
        signUpResultCall.enqueue(new Callback<SelectCategoryResult>() {
            @Override
            public void onResponse(Call<SelectCategoryResult> call, Response<SelectCategoryResult> response) {
                if(response.isSuccessful()){
                    if(response.body().message.equals("Signup success")){
                        callBack.onSuccess(success);
                    }
                    else callBack.onError(fail);
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<SelectCategoryResult> call, Throwable t) {
                Log.i("network error", t.getMessage());
            }
        });
    }
}
