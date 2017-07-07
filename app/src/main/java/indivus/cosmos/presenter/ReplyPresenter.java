package indivus.cosmos.presenter;

import android.util.Log;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.reply.ReplyDetailData;
import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyMessage;
import indivus.cosmos.model.server.reply.ReplyPostId;
import indivus.cosmos.model.server.reply.ReplyResult;
import indivus.cosmos.model.server.reply.ReplyWrite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-04.
 */

public class ReplyPresenter {

    NetworkService service;

    public ReplyPresenter() {
        service = Indivus.getInstance().getNetworkService();
    }

    public void getReply(int post_id, final ReplyCallBack callBack) {
        String token = Indivus.getInstance().getPreferences();
        ReplyPostId post_id_data = new ReplyPostId(post_id);

        Call<ReplyResult> replyResultCall = service.getReply(token, post_id_data);
        replyResultCall.enqueue(new Callback<ReplyResult>() {
            @Override
            public void onResponse(Call<ReplyResult> call, Response<ReplyResult> response) {
                if (response.isSuccessful()) {
                    callBack.getReply(response.body());
                }
                else{
                    int statusCode = response.code();
                    Log.i("server status", "CODE : " + statusCode);
                    if(response.message().equals("access denied")){
                        //callBack.accessDenied();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReplyResult> call, Throwable t) {

            }
        });
    }

    public void getReplyDetail(int comment_id, final ReplyCallBack callBack){
        String token = Indivus.getInstance().getPreferences();
        ReplyDetailData data = new ReplyDetailData(comment_id);

        final Call<ReplyDetailResult> replyDetailResultCall = service.getReplyDetail(token, data);
        replyDetailResultCall.enqueue(new Callback<ReplyDetailResult>() {
            @Override
            public void onResponse(Call<ReplyDetailResult> call, Response<ReplyDetailResult> response) {
                if(response.isSuccessful()){
                    callBack.getReplyDetail(response.body());
                }
            }

            @Override
            public void onFailure(Call<ReplyDetailResult> call, Throwable t) {

            }
        });
    }

    public void sendReply(int post_id, String reply_content, final ReplyCallBack callBack){}
}
