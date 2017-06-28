package indivus.cosmos.application;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

import indivus.cosmos.model.network.NetworkService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by seowo on 2017-06-27.
 */

public class Indivus extends Application {

    private static Indivus indivus;

    private static String baseUrl = "http://192.168.200.104:3000";

    private NetworkService network_service;

    public static Indivus getInstance(){
        return indivus;
    }

    public NetworkService getNetworkService(){ return network_service; }

    @Override
    public void onCreate() {
        super.onCreate();

        /*Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "fonts/NotoSansLao-Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "fonts/NotoSansLao-Bold.ttf"));*/

        Indivus.indivus = this;
        buildService();
    }

    public void buildService(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        network_service = retrofit.create(NetworkService.class);
    }
}
