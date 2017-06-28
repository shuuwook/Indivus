package indivus.cosmos.model.network;

import indivus.cosmos.model.server.CardResult;
import indivus.cosmos.model.server.LoginResult;
import indivus.cosmos.model.server.SignUpData;
import indivus.cosmos.model.server.SignUpResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by seowo on 2017-06-25.
 */

public interface NetworkService  {

    @GET("/post/{post_id}")
    Call<CardResult> getCardResult(@Path("post_id") int post_id);

    @POST("/user/{id}")
    Call<LoginResult> tryLogin(@Path("id") String id, @Body String password);

    @POST("/user")
    Call<SignUpResult> signUp(@Body SignUpData data);

  /*  @Multipart
    @POST("/post/{id}")
    Call<ResisterResult> registerPost(@Part MultipartBody.Part file,
                                      @Part("writer") RequestBody writer,
                                      @Part("title") RequestBody title,
                                      @Part("content") RequestBody contents);*/


}

