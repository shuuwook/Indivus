package indivus.cosmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import indivus.cosmos.R;
import indivus.cosmos.ReplyActivity;
import indivus.cosmos.SeriesActivity;
import indivus.cosmos.model.data.PostContent;
import indivus.cosmos.model.data.PostRecommend;
import indivus.cosmos.model.server.post.HomeCardResult;
import indivus.cosmos.model.server.reply.ReplyDetailResult;
import indivus.cosmos.model.server.reply.ReplyResult;
import indivus.cosmos.model.server.series.SeriesResult;
import indivus.cosmos.model.server.post.PostResult;
import indivus.cosmos.view.post.ButtonViewHolder;
import indivus.cosmos.view.post.CreatorViewHolder;
import indivus.cosmos.view.post.ImageViewHolder;
import indivus.cosmos.view.post.RecommendViewHolder;
import indivus.cosmos.view.post.SeriesViewHolder;
import indivus.cosmos.view.post.TextViewHolder;

/**
 * Created by seowo on 2017-07-01.
 */

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ERROR = 0;
    private final int TYPE_CREATOR = 1;
    private final int TYPE_BUTTON = 2;
    private final int TYPE_RECOMMEND = 3;
    private final int TYPE_SERIES = 4;

    private final int TYPE_CONTENT_IMAGE = 5;
    private final int TYPE_CONTENT_TEXT = 6;

    Context context;
    PostResult post_result;
    ArrayList<PostContent> post_contents;
    ArrayList<PostRecommend> post_recommend_list;
    ArrayList<SeriesResult.PostSeries> post_series;
    int post_size;
    int post_id;

    PostContentPresenter presenter;
    ReplyPresenter reply_presenter;
    HomeFeedPresenter bulb_presenter;

    public PostAdapter(Context context, PostResult result) {
        this.context = context;

        presenter = new PostContentPresenter();

        post_result = result;

        post_id = result.post_id;
        post_contents = result.contents;
        post_size = post_contents.size();

        post_recommend_list = result.recommends;
    }

    @Override
    public int getItemViewType(int position) {
        //content
        if(position < post_size){
            if(post_contents.get(position).getType().compareTo("image") == 0) {
                return TYPE_CONTENT_IMAGE;
            }
            else if(post_contents.get(position).getType().compareTo("text") == 0){
                return TYPE_CONTENT_TEXT;
            }
            else{
                return ERROR;
            }
        }
        //series
        if(position == post_size) return TYPE_SERIES;
        //creator
        if(position == post_size + 1) return TYPE_CREATOR;
        //button
        if(position == post_size + 2) return TYPE_BUTTON;
        //recommend
        if(position == post_size + 3) return TYPE_RECOMMEND;

        return ERROR;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (viewType){
            case TYPE_CONTENT_IMAGE :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_image, parent, false);
                return new ImageViewHolder(v);
            case TYPE_CONTENT_TEXT :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_text, parent, false);
                return new TextViewHolder(v);
            case TYPE_SERIES :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_series, parent, false);
                return new SeriesViewHolder(v);
            case TYPE_CREATOR :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_creator, parent, false);
                return new CreatorViewHolder(v);
            case TYPE_BUTTON :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_button, parent, false);
                return new ButtonViewHolder(v);
            case TYPE_RECOMMEND :
                v = LayoutInflater.from(context).inflate(R.layout.post_recyclerview_recommend, parent, false);
                return new RecommendViewHolder(v);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof ImageViewHolder){
            ImageView image = ((ImageViewHolder)holder).post_content_image;
            Glide.with(context).load(Uri.parse(post_contents.get(position).getContent())).thumbnail(0.1f).into(image);

            image.setScaleType(ImageView.ScaleType.FIT_CENTER);
            image.setPadding(0,23,0,23);
        }
        else if(holder instanceof TextViewHolder){
            TextView text = ((TextViewHolder)holder).post_content_text;
            text.setText(post_contents.get(position).getContent());
            text.setTextSize(14);
            text.setPadding(0,7,0,7);
        }
        else if(holder instanceof SeriesViewHolder){
            Button series_previous_btn = ((SeriesViewHolder)holder).series_previous_btn;
            Button series_next_btn = ((SeriesViewHolder)holder).series_next_btn;
            Button series_view_btn = ((SeriesViewHolder)holder).series_view_btn;

            series_previous_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            series_next_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            series_view_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.getSeries(post_result.post_id, post_result.title, new PostContentCallback() {
                        @Override
                        public void getContent(PostResult result) {
                        }
                        @Override
                        public void getSeries(ArrayList<SeriesResult.PostSeries> result) {
                            Intent intent = new Intent(context, SeriesActivity.class);
                            intent.putExtra("series", result);
                            context.startActivity(intent);
                        }
                        @Override
                        public void clickCreator(int ID_creator) {
                        }
                        @Override
                        public void clickPrevious(int post_id) {
                        }
                        @Override
                        public void clickNext(int post_id) {
                        }
                        @Override
                        public void clickSeries(int post_id) {
                        }
                        @Override
                        public void clickAwesome(int awesome_count) {
                        }
                        @Override
                        public void clickReply(int post_id) {
                        }
                        @Override
                        public void clickCollect(int collect_count) {
                        }
                        @Override
                        public void clickRecommend(int post_id) {
                        }
                    });
                }
            });
        }
        else if(holder instanceof CreatorViewHolder){
            ImageView  profile_img = ((CreatorViewHolder)holder).profile_img;
            Glide.with(context).load(post_result.profile_photo).thumbnail(0.1f).into(profile_img);
            //이미지 모서리
            GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.background_white_round_box_r8);
            profile_img.setBackground(drawable);
            profile_img.setClipToOutline(true);

            TextView profile_name = ((CreatorViewHolder)holder).profile_name;
            profile_name.setText(post_result.username);

            TextView profile_position = ((CreatorViewHolder)holder).profile_position;
            profile_position.setText(post_result.jobs);

            Button profile_btn = ((CreatorViewHolder)holder).profile_btn;
            //버튼 누르면 프로필 액티비티
            profile_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            TextView comment = ((CreatorViewHolder)holder).comment;
            comment.setText(post_result.comment);
        }
        else if(holder instanceof ButtonViewHolder){
            final TextView bulb_count = ((ButtonViewHolder)holder).bulb_count;
            bulb_count.setText(post_result.like_counts + "");
            TextView reply_count = ((ButtonViewHolder)holder).reply_count;
            reply_count.setText(post_result.comment_counts + "");
            TextView collect_count = ((ButtonViewHolder)holder).collect_count;
            collect_count.setText(post_result.collection_counts + "");

            final Button bulb_btn = ((ButtonViewHolder)holder).bulb_btn;
            bulb_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bulb_presenter.clickAwesome(post_id, new HomeFeedCallBack() {
                        @Override
                        public void getHomeCard(HomeCardResult result) {
                            //null
                        }
                        @Override
                        public void clickAwesome(int awesome_count, boolean like) {
                            if(like) bulb_btn.setBackgroundResource(R.drawable.detailview_bulblignt);
                            else bulb_btn.setBackgroundResource(R.drawable.detailview_bulb);
                            bulb_count.setText(awesome_count+"");
                        }
                        @Override
                        public void clickCollect(int collect_count) {
                            //null
                        }
                    });
                }
            });
            Button reply_btn = ((ButtonViewHolder)holder).reply_btn;
            reply_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reply_presenter.getReply(post_id, new ReplyCallBack() {
                        @Override
                        public void getReply(ReplyResult result) {
                            Intent intent = new Intent(context, ReplyActivity.class);
                            intent.putExtra("post_id", post_id);
                            context.startActivity(intent);
                        }
                        @Override
                        public void sendReply() {
                        }
                        @Override
                        public void getReplyDetail(ReplyDetailResult result) {
                        }
                        @Override
                        public void sendReplyDetail() {
                        }
                    });
                }
            });
            Button collect_btn = ((ButtonViewHolder)holder).collect_btn;
            //collect_btn.setOnClickListener();
        }
        else if(holder instanceof RecommendViewHolder){
            RecyclerView recommend_recycler = ((RecommendViewHolder)holder).recommend_recycler;
            PostRecommendAdapter adapter = new PostRecommendAdapter(context, post_recommend_list);
            RecyclerView.LayoutManager layout_manager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);

            recommend_recycler.setLayoutManager(layout_manager);
            recommend_recycler.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return post_contents.size() + 4;
    }
}