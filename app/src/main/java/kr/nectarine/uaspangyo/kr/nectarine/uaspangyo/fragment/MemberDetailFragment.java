package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kr.nectarine.uaspangyo.R;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model.Member;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class MemberDetailFragment extends Fragment {
    Transformation roundTransformation = new RoundedTransformationBuilder().borderColor(Color.LTGRAY).borderWidthDp(3).cornerRadiusDp(30).oval(true).build();

    @InjectView(R.id.iv_photo)
    ImageView ivPhoto;

    private Member member;


    public static Fragment newInstance(Member member) {
        Bundle argument = new Bundle();
        argument.putParcelable("member", member);
        Fragment fragment = new MemberDetailFragment();
        fragment.setArguments(argument);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        member = getArguments().getParcelable("member");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.inject(this, rootView);
        Picasso.with(getActivity()).load(member.photo).transform(roundTransformation).into(ivPhoto);
        ivPhoto.setTransitionName(member.name);
        Log.d("tag", ivPhoto.getTransitionName());
        return rootView;
    }
}
