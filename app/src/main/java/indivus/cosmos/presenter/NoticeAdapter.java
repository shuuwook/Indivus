package indivus.cosmos.presenter;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.MessageResult;
import indivus.cosmos.model.server.notice.NoticeIdData;
import indivus.cosmos.model.server.notice.NoticeResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seowo on 2017-07-06.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{
    final private int SUCCESS = 0;
    final private int FAIL = 1;

    public ArrayList<NoticeResult.Notice> notice_list;

    Context context;
    NetworkService service;

    public NoticeAdapter(Context context, final ResponseCallBack callBack) {
        this.context = context;
        service = Indivus.getInstance().getNetworkService();
        String token = Indivus.getInstance().getPreferences();
        Call<NoticeResult> noticeResultCall = service.getNotice(token);
        noticeResultCall.enqueue(new Callback<NoticeResult>() {
            @Override
            public void onResponse(Call<NoticeResult> call, Response<NoticeResult> response) {
                if(response.isSuccessful()){
                    notice_list = response.body().result;
                    callBack.onSuccess(SUCCESS);
                }
            }
            @Override
            public void onFailure(Call<NoticeResult> call, Throwable t) {
                callBack.onError(FAIL);
            }
        });
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        holder.notice_type.setText(notice_list.get(position).to_type);
        holder.notice_time.setText(Indivus.getRelativeDate(notice_list.get(position).date));
        Glide.with(context).load(Uri.parse(notice_list.get(position).profile_photo)).thumbnail(0.1f).into(holder.profile_img);
        holder.notice_text.setText(notice_list.get(position).text);

        holder.notice_id = notice_list.get(position).notice_id;
    }

    @Override
    public int getItemCount() {
        return notice_list != null ? notice_list.size() : 0;
    }

    public void removeNotice(final int position){
        String token = Indivus.getInstance().getPreferences();
        if(position > notice_list.size()) {
            NoticeIdData data = new NoticeIdData(notice_list.get(position).notice_id);

            Call<MessageResult> noticeCall = service.removeNotice(token, data);
            noticeCall.enqueue(new Callback<MessageResult>() {
                @Override
                public void onResponse(Call<MessageResult> call, Response<MessageResult> response) {
                    if (response.isSuccessful()) {
                        if (response.body().message.equals("delete success")) {
                            Log.i("delete", "message");
                            notice_list.remove(position);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageResult> call, Throwable t) {

                }
            });
        }
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{

        public int notice_id;
        public TextView notice_type;
        public TextView notice_time;
        public ImageView profile_img;
        public TextView notice_text;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            notice_type = (TextView)itemView.findViewById(R.id.item_notice_type);
            notice_time = (TextView)itemView.findViewById(R.id.item_notice_time);
            profile_img = (ImageView)itemView.findViewById(R.id.item_notice_profile_img);
            profile_img.setBackground(new ShapeDrawable(new OvalShape()));
            profile_img.setClipToOutline(true);
            notice_text = (TextView)itemView.findViewById(R.id.item_notice_text);
        }
    }
}
