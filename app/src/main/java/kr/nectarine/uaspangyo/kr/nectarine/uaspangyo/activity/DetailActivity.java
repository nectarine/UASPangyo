package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kr.nectarine.uaspangyo.R;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model.Member;

public class DetailActivity extends Activity {
    Transformation roundTransformation = new RoundedTransformationBuilder().borderColor(Color.LTGRAY).borderWidthDp(3).cornerRadiusDp(30).oval(true).build();

    @InjectView(R.id.iv_photo)
    ImageView ivPhoto;

    @InjectView(R.id.tv_comment)
    TextView tvComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);
        ButterKnife.inject(this);

        Member member = getIntent().getParcelableExtra("member");
        tvComment.setText(member.comment);
        Picasso.with(this).load(member.photo).transform(roundTransformation).into(ivPhoto);
        ivPhoto.setTransitionName(member.name);
        getActionBar().setTitle(member.name);

        Log.d("tag", member.comment);

    }

}
