package indivus.cosmos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import indivus.cosmos.model.server.profile.ProfileResult;
import indivus.cosmos.presenter.ProfileCallBack;
import indivus.cosmos.presenter.ProfilePresenter;

/**
 * Created by seowo on 2017-07-06.
 */

public class ManageFragment extends Fragment {
    TextView manage_name;
    TextView manage_job;
    TextView manage_birth;
    TextView manage_gender;
    TextView manage_phone;
    TextView manage_email;
    TextView manage_locate;

    Button collection;
    Button closet;

    ProfilePresenter presenter;

    public ManageFragment() {
        presenter = new ProfilePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_myspace_manage_card, container, false);

        manage_name = (TextView)v.findViewById(R.id.myspace_manage_name);
        manage_job = (TextView)v.findViewById(R.id.myspace_manage_job);
        manage_birth = (TextView)v.findViewById(R.id.myspace_manage_birth);
        manage_gender = (TextView)v.findViewById(R.id.myspace_manage_gender);
        manage_phone = (TextView)v.findViewById(R.id.myspace_manage_phone);
        manage_email = (TextView)v.findViewById(R.id.myspace_manage_email);
        manage_locate = (TextView)v.findViewById(R.id.myspace_manage_locate);

        collection = (Button)v.findViewById(R.id.myspace_manage_collection_btn);
        closet = (Button)v.findViewById(R.id.myspace_manage_closet_btn);

        presenter.getProfile(new ProfileCallBack() {
            @Override
            public void getProfile(ProfileResult profile) {

                manage_name.setText(profile.name);
                manage_job.setText(profile.jobs);
                manage_birth.setText(profile.birth);
                if(profile.gender == 0) manage_gender.setText("남자");
                if(profile.gender == 1) manage_gender.setText("여자");
                manage_phone.setText(profile.tel);
                manage_email.setText(profile.email);
                manage_locate.setText(profile.active_Area);
            }
        });

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CollectionActivity.class));
            }
        });

        closet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ClosetActivity.class));
            }
        });

        return v;
    }
}
