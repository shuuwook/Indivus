package indivus.cosmos;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import indivus.cosmos.application.Indivus;
import indivus.cosmos.model.network.NetworkService;
import indivus.cosmos.model.server.profile.ProfileResult;
import indivus.cosmos.presenter.ProfileCallBack;
import indivus.cosmos.presenter.ProfilePresenter;

/**
 * Created by seowo on 2017-07-06.
 */

public class ProfileFragment extends Fragment {
    ImageView profile_top_img;
    ImageView profile_profile_img;

    TextView following_count;
    TextView follower_count;

    TextView profile_job;
    TextView profile_name;
    TextView profile_comment;
    TextView profile_awards;

    NetworkService service;
    ProfilePresenter presenter;

    //button
    //Button profile_top_img_setting_btn;

    public ProfileFragment() {
        service = Indivus.getInstance().getNetworkService();
        presenter = new ProfilePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myspace_profile_card, container, false);

        profile_top_img = (ImageView)v.findViewById(R.id.myspace_profile_cardview_top_img);
        profile_profile_img = (ImageView)v.findViewById(R.id.myspace_profile_cardview_profile_img);
        profile_profile_img.setBackground(new ShapeDrawable(new OvalShape()));
        profile_profile_img.setClipToOutline(true);
        following_count = (TextView)v.findViewById(R.id.myspace_profile_cardview_following_count);
        follower_count = (TextView)v.findViewById(R.id.myspace_profile_cardview_follower_count);

        profile_job = (TextView)v.findViewById(R.id.myspace_profile_cardview_job);
        profile_name = (TextView)v.findViewById(R.id.myspace_profile_cardview_name);
        profile_comment = (TextView)v.findViewById(R.id.myspace_profile_cardvidw_comment);
        profile_awards = (TextView)v.findViewById(R.id.myspace_profile_cardvidw_awards);

        //button
        //profile_top_img_setting_btn = (Button)v.setBackground(R.id.myspace_profile_cardview_top_img_setting_btn);

        presenter.getProfile(new ProfileCallBack() {
            @Override
            public void getProfile(ProfileResult profile) {
                Glide.with(getContext()).load(Uri.parse(profile.background_Image)).thumbnail(0.1f).into(profile_top_img);
                Glide.with(getContext()).load(Uri.parse(profile.profile_photo)).thumbnail(0.1f).into(profile_profile_img);

                following_count.setText(profile.following_counts+"");
                follower_count.setText(profile.follower_counts+"");

                profile_job.setText(profile.jobs);
                profile_name.setText(profile.name);
                profile_comment.setText(profile.condition_message);
                profile_awards.setText(profile.awards);
            }
        });

        /*profile_top_img_setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        return v;
    }
}
