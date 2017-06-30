package indivus.cosmos.model.network;

import java.util.ArrayList;

import indivus.cosmos.model.server.CardResult;
import indivus.cosmos.model.server.CategoryResult;
import indivus.cosmos.model.server.CheckResult;
import indivus.cosmos.model.server.EmailCheckData;
import indivus.cosmos.model.server.LoginData;
import indivus.cosmos.model.server.LoginResult;
import indivus.cosmos.model.server.NameCheckData;
import indivus.cosmos.model.server.SelectCategoryData;
import indivus.cosmos.model.server.SelectCategoryResult;
import indivus.cosmos.model.server.SignUpData;
import indivus.cosmos.model.server.SignUpResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by seowo on 2017-06-25.
 */

public interface NetworkService {

    @GET("/post")
    Call<CardResult> getCardResult();

    @POST("/login")
    Call<LoginResult> tryLogin(@Body LoginData login_data);

    @POST("/signup/check")
    Call<CheckResult> checkEmail(@Body EmailCheckData email);

    @POST("/signup/check")
    Call<CheckResult> checkName(@Body NameCheckData username);

    @POST("/signup")
    Call<SignUpResult> signUpNext(@Body SignUpData sign_up_data);

    @GET("/selectCategory")
    Call<CategoryResult> getCategory();

    @POST("/selectCategory")
    Call<SelectCategoryResult> signUp(@Body SelectCategoryData category_data);

  /*  @Multipart
    @POST("/post/{id}")
    Call<ResisterResult> registerPost(@Part MultipartBody.Part file,
                                      @Part("writer") RequestBody writer,
                                      @Part("title") RequestBody title,
                                      @Part("content") RequestBody contents);*/


}

