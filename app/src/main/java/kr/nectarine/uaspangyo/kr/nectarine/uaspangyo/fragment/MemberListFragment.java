package kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.fragment;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kr.nectarine.uaspangyo.R;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.activity.DetailActivity;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.model.Member;
import kr.nectarine.uaspangyo.kr.nectarine.uaspangyo.widget.MemberListAdapter;

/**
 * Created by Nectarine on 2014-12-14.
 */
public class MemberListFragment extends Fragment {

    @InjectView(R.id.lv_member)
    ListView lvMember;

    MemberListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);

        ImageView ivHeader = new ImageView(getActivity());
        ivHeader.setImageResource(R.drawable.main_background);
        lvMember.addHeaderView(ivHeader);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new MemberListAsyncTask().execute();

    }

    class MemberListAsyncTask extends AsyncTask<Void, Void, Member[]> {

        private static final String URL_MEMBER_LIST = "https://raw.githubusercontent.com/nectarine/UASPangyo/master/app/extra/member.json";

        @Override
        protected Member[] doInBackground(Void... params) {
            String body = HttpRequest.get(URL_MEMBER_LIST).contentType("application/json").body();
            return new Gson().fromJson(body, new TypeToken<Member[]>() {
            }.getType());
        }

        @Override
        protected void onPostExecute(Member[] members) {
            super.onPostExecute(members);
            adapter = new MemberListAdapter(getActivity(), R.layout.list_item_member, members);
            lvMember.setAdapter(adapter);
            lvMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ImageView sharedView = ButterKnife.findById(view, R.id.iv_photo);

                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("member", adapter.getItem(position-1));
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(getActivity(), sharedView, sharedView.getTransitionName());
                    startActivity(intent, options.toBundle());
                }
            });
        }
    }

}
