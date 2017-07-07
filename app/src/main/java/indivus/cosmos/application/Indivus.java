package indivus.cosmos.application;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.util.Log;

import com.tsengvn.typekit.Typekit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import indivus.cosmos.model.network.NetworkService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by seowo on 2017-06-27.
 */

public class Indivus extends Application {

    private static Indivus indivus;

    private static String baseUrl = "http://192.168.200.165:3000";
    //private static String baseUrl = "http://13.124.131.26:3000";

    private NetworkService network_service;

    public static Indivus getInstance(){
        return indivus;
    }

    public NetworkService getNetworkService(){ return network_service; }

    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/NotoSansLao-Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/NotoSansLao-Bold.ttf"));

        Indivus.indivus = this;
        buildService();
    }

    public void buildService(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        network_service = retrofit.create(NetworkService.class);
    }

    // 값 불러오기
    public String getPreferences(){
        SharedPreferences pref = getSharedPreferences("indivus_token", MODE_PRIVATE);
        return pref.getString("Authorization", "");
    }

    // 값 저장하기
    public void savePreferences(String token){
        SharedPreferences pref = getSharedPreferences("indivus_token", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Authorization", token);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public void removePreferences(){
        SharedPreferences pref = getSharedPreferences("indivus_token", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("Authorization");
        editor.commit();
    }

    //RelativeTime 계산
    public static String getRelativeDate(String srcDate){
        SimpleDateFormat desiredFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        long dateInMillis = 0;
        try {
            Date date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime();
        } catch (ParseException e) {
            Log.d("parsing date : ", e.getMessage());
            e.printStackTrace();
            return "";
        }

        return DateUtils.getRelativeDateTimeString(getInstance().getApplicationContext(), dateInMillis
                , DateUtils.MINUTE_IN_MILLIS, DateUtils.YEAR_IN_MILLIS, DateUtils.FORMAT_NO_NOON).toString();
    }
}
