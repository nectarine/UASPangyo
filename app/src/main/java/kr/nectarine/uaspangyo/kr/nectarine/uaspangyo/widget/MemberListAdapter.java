package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.ButterKnife;
import kr.nectarine.uaspangyo.R;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model.Member;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class MemberListAdapter extends ArrayAdapter<Member> {

    Transformation roundTransformation = new RoundedTransformationBuilder().borderColor(Color.LTGRAY).borderWidthDp(3).cornerRadiusDp(30).oval(true).build();

    public MemberListAdapter(Context context, int resource, Member[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_member, parent, false);
        }

        Member member = getItem(position);

        TextView tvName = ButterKnife.findById(convertView, R.id.tv_name);
        ImageView ivPhoto = ButterKnife.findById(convertView, R.id.iv_photo);

        if (member.isHelper) {
            tvName.setText(member.name + getContext().getString(R.string.suffix_helper));
        }else {
            tvName.setText(member.name);
        }

        Picasso.with(getContext()).load(member.photo).transform(roundTransformation).into(ivPhoto);
        ivPhoto.setTransitionName(member.name);
        return convertView;
    }
}
